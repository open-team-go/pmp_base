package com.arz.pmp.base.api.interceptor;

import com.alibaba.fastjson.JSON;
import com.arz.pmp.base.api.service.redis.RedisService;
import com.arz.pmp.base.entity.PmpAdminEntity;
import com.arz.pmp.base.framework.commons.RestRequest;
import com.arz.pmp.base.framework.commons.constants.Constants;
import com.arz.pmp.base.framework.commons.enums.CommonCodeEnum;
import com.arz.pmp.base.framework.commons.response.RestResponse;
import com.arz.pmp.base.framework.commons.utils.NumberUtil;
import com.arz.pmp.base.framework.core.utils.WebUtil;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * description: 拦截器
 *
 * @author chen wei
 * @date 2019/7/12 13:10
 * @version: 1.0
 *           <p>
 *           Copyright: Copyright (c) 2019
 *           </p>
 */
public class LoginAuthInterceptor extends HandlerInterceptorAdapter {

    private static final Logger logger = LoggerFactory.getLogger(LoginAuthInterceptor.class);

    @Autowired
    private RedisService redisService;
    @Value("${service.project.auth.enable}")
    private boolean authEnable;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
        throws Exception {
        if (isAllowPath(request)) {
            return true;
        }
        if (!authEnable) {
            logger.info("properties配置中关闭权限验证");
            return true;
        }
        // 管理端校验
        if (isManagerPath(request) && !validSysToken(request, true)) {
            // 请求rest未登录
            sendRestResponse(request, response, RestResponse.error(CommonCodeEnum.PERMISSION_ERROR_LOGIN));
            return false;
        }
        // 用户端校验
        if (isFrontUserPath(request) && !validSysToken(request, false)) {
            // 请求rest未登录
            sendRestResponse(request, response, RestResponse.error(CommonCodeEnum.PERMISSION_ERROR_LOGIN));
            return false;
        }
        return true;
    }

    private void sendRestResponse(ServletRequest request, ServletResponse response, RestResponse result) {

        WebUtil.sendRestResponse(request, response, result);
    }

    /**
     * description 验证是否有访问权限，验证登录参数
     * 
     * @param request
     * @param response
     * @author chen wei
     * @date 2019/7/29
     * @return boolean
     */
    public boolean onAccessDenied(ServletRequest request, ServletResponse response) {
        HttpServletRequest req = (HttpServletRequest)request;
        HttpServletResponse res = (HttpServletResponse)response;
        // TODO 登录验证

        boolean flag = validFrontHeader(req, res);
        return flag;
    }

    /**
     * description 验证前台请求头中参数
     * 
     * @param request
     * @param response
     * @author chen wei
     * @date 2019/7/29
     * @return boolean
     */
    private boolean validFrontHeader(HttpServletRequest request, HttpServletResponse response) {

        return true;
    }

    /**
     * description 验证管理端token
     * 
     * @param request
     * @author chen wei
     * @date 2019/8/12
     * @return boolean
     */
    private boolean validSysToken(HttpServletRequest request, boolean managerFlag) {

        JSON json = WebUtil.getRequestBodyJson(request);
        if (json == null) {
            return true;
        }
        RestRequest restRequest = json.toJavaObject(RestRequest.class);
        if (restRequest == null || restRequest.getHeader() == null) {
            return false;
        }
        String token = restRequest.getHeader().getAuthentication();
        if (StringUtils.isBlank(token)) {
            return false;
        }
        // 验证登录缓存
        if (managerFlag) {
            PmpAdminEntity userInfo = redisService.getOperatorByToken(token);
            return userInfo != null;
        } else {
            Long userId = redisService.geFrontUserByToken(token);
            return NumberUtil.isPositive(userId);
        }

    }

    /**
     * description 不需要auth验证的请求
     * 
     * @param request
     * @author chen wei
     * @date 2019/8/12
     * @return boolean
     */
    private boolean isAllowPath(HttpServletRequest request) {

        String path = request.getServletPath();

        return Constants.REGEX_INTERCEPTOR_AUTH_OFF.matcher(path).matches();
    }

    /**
     * description 需要auth验证的管理后台请求
     * 
     * @param request
     * @author chen wei
     * @date 2019/8/12
     * @return boolean
     */
    private boolean isManagerPath(HttpServletRequest request) {

        String path = request.getServletPath();

        return Constants.REGEX_INTERCEPTOR_AUTH_MANAGER.matcher(path).matches();
    }

    /**
     * description 需要auth验证的管理后台请求
     * 
     * @param request
     * @author chen wei
     * @date 2019/8/12
     * @return boolean
     */
    private boolean isFrontUserPath(HttpServletRequest request) {

        String path = request.getServletPath();

        return Constants.REGEX_INTERCEPTOR_AUTH_FRONT.matcher(path).matches();
    }

}
