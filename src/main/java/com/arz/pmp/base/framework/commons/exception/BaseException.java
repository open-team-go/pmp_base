package com.arz.pmp.base.framework.commons.exception;

import com.arz.pmp.base.framework.commons.enums.CommonCodeEnum;

import lombok.Data;

/**
 * description 基础异常
 *
 * @date 2019/11/11
 */
@Data
public class BaseException extends RuntimeException {
    /**
     * 错误消息信息
     */
    private String msg;
    /**
     * 错误代码
     */
    private String code;

    public BaseException() {

    }

    public BaseException(CommonCodeEnum statusCodeEnum) {
        super(statusCodeEnum.getMsg());
        this.setCode(statusCodeEnum.getCode());
        this.setMsg(statusCodeEnum.getMsg());
    }

    public BaseException(String message) {
        super(message);
    }

    public BaseException(CommonCodeEnum statusCodeEnum, String message) {
        super(statusCodeEnum.getMsg());
        this.setCode(statusCodeEnum.getCode());
        this.setMsg(message);
    }

    public BaseException(String code, String message) {
        super(message);
        this.setCode(code);
        this.setMsg(message);
    }

    public BaseException(String message, Throwable cause) {
        super(message, cause);
    }

    /**
     * 用指定原因构造一个新的异常
     *
     * @param cause
     */
    public BaseException(Throwable cause) {
        super(cause);
    }

}
