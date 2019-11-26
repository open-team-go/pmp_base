package com.arz.pmp.base.framework.commons.exception.builder;

import com.arz.pmp.base.framework.commons.enums.CommonCodeEnum;
import com.arz.pmp.base.framework.commons.enums.StatusCode;
import com.arz.pmp.base.framework.commons.exception.BaseException;
import com.arz.pmp.base.framework.commons.exception.BusinessException;
import com.arz.pmp.base.framework.commons.exception.ParamException;
import com.arz.pmp.base.framework.commons.exception.SystemException;

/**
 * description 异常
 *
 * @author chen wei
 * @date 2019/11/11
 */
public class ExceptionBuilder {

    public static BaseException build(StatusCode statusCode) {
        return createException(statusCode.getCode(), statusCode.getMsg());

    }

    public static BaseException build(StatusCode statusCode, String msg) {
        return createException(statusCode.getCode(), msg);

    }

    public static BaseException build(String code, String msg) {
        return createException(code, msg);

    }

    private static BaseException createException(String code, String msg) {

        if (code.indexOf(CommonCodeEnum.PARAM_ERROR.getCode()) != -1) {
            return new ParamException(code, msg);
        } else if (code.indexOf(CommonCodeEnum.SYSTEM_ERROR.getCode()) != -1) {
            return new SystemException(code, msg);
        } else if (code.indexOf(CommonCodeEnum.BUSINESS_ERROR.getCode()) != -1) {
            return new BusinessException(code, msg);
        } else if (code.indexOf(CommonCodeEnum.PERMISSION_ERROR.getCode()) != -1) {
            return new PermissionException(code, msg);
        }
        return new BaseException(code, msg);
    }
}
