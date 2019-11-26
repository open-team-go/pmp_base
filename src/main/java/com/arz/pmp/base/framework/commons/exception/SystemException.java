package com.arz.pmp.base.framework.commons.exception;

import com.arz.pmp.base.framework.commons.enums.CommonCodeEnum;

/**
 * description 系统异常
 *
 * @author chen wei
 * @date 2019/11/11
 */
public class SystemException extends BaseException {

    public SystemException() {

    }

    public SystemException(CommonCodeEnum statusCodeEnum) {
        super(statusCodeEnum);
    }

    public SystemException(CommonCodeEnum statusCodeEnum, String message) {
        super(statusCodeEnum.getMsg());
        this.setCode(statusCodeEnum.getCode());
        this.setMsg(message);
    }

    public SystemException(String code, String message) {
        super(code, message);
    }

}
