package com.dev.simple.config;

import org.springframework.beans.BeanUtils;
import org.springframework.core.MethodParameter;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

import java.lang.reflect.Field;
import java.util.Iterator;


/**
 * @author xiaotian.huang
 * 前端参数名下划线转驼峰
 */
public class LineToHumpHandler implements HandlerMethodArgumentResolver {

    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        return parameter.hasParameterAnnotation(LineConvertHump.class);
    }

    @Override
    public Object resolveArgument(MethodParameter parameter,
                                  ModelAndViewContainer mavContainer, NativeWebRequest webRequest,
                                  WebDataBinderFactory binderFactory) throws Exception {
        //生成结果的对象
        Object resultObject = BeanUtils.instantiate(parameter.getParameterType());
        //未经过处理的字段名  music_name
        String sourceTemp;
        //经过处理的字段名    musicName
        String executeTemp;
        //传参的值  例如  music_name=asdmsakdlasjd 的value
        String[] values;
        //得到bean中的方法
        Field[] frr = parameter.getParameterType().getDeclaredFields();
        for (Iterator<String> itr = webRequest.getParameterNames(); itr.hasNext(); ) {
            sourceTemp = itr.next();
            executeTemp = sourceTemp;
            //  music_name    ->   musicName
            while (executeTemp.indexOf("_") > 0) {
                Integer indexOf = executeTemp.indexOf("_");
                executeTemp = executeTemp.replaceFirst("_", "");
                executeTemp = executeTemp.substring(0, indexOf) + executeTemp.substring(indexOf, indexOf + 1).toUpperCase() + executeTemp.substring(indexOf + 1, executeTemp.length());
            }
            //去赋值
            for (int i = 0; i < frr.length; i++) {
                frr[i].setAccessible(true);
                if (executeTemp.equals(frr[i].getName())) {
                    values = webRequest.getParameterValues(sourceTemp);
                    frr[i].set(resultObject, values[0]);
                }
            }
        }
        return resultObject;
    }
}
