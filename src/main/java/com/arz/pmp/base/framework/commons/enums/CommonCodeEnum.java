package com.arz.pmp.base.framework.commons.enums;

/**
 * description 返回状态码
 *
 * @date 2019/11/11
 */
public enum CommonCodeEnum implements StatusCode {
    /**
     * 请求成功
     */
    SUCCESS("请求成功", "SUCCESS"),

    /**
     * 用户传递参数格式不正确
     */
    PARAM_ERROR("参数错误", "PARAM_ERROR"),

    PARAM_ERROR_LOGIN_USERNAME("用户名不存在", "PARAM_ERROR_LOGIN_USERNAME"),
    PARAM_ERROR_LOGIN_PASSWORD("登录密码错误", "PARAM_ERROR_LOGIN_PASSWORD"),
    PARAM_ERROR_USERNAME_MULTI("用户名重复", "PARAM_ERROR_USERNAME_MULTI"),
    PARAM_ERROR_ADMIN("超级管理员不支持修改、删除操作", "PARAM_ERROR_ADMIN"),

    /**
     * 访问权限异常
     */
    PERMISSION_ERROR("访问权限异常", "ERROR_PERMISSION"),
    /**
     * 访问权限异常
     */
    PERMISSION_ERROR_LOGIN("访问权限异常,未登录", "PERMISSION_ERROR_LOGIN"),

    /**
     * 业务错误，非正常操作，或者请求数据和系统数据冲突，系统数据错误等
     */
    BUSINESS_ERROR("业务异常", "BUSINESS_ERROR"),

    /**
     * 业务错误，非正常操作，或者请求数据和系统数据冲突，系统数据错误等
     */
    BUSINESS_ERROR_USER_NOT_EXIST("业务异常", "BUSINESS_ERROR_USER_NOT_EXIST"),

    /**
     * 系统错误，非程序数据错误，监控日志时，需要关注
     */
    SYSTEM_ERROR("系统异常", "SYSTEM_ERROR"),
    /**
     * 系统错误，EXCEL文件解析異常
     */
    SYSTEM_ERROR_EXCEL_PARSING_ERROR("EXCEL文件解析异常", "SYSTEM_ERROR_EXCEL_PARSING_ERROR"),

    /**
     * 系统错误，非程序数据错误，监控日志时，需要关注
     */
    SYSTEM_ERROR_ROMOTE_SERVICE_ERROR("远程服务错误", "SYSTEM_ERROR_ROMOTE_SERVICE_ERROR"),
    /**
     * 系统错误，非程序数据错误，监控日志时，需要关注
     */
    SYSTEM_ERROR_REDIS_ERROR("reids 服务错误", "SYSTEM_ERROR_REDIS_ERROR"),;
    private String code;
    private String msg;

    CommonCodeEnum(String msg, String code) {
        this.msg = msg;
        this.code = code;
    }

    @Override
    public String getCode() {
        return code;
    }

    @Override
    public String getMsg() {
        return msg;
    }
}
