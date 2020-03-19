package com.arz.pmp.base.framework.core.config;

import com.arz.pmp.base.framework.commons.enums.CommonCodeEnum;
import com.arz.pmp.base.framework.commons.exception.BaseException;
import com.arz.pmp.base.framework.commons.exception.BusinessException;
import com.arz.pmp.base.framework.commons.exception.ParamException;
import com.arz.pmp.base.framework.commons.exception.SystemException;
import com.arz.pmp.base.framework.commons.response.RestResponse;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.multipart.MultipartException;

import javax.servlet.ServletException;
import java.sql.SQLException;
import java.util.List;

/**
 * description: REST异常处理
 *
 * @Author: chen wei
 * @CreateDate: 2019/7/2 15:08
 * @UpdateDate: 2019/7/2 15:08
 * @UpdateRemark: The modified content
 * @Version: 1.0
 *           <p>
 *           Copyright: Copyright (c) 2019
 *           </p>
 */
@RestControllerAdvice
public class ExceptionAdviceConfig {

    private static final Logger logger = LoggerFactory.getLogger(ExceptionAdviceConfig.class);

    /**
     * description 系统自定义业务异常
     *
     * @param e
     * @return com.yug.commons.utils.webresult.WebResult
     * @author chen wei
     * @date 2019/7/13
     */
    @ExceptionHandler(value = BusinessException.class)
    public RestResponse businessExpHandle(BusinessException e) {
        // 打印堆栈信息
        logger.error("HERE IS EXCEPTION===={}", e);
        return RestResponse.error(e.getCode(), e.getMsg());
    }

    /**
     * description 系统自定义底层异常
     *
     * @param e
     * @return com.yug.commons.utils.webresult.WebResult
     * @author chen wei
     * @date 2019/7/13
     */
    @ExceptionHandler(value = SystemException.class)
    public RestResponse systemExpHandle(SystemException e) {

        // 打印堆栈信息
        logger.error("HERE IS EXCEPTION===={}", e);
        return RestResponse.error(e.getCode(), e.getMsg());
    }

    /**
     * description 系统自定义底层异常
     *
     * @param e
     * @return com.yug.commons.utils.webresult.WebResult
     * @author chen wei
     * @date 2019/7/13
     */
    @ExceptionHandler(value = ParamException.class)
    public RestResponse paramExpHandle(ParamException e) {

        // 打印堆栈信息
        logger.error("HERE IS EXCEPTION===={}", e);
        return RestResponse.error(e.getCode(), e.getMsg());
    }

    /**
     * description validation异常
     *
     * @param e
     * @return com.yug.commons.utils.result.WebResult
     * @author chen wei
     * @date 2019/7/13
     */
    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    public RestResponse methodArgumentHandle(MethodArgumentNotValidException e) {
        List<FieldError> errorList = e.getBindingResult().getFieldErrors();
        StringBuilder message = new StringBuilder();
        for (FieldError error : errorList) {
            message.append(error.getField()).append(":").append(error.getDefaultMessage()).append(";");
        }
        // 打印堆栈信息
        logger.error("HERE IS EXCEPTION===={}", e);

        return RestResponse.error(CommonCodeEnum.PARAM_ERROR.getCode(), message.toString());
    }

    /**
     * description db异常
     *
     * @param e
     * @return com.yug.commons.utils.result.WebResult
     * @author chen wei
     * @date 2019/7/13
     */
    @ExceptionHandler(value = SQLException.class)
    public RestResponse sqlExpHandle(SQLException e) {
        String message = e.getMessage();
        // 打印堆栈信息
        logger.error("HERE IS EXCEPTION===={}", e);
        return RestResponse.error(CommonCodeEnum.SYSTEM_ERROR.getCode(), message);
    }

    /**
     * description 请求参数非法无法封装异常
     *
     * @param e
     * @return com.yug.commons.utils.result.WebResult
     * @author chen wei
     * @date 2019/7/13
     */
    @ExceptionHandler(value = JsonProcessingException.class)
    public RestResponse jsonReqExpHandle(JsonProcessingException e) {
        String message = e.getMessage();
        // 打印堆栈信息
        logger.error("HERE IS EXCEPTION===={}", e);
        return RestResponse.error(CommonCodeEnum.BUSINESS_ERROR.getCode(), message);
    }

    /**
     * description 请求参数非法无法封装异常
     *
     * @param e
     * @return com.yug.commons.utils.result.WebResult
     * @author chen wei
     * @date 2019/7/13
     */
    @ExceptionHandler(value = ServletException.class)
    public RestResponse servletExpHandle(ServletException e) {
        String message = e.getMessage();
        // 打印堆栈信息
        logger.error("HERE IS EXCEPTION===={}", e);

        return RestResponse.error(CommonCodeEnum.PARAM_ERROR.getCode(), message);
    }

    /**
     * description Multipart异常
     *
     * @param e
     * @return com.yug.commons.utils.result.WebResult
     * @author chen wei
     * @date 2019/7/13
     */
    @ExceptionHandler(value = MultipartException.class)
    public RestResponse multipartExpHandle(MultipartException e) {
        String message = e.getMessage();
        // 打印堆栈信息
        logger.error("HERE IS EXCEPTION===={}", e);
        return RestResponse.error(CommonCodeEnum.PARAM_ERROR.getCode(), message);
    }

    /**
     * description 请求参数非法无法封装异常
     *
     * @param e
     * @return com.yug.commons.utils.result.WebResult
     * @author chen wei
     * @date 2019/7/13
     */
    @ExceptionHandler(value = HttpMessageNotReadableException.class)
    public RestResponse httpMessageExpHandle(HttpMessageNotReadableException e) {
        String message = e.getMessage();
        // 打印堆栈信息
        logger.error("HERE IS EXCEPTION===={}", e);
        return RestResponse.error(CommonCodeEnum.PARAM_ERROR.getCode(), message);
    }

    @ExceptionHandler(value = Exception.class)
    public RestResponse<String> expHandle(Exception e) {

        String message = e.getMessage();
        // 打印堆栈信息
        logger.error("HERE IS EXCEPTION===={}", e);
        if (e instanceof BaseException) {
            BaseException ex = (BaseException)e;
            return RestResponse.error(ex.getCode(), ex.getMsg());
        }
        return RestResponse.error(CommonCodeEnum.SYSTEM_ERROR.getCode(), message);
    }
}
