package com.arz.pmp.base.framework.core.aop;

import java.lang.reflect.Method;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.aop.aspectj.MethodInvocationProceedingJoinPoint;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.arz.pmp.base.api.service.redis.RedisService;
import com.arz.pmp.base.entity.PmpSystemLogEntity;
import com.arz.pmp.base.framework.commons.RequestHeader;
import com.arz.pmp.base.framework.commons.RestRequest;
import com.arz.pmp.base.framework.commons.response.RestResponse;
import com.arz.pmp.base.framework.commons.utils.DateUtil;
import com.arz.pmp.base.framework.core.annotation.SysLog;
import com.arz.pmp.base.framework.core.bean.FrameworkLog;
import com.arz.pmp.base.framework.core.utils.WebUtil;
import com.arz.pmp.base.mapper.PmpSystemLogEntityMapper;

import lombok.extern.slf4j.Slf4j;

@Aspect
@Component
@Slf4j
public class LogAop {

    @Autowired
    private PmpSystemLogEntityMapper pmpSystemLogEntityMapper;
    @Autowired
    private RedisService redisService;

    @Pointcut("execution(* com.arz.pmp..*.api.controller..*Controller.*(..))")
    public void api() {

    }

    @Pointcut("api()")
    private void log() {}

    @Around("log()")
    public Object doBeforeMethod(JoinPoint jp) throws Throwable {
        MethodInvocationProceedingJoinPoint joinPoint = (MethodInvocationProceedingJoinPoint)jp;
        // 请求参数
        Object[] args = joinPoint.getArgs();
        // 返回参数

        String methodName = jp.getSignature().getName();
        String clazzName = jp.getTarget().getClass().getName();

        HttpServletRequest req = WebUtil.getRequest();
        // 请求地址IP
        String ip = WebUtil.getIpAddress(req);

        // 封装相关信息
        FrameworkLog frameworkLog = new FrameworkLog();

        frameworkLog.setRequest(args);

        frameworkLog.setIp(ip);
        frameworkLog.setRequestUri(req.getRequestURI());
        frameworkLog.setClazz(clazzName + "." + methodName);
        log.info("Request=={}", JSON.toJSONString(frameworkLog));
        Object object = joinPoint.proceed();
        frameworkLog.setResponse(object);
        log.info("Response=={}", JSON.toJSONString(frameworkLog));
        // 判断是否需要写入数据库
        Method method = ((MethodSignature)jp.getSignature()).getMethod();
        if (method.isAnnotationPresent(SysLog.class) && object != null && object instanceof RestResponse) {
            SysLog sysLog = method.getAnnotation(SysLog.class);
            RestResponse resp = (RestResponse)object;
            // 返回状态码正常
            if (sysLog != null && resp.confirmSuccess()) {
                doDbLog(jp, sysLog);
            }
        }
        return object;
    }

    /**
     * description 写入数据库
     * 
     * @param jp
     * @param sysLog
     * @author chen wei
     * @date 2019/11/12
     */
    private void doDbLog(JoinPoint jp, SysLog sysLog) {

        RestRequest restRequest = getRestRequest(jp);

        if (restRequest == null) {
            return;
        }
        String appToken = getHeaderToken(restRequest);

        // 请求参数
        Object[] args = jp.getArgs();
        // 返回参数
        HttpServletRequest req = WebUtil.getRequest();
        // 请求地址IP
        String ip = WebUtil.getIpAddress(req);
        Long adminId = getOperatorId(appToken);
        // 封装相关信息
        PmpSystemLogEntity logEntity = new PmpSystemLogEntity();
        logEntity.setDelOn(false);
        logEntity.setAdminId(adminId);
        logEntity.setCreateManager(adminId);
        logEntity.setOptModule(sysLog.module().getName());
        logEntity.setCreateTime(DateUtil.getCurSecond());
        logEntity.setOptType(sysLog.type().getName());
        logEntity.setOptApi(req.getRequestURI());
        logEntity.setOptData(JSONArray.toJSONString(args));
        logEntity.setRequestIp(ip);
        logEntity.setOptDesc(sysLog.describe());

        addDBLog(logEntity);
    }

    private void addDBLog(PmpSystemLogEntity logEntity) {

        pmpSystemLogEntityMapper.insert(logEntity);
    }

    private Long getOperatorId(String token) {
        if (!StringUtils.isBlank(token)) {
            return redisService.getOperatorIdByToken(token);
        }
        return 0L;
    }

    private RestRequest getRestRequest(JoinPoint jp) {
        Object[] args = jp.getArgs();
        if (ArrayUtils.isEmpty(args)) {
            return null;
        }
        for (Object obj : args) {
            if (obj != null && obj instanceof RestRequest) {
                return (RestRequest)obj;
            }
        }
        return null;
    }

    private String getHeaderToken(RestRequest restRequest) {
        RequestHeader header = restRequest.getHeader();
        if (header == null) {
            return null;
        }
        return header.getAuthentication();
    }
}
