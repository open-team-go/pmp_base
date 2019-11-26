package com.arz.pmp.base.framework.commons.exception;

import com.arz.pmp.base.framework.commons.enums.CommonCodeEnum;

/**
 * description 业务异常
 *
 * @author chen wei
 * @date 2019/11/11
 */
public class BusinessException extends BaseException {

    public BusinessException() {

    }

    public BusinessException(CommonCodeEnum statusCodeEnum) {
        super(statusCodeEnum);
    }

    public BusinessException(CommonCodeEnum statusCodeEnum, String message) {
        super(statusCodeEnum.getMsg());
        this.setCode(statusCodeEnum.getCode());
        this.setMsg(message);
    }

    public BusinessException(String code, String message) {
        super(code, message);
    }

}
