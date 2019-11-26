package com.arz.pmp.base.framework.commons.exception.builder;

import com.arz.pmp.base.framework.commons.enums.CommonCodeEnum;
import com.arz.pmp.base.framework.commons.exception.BaseException;

/**
 * description 参数异常
 *
 * @author chen wei
 * @date 2019/11/11
 */
public class PermissionException extends BaseException {

    public PermissionException() {

    }

    public PermissionException(CommonCodeEnum statusCodeEnum) {
        super(statusCodeEnum);
    }

    public PermissionException(CommonCodeEnum statusCodeEnum, String message) {
        super(statusCodeEnum.getMsg());
        this.setCode(statusCodeEnum.getCode());
        this.setMsg(message);
    }

    public PermissionException(String code, String message) {
        super(code, message);
    }

}
