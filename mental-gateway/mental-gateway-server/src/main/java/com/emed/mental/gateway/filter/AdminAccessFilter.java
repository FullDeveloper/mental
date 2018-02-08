package com.emed.mental.gateway.filter;

import com.alibaba.fastjson.JSON;
import com.emed.mental.api.vo.authority.PermissionInfo;
import com.emed.mental.auth.client.config.ServiceAuthConfig;
import com.emed.mental.auth.client.config.UserAuthConfig;
import com.emed.mental.auth.client.jwt.ServiceAuthUtil;
import com.emed.mental.auth.client.jwt.UserAuthUtil;
import com.emed.mental.auth.common.util.jwt.IJWTInfo;
import com.emed.mental.common.context.BaseContextHandler;
import com.emed.mental.common.result.auth.TokenErrorResponse;
import com.emed.mental.common.result.auth.TokenForbiddenResponse;
import com.emed.mental.common.util.ClientUtil;
import com.emed.mental.gateway.feign.IUserFeign;
import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import java.net.URLEncoder;
import java.util.Date;
import java.util.List;
import java.util.function.Predicate;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @Author: 周润斌
 * @Date: create in 上午 17:36 2018/2/1 0001
 * @Description:
 */
@Component
public class AdminAccessFilter extends ZuulFilter {

    private static Logger logger = LoggerFactory.getLogger(AdminAccessFilter.class);

    @Value("${zuul.prefix}")
    private String proxyPrefix;;

    @Value("${gate.ignore.startWith}")
    private String startWith;

    @Autowired
    private UserAuthConfig userAuthConfig;
    @Autowired
    private UserAuthUtil userAuthUtil;
    @Autowired
    private IUserFeign userService;
    @Autowired
    private ServiceAuthConfig serviceAuthConfig;
    @Autowired
    private ServiceAuthUtil serviceAuthUtil;


    @Override
    public String filterType() {
        return "pre";
    }

    @Override
    public int filterOrder() {
        return 1;
    }

    @Override
    public boolean shouldFilter() {
        return true;
    }


    @Override
    public Object run() {
        //获取请求对象
        RequestContext context = RequestContext.getCurrentContext();
        HttpServletRequest request = context.getRequest();
        //获取请求信息
        final String requestUri = request.getRequestURI().substring(proxyPrefix.length());
        final String method = request.getMethod();
        BaseContextHandler.setToken(null);
        //判断是否需要拦截
        if(isStartWith(requestUri)){
            //直接放行
            return null;
        }
        IJWTInfo user = null;
        try {
            user = getJWTUser(request,context);
        } catch (Exception e) {
            setFailedRequest(JSON.toJSONString(new TokenErrorResponse(e.getMessage())), 200);
            return null;
        }
        List<PermissionInfo> permissionIfs = userService.getAllPermissionInfo();
        // 判断资源是否启用权限约束
        Stream<PermissionInfo> stream = getPermissionIfs(requestUri, method, permissionIfs);
        List<PermissionInfo> result = stream.collect(Collectors.toList());
        PermissionInfo[] permissions = result.toArray(new PermissionInfo[]{});
        if (permissions.length > 0) {
            checkUserPermission(permissions, context, user);
        }
        // 申请客户端密钥头
        context.addZuulRequestHeader(serviceAuthConfig.getTokenHeader(), serviceAuthUtil.getClientToken());
        BaseContextHandler.remove();
        return null;
    }

    private void checkUserPermission(PermissionInfo[] permissions, RequestContext ctx, IJWTInfo user) {
        List<PermissionInfo> permissionInfos = userService.getPermissionByUsername(user.getUniqueName());
        PermissionInfo current = null;
        for (PermissionInfo info : permissions) {
            boolean anyMatch = permissionInfos.parallelStream().anyMatch(new Predicate<PermissionInfo>() {
                @Override
                public boolean test(PermissionInfo permissionInfo) {
                    return permissionInfo.getCode().equals(info.getCode());
                }
            });
            if (anyMatch) {
                current = info;
                break;
            }
        }
        if (current == null) {
            setFailedRequest(JSON.toJSONString(new TokenForbiddenResponse("Token Forbidden!")), 200);
        } else {
            if (!RequestMethod.GET.toString().equals(current.getMethod())) {
                setCurrentUserInfoAndLog(ctx, user, current);
            }
        }
    }

    private void setCurrentUserInfoAndLog(RequestContext ctx, IJWTInfo user, PermissionInfo pm) {
        String host = ClientUtil.getClientIp(ctx.getRequest());
        ctx.addZuulRequestHeader("userId", user.getId());
        ctx.addZuulRequestHeader("userName", URLEncoder.encode(user.getName()));
        ctx.addZuulRequestHeader("userHost", ClientUtil.getClientIp(ctx.getRequest()));
       /* LogInfo logInfo = new LogInfo(pm.getMenu(), pm.getName(), pm.getUri(), new Date(), user.getId(), user.getName(), host);
        DBLog.getInstance().setLogService(logService).offerQueue(logInfo);*/
    }


    /**
     * 获取目标权限资源
     *
     * @param requestUri
     * @param method
     * @param serviceInfo
     * @return
     */
    private Stream<PermissionInfo> getPermissionIfs(final String requestUri, final String method, List<PermissionInfo> serviceInfo) {
        return serviceInfo.parallelStream().filter(new Predicate<PermissionInfo>() {
            @Override
            public boolean test(PermissionInfo permissionInfo) {
                String url = permissionInfo.getUri();
                String uri = url.replaceAll("\\{\\*\\}", "[a-zA-Z\\\\d]+");
                String regEx = "^" + uri + "$";
                return (Pattern.compile(regEx).matcher(requestUri).find() || requestUri.startsWith(url + "/"))
                        && method.equals(permissionInfo.getMethod());
            }
        });
    }

    /**
     * 网关抛异常
     *
     * @param body
     * @param code
     */
    private void setFailedRequest(String body, int code) {
        logger.debug("Reporting error ({}): {}", code, body);
        RequestContext ctx = RequestContext.getCurrentContext();
        ctx.setResponseStatusCode(code);
        if (ctx.getResponseBody() == null) {
            ctx.setResponseBody(body);
            ctx.setSendZuulResponse(false);
        }
    }

    private IJWTInfo getJWTUser(HttpServletRequest request, RequestContext context) throws Exception {
        String authToken = request.getHeader(userAuthConfig.getTokenHeader());
        if(StringUtils.isBlank(authToken)){
            authToken = request.getHeader("token");
        }
        context.addZuulRequestHeader(userAuthConfig.getTokenHeader(),authToken);
        BaseContextHandler.setToken(authToken);
        return userAuthUtil.getInfoFromToken(authToken);

    }

    /**
     * URI是以什么打头
     * @param requestUri
     * @return
     */
    private boolean isStartWith(String requestUri){
        for(String s: startWith.split(",")){
            if(requestUri.startsWith(s)){
                return true;
            }
        }
        return false;
    }

}
