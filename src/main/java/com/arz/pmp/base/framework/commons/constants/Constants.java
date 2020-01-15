package com.arz.pmp.base.framework.commons.constants;

import java.util.regex.Pattern;

/**
 * description: 全局常量类
 *
 * @Author: chen wei
 * @CreateDate: 2019/7/11 9:40
 * @UpdateDate: 2019/7/11 9:40
 * @UpdateRemark: The modified content
 * @Version: 1.0
 *           <p>
 *           Copyright: Copyright (c) 2019
 *           </p>
 */
public final class Constants {

    public static final int LOG_OPERATOR_DATA_LENGTH = 500;

    public static final int LOG_OPERATOR_DATA_START = 0;

    /**
     * 拦截器登录拦截跳过地址
     */
    private static final String REGEX_INTERCEPTOR_AUTH_OFF_STR =
        "(/)|(/configuration/.*)|(/.*swagger.*)|(/.*/api-docs.*)|(/webjars/.*)|(.*\\.html.*)|(.*\\.js.*)|(/favicon.ico)|(/back/admin/login.*)";

    public static final Pattern REGEX_INTERCEPTOR_AUTH_OFF = Pattern.compile(REGEX_INTERCEPTOR_AUTH_OFF_STR);
    /**
     * 需要auth验证的管理后台请求
     */
    private static final String REGEX_INTERCEPTOR_AUTH_MANAGER_STR = "/back/.*";

    public static final Pattern REGEX_INTERCEPTOR_AUTH_MANAGER = Pattern.compile(REGEX_INTERCEPTOR_AUTH_MANAGER_STR);
    /**
     * 需要auth验证的前台请求
     */
    private static final String REGEX_INTERCEPTOR_AUTH_FRONT_STR = "/front/.*";

    public static final Pattern REGEX_INTERCEPTOR_AUTH_FRONT = Pattern.compile(REGEX_INTERCEPTOR_AUTH_FRONT_STR);

    /**
     * 请求连接正则
     */
    public static final String REGEX_URL_STR =
        "^(https?|ftp|file)://[-a-zA-Z0-9+&@#/%?=~_|!:,.;]*[-a-zA-Z0-9+&@#/%=~_|]";

    public static final Pattern REGEX_URL = Pattern.compile(REGEX_URL_STR);
    /**
     * 邮箱正则
     */
    public static final String REGEX_EMAIL_STR =
        "^([a-z0-9A-Z]+[-|\\.]?)+[a-z0-9A-Z]@([a-z0-9A-Z]+(-[a-z0-9A-Z]+)?\\.)+[a-zA-Z]{2,}$";

    public static final Pattern REGEX_EMAIL = Pattern.compile(REGEX_EMAIL_STR);
    /**
     * 身份证正则
     */
    public static final String REGEX_IDENTITY_NO =
            "^[1-9]\\d{5}(18|19|20)\\d{2}((0[1-9])|(1[0-2]))(([0-2][1-9])|10|20|30|31)\\d{3}[0-9Xx]$";
    public static final String REGEX_IDENTITY_NO_MESSAGE = "身份证号码格式不正确";
    /**
     * 手机号码正则
     */
    public static final String REGEX_PHONE_NO_STR =
            "^\\d{11}$";
    public static final Pattern REGEX_PHONE_NO = Pattern.compile(REGEX_PHONE_NO_STR);
    public static final String REGEX_PHONE_NO_MESSAGE = "手机号码格式不正确";

    /**
     * 管理员默认登录密码
     */
    public static final String ADMIN_DEFAULT_PASSWORD = "12345678";

    /**
     * 超级管理员ID
     */
    public static final long ADMIN_DEFAULT_ID = 1;

    /**
     * description 正则匹配字符串
     *
     * @author chen wei
     * @date 2019/8/2
     */
    public static class ParamValidPatter {

        public static final String MOBILE = "1\\d{10}";
    }

    /**
     * description 后端角色
     * 
     * @author chen wei
     * @date 2019/11/12
     */
    public static class SysRoleCode {
        /**
         * 超级管理员
         */
        public static final String SYS_ROLE_ADMIN = "ADMIN";
        /**
         * 普通管理员
         */
        public static final String SYS_ROLE_OPERATOR = "OPERATOR";
        /**
         * 销售
         */
        public static final String SYS_ROLE_SALES_PERSON = "SALES";
        /**
         * 教务
         */
        public static final String SYS_ROLE_EDUCATION = "EDUCATION";
    }

}
