package com.arz.pmp.base.framework.commons.exception;

import com.arz.pmp.base.framework.commons.enums.CommonCodeEnum;

/**
 * description 参数异常
 *
 * @author chen wei
 * @date 2019/11/11
 */
public class ParamException extends BaseException {

    public ParamException() {

    }

    public ParamException(CommonCodeEnum statusCodeEnum) {
        super(statusCodeEnum);
    }

    public ParamException(CommonCodeEnum statusCodeEnum, String message) {
        super(statusCodeEnum.getMsg());
        this.setCode(statusCodeEnum.getCode());
        this.setMsg(message);
    }

    public ParamException(String code, String message) {
        super(code, message);
    }

}
