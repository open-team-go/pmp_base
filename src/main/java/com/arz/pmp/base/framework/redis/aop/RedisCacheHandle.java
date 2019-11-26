package com.arz.pmp.base.framework.redis.aop;

import com.alibaba.fastjson.JSON;
import com.arz.pmp.base.framework.commons.utils.Util;
import com.arz.pmp.base.framework.redis.RedisUtil;
import com.arz.pmp.base.framework.redis.TimeConstants;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.ss.formula.functions.T;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.aop.aspectj.MethodInvocationProceedingJoinPoint;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

/**
 * description java类作用描述
 *
 * @author chen wei
 * @version 1.0
 *          <p>
 *          Copyright: Copyright (c) 2019
 *          </p>
 * @date 2019/7/29 17:32
 */
@Component
@Aspect
public class RedisCacheHandle {

    private static final Logger logger = LoggerFactory.getLogger(RedisCacheHandle.class);
    /** 方法返回void */
    private static final String METHOD_RETURN_VOID = "void";

    @Autowired
    private RedisUtil redisUtil;

    @Around(value = "@annotation(redisMethodCache)")
    public Object doAroundRedis(JoinPoint jp, RedisMethodCache redisMethodCache) throws Throwable {

        MethodInvocationProceedingJoinPoint joinPoint = (MethodInvocationProceedingJoinPoint)jp;
        Object[] args = joinPoint.getArgs();
        MethodSignature signature = (MethodSignature)joinPoint.getSignature();
        System.out.println(signature.getReturnType());
        Method method = signature.getMethod();
        Type returnType = method.getGenericReturnType();
        if (!METHOD_RETURN_VOID.equals(returnType.getTypeName()) && redisMethodCache.saveOn()) {
            Class<T> returnClass = signature.getReturnType();;
            if (returnType instanceof ParameterizedType) {
                returnClass = (Class<T>)((ParameterizedType)returnType).getActualTypeArguments()[0];
            }
            String keyPre = redisMethodCache.keyPre();
            String key = null;
            if (!StringUtils.isBlank(keyPre)) {
                key = keyGenerator(keyPre, args);
            } else {

                key = keyGenerator(jp.getTarget().getClass().getName(), method, args);
            }
            Object value = null;
            try {
                value = redisUtil.get(key);
            } catch (Exception e) {
                e.printStackTrace();
            }

            if (value == null) {

                Object obj = joinPoint.proceed();
                if (Util.isEmpty(obj)) {
                    return obj;
                }
                value = JSON.toJSONString(obj);

                logger.info("方法执行返回");
                try {
                    if (redisMethodCache.expire() == TimeConstants.TIME_NO_LIMIT) {
                        redisUtil.set(key, value);
                    } else {
                        redisUtil.set(key, value, redisMethodCache.expire());
                    }

                } catch (Exception e) {
                    e.printStackTrace();
                }
                return obj;
            } else {
                logger.info("redis执行返回");
                if (returnType instanceof ParameterizedType) {
                    return JSON.parseArray(value.toString(), returnClass);
                }
                return JSON.parseObject(value.toString(), returnClass);
            }

        }
        return joinPoint.proceed();
    }

    /**
     * description redis KEY生成
     * 
     * @param className
     *            类名
     * @param method
     *            方法
     * @param params
     *            参数值
     * @author chen wei
     * @date 2019/7/30
     * @return java.lang.String
     */
    public String keyGenerator(String className, Method method, Object... params) {
        // 规定 本类名+方法名+参数名 为key
        StringBuilder sb = new StringBuilder();
        sb.append(className + "_");
        sb.append(method.getName() + "_");
        Class[] paramTypes = method.getParameterTypes();
        if (ArrayUtils.isEmpty(paramTypes)) {
            return sb.toString();
        }
        int length = paramTypes.length;
        for (int i = 0; i < length; i++) {
            sb.append(paramTypes[i].getName() + "_");
            sb.append((params[i] == null ? "null" : params[i].toString()) + "_");
        }
        return sb.toString();
    }

    /**
     * description redis KEY生成
     * 
     * @param keyPre
     *            KEY前缀
     * @param params
     *            参数值
     * @author chen wei
     * @date 2019/7/30
     * @return java.lang.String
     */
    public String keyGenerator(String keyPre, Object... params) {
        // 规定 本类名+方法名+参数名 为key
        StringBuilder sb = new StringBuilder();
        sb.append(keyPre);
        if (ArrayUtils.isEmpty(params)) {
            return sb.toString();
        }
        for (Object item : params) {
            sb.append((item == null ? "null" : item.toString()) + "_");
        }
        String result = sb.toString();
        return result.substring(0, result.length() - 1);
    }
}
