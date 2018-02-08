package com.emed.mental.common.util;

import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Field;
import java.net.URLDecoder;
import java.util.Date;

/**
 * @Author: 周润斌
 * @Date: create in 上午 14:14 2018/1/31 0031
 * @Description:
 */
public class EntityUtils {



    public static <T> void setCreateAndUpdateInfo(T entity){
        setCreateInfo(entity);
        setUpdatedInfo(entity);
    }

    public static <T> void setUpdatedInfo(T entity){
        HttpServletRequest request = ((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getRequest();
        String hostIp = "";
        String name = "";
        String id = "";
        if(request!=null) {
            hostIp = String.valueOf(request.getHeader("userHost"));
            name = String.valueOf(request.getHeader("userName"));
            name = URLDecoder.decode(name);
            id = String.valueOf(request.getHeader("userId"));
        }
        // 默认属性
        String[] fields = {"updName","updUser","updHost","updTime"};
        Field field = ReflectionUtils.getAccessibleField(entity, "updTime");
        Object [] value = null;
        if(field!=null&&field.getType().equals(Date.class)){
            value = new Object []{name,id,hostIp,new Date()};
        }
        // 填充默认属性值
        setDefaultValues(entity, fields, value);
    }

    /**
     * 快速将bean的updUser、updHost、updTime附上相关值
     * @param entity
     * @param <T>
     */
    public static <T> void setCreateInfo(T entity){

        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        String hostIp = "";
        String name = "";
        String id = "";
        if(request!=null) {
            hostIp = String.valueOf(request.getHeader("userHost"));
            name = String.valueOf(request.getHeader("userName"));
            name = URLDecoder.decode(name);
            id = String.valueOf(request.getHeader("userId"));
        }
        // 默认属性
        String[] fields = {"crtName","crtUser","crtHost","crtTime"};
        Field field = ReflectionUtils.getAccessibleField(entity, "crtTime");
        // 默认值
        Object [] value = null;
        if(field!=null&&field.getType().equals(Date.class)){
            value = new Object []{name,id,hostIp,new Date()};
        }
        // 填充默认属性值
        setDefaultValues(entity, fields, value);
    }

    /**
     * 依据对象的属性数组和值数组对对象的属性进行赋值
     *
     * @param entity 对象
     * @param fields 属性数组
     * @param value 值数组
     * @author 王浩彬
     */
    private static <T> void setDefaultValues(T entity, String[] fields, Object[] value) {
        for(int i=0;i<fields.length;i++){
            String field = fields[i];
            if(ReflectionUtils.hasField(entity, field)){
                ReflectionUtils.invokeSetter(entity, field, value[i]);
            }
        }
    }




}
