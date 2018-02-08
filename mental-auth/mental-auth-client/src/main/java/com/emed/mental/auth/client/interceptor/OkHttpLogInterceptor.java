package com.emed.mental.auth.client.interceptor;

import com.emed.mental.common.constant.CommonConstants;
import okhttp3.FormBody;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import java.io.IOException;

/**
 * @Author: 周润斌
 * @Date: create in 上午 10:51 2018/2/5 0005
 * @Description:
 */
@Component
public class OkHttpLogInterceptor implements Interceptor {

    private Logger log = LoggerFactory.getLogger(OkHttpLogInterceptor.class);

    @Override
    public Response intercept(Chain chain) throws IOException {
        long startTime = System.currentTimeMillis();
        Request request = chain.request();
        Response response = chain.proceed(request);
        long endTime = System.currentTimeMillis();
        long duration=endTime-startTime;
        okhttp3.MediaType mediaType = response.body().contentType();
        String content = response.body().string();
        log.info("\n");
        log.info("----------Start----------------");
        log.info("| "+request.toString());
        String method=request.method();
        if("POST".equals(method)){
            StringBuilder sb = new StringBuilder();
            if (request.body() instanceof FormBody) {
                FormBody body = (FormBody) request.body();
                for (int i = 0; i < body.size(); i++) {
                    sb.append(body.encodedName(i) + "=" + body.encodedValue(i) + ",");
                }
                sb.delete(sb.length() - 1, sb.length());
                log.info("| RequestParams:{"+sb.toString()+"}");
            }
        }
        log.info("| Response:" + content);
        log.info("----------End:"+duration+"毫秒----------");
        if(HttpStatus.FORBIDDEN.value()==response.code()){
            if(response.body().string().contains(String.valueOf(CommonConstants.EX_CLIENT_INVALID_CODE))){
                log.info("Client Token Expire,Retry to request...");
                /*serviceAuthUtil.refreshClientToken();*/
                Request newRequest = chain.request()
                        .newBuilder()
                       /* .header(serviceAuthConfig.getTokenHeader(),serviceAuthUtil.getClientToken())*/
                        .build();
                response = chain.proceed(newRequest);
            }
        }
        return response.newBuilder()
                .body(okhttp3.ResponseBody.create(mediaType, content))
                .build();
    }



}
