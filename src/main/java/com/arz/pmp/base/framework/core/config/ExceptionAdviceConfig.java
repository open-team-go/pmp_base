package com.arz.pmp.base.framework.core.config;

import com.arz.pmp.base.framework.commons.constants.Constants;
import com.arz.pmp.base.framework.commons.enums.CommonCodeEnum;
import com.arz.pmp.base.framework.commons.exception.BaseException;
import com.arz.pmp.base.framework.commons.response.RestResponse;
import com.google.common.base.Strings;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;
import org.springframework.beans.ConversionNotSupportedException;
import org.springframework.beans.TypeMismatchException;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.http.converter.HttpMessageNotWritableException;
import org.springframework.validation.BindException;
import org.springframework.validation.FieldError;
import org.springframework.web.HttpMediaTypeNotAcceptableException;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingPathVariableException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.multipart.support.MissingServletRequestPartException;
import org.springframework.web.servlet.NoHandlerFoundException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.ConstraintViolationException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

    private static final Logger LOGGER = LoggerFactory.getLogger(ExceptionAdviceConfig.class);
    private static final Map<Class<?>, Integer> EXCEPTIONS = new HashMap<>(16);

    static {
        EXCEPTIONS.put(HttpRequestMethodNotSupportedException.class, HttpServletResponse.SC_METHOD_NOT_ALLOWED);
        EXCEPTIONS.put(HttpMediaTypeNotSupportedException.class, HttpServletResponse.SC_UNSUPPORTED_MEDIA_TYPE);
        EXCEPTIONS.put(HttpMediaTypeNotAcceptableException.class, HttpServletResponse.SC_NOT_ACCEPTABLE);
        EXCEPTIONS.put(MissingPathVariableException.class, HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        EXCEPTIONS.put(MissingServletRequestParameterException.class, HttpServletResponse.SC_BAD_REQUEST);
        EXCEPTIONS.put(ServletRequestBindingException.class, HttpServletResponse.SC_BAD_REQUEST);
        EXCEPTIONS.put(ConversionNotSupportedException.class, HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        EXCEPTIONS.put(TypeMismatchException.class, HttpServletResponse.SC_BAD_REQUEST);
        EXCEPTIONS.put(HttpMessageNotReadableException.class, HttpServletResponse.SC_BAD_REQUEST);
        EXCEPTIONS.put(HttpMessageNotWritableException.class, HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        EXCEPTIONS.put(MethodArgumentNotValidException.class, HttpServletResponse.SC_BAD_REQUEST);
        EXCEPTIONS.put(MissingServletRequestPartException.class, HttpServletResponse.SC_BAD_REQUEST);
        EXCEPTIONS.put(BindException.class, HttpServletResponse.SC_BAD_REQUEST);
        EXCEPTIONS.put(NoHandlerFoundException.class, HttpServletResponse.SC_NOT_FOUND);
        EXCEPTIONS.put(IllegalArgumentException.class, HttpServletResponse.SC_BAD_REQUEST);
        EXCEPTIONS.put(MethodArgumentTypeMismatchException.class, HttpServletResponse.SC_BAD_REQUEST);
    }


    @ExceptionHandler(value = Exception.class)
    public Object expHandle(Exception e, HttpServletRequest request, HandlerMethod handler) {

        String bodyString = null;
        String id = MDC.get(Constants.REQUEST_ID);

        RestResponse rep = null;
        Integer status;
        StringBuilder sb = new StringBuilder();
        if (e instanceof ConstraintViolationException) {
            String errorMessage = ((ConstraintViolationException) e).getConstraintViolations()
                    .iterator()
                    .next()
                    .getMessage();
            status = HttpServletResponse.SC_BAD_REQUEST * 100;
            rep = RestResponse.error(String.valueOf(status),errorMessage);

        } else if (e instanceof BaseException) {
            //output
            BaseException exception = (BaseException) e;
            rep = RestResponse.error(exception.getCode(),exception.getMsg());
            sb.append("current request app exception failed :")
                    .append(StringUtils.LF)
                    .append("url:")
                    .append(requestUrl(request))
                    .append(StringUtils.LF)
                    .append("request body:")
                    .append(bodyString)
                    .append(StringUtils.LF)
                    .append(String.format("response code:%s > msg: %s", exception.getCode(), exception.getMessage()));

            LOGGER.warn(sb.toString());

        } else if (e instanceof MethodArgumentNotValidException) {
            //output
            return methodArgumentHandle((MethodArgumentNotValidException) e, request, handler);
        } else {
            sb.append("current request failed :")
                    .append(StringUtils.LF)
                    .append("url:")
                    .append(requestUrl(request))
                    .append(StringUtils.LF)
                    .append("request body:")
                    .append(bodyString)
                    .append(StringUtils.LF)
                    .append(String.format("request id:%s > stack: %s", id, ExceptionUtils.getStackTrace(e)));
            LOGGER.error(sb.toString());
            status = EXCEPTIONS.get(e.getClass());
            String code = status == null ? CommonCodeEnum.SYSTEM_ERROR.getCode() : String.valueOf(status);
            rep = RestResponse.error(code,e.getMessage());
        }
        return exceptionResolver(rep);

    }

    private String requestUrl(HttpServletRequest request) {
        if (!Strings.isNullOrEmpty(request.getQueryString())) {
            return String.format("%s?%s", request.getRequestURL(), request.getQueryString());
        }
        return request.getRequestURL().toString();
    }

    private Object exceptionResolver(RestResponse result) {
            return result;
    }

    public Object methodArgumentHandle(MethodArgumentNotValidException e, HttpServletRequest request,
                                       HandlerMethod handler) {
        List<FieldError> errorList = e.getBindingResult().getFieldErrors();
        StringBuilder message = new StringBuilder();
        for (FieldError error : errorList) {
            message.append(error.getField()).append(":").append(error.getDefaultMessage()).append(";");
        }
        // 打印堆栈信息
        LOGGER.warn("HERE IS MethodArgumentNotValidException===={}", ExceptionUtils.getStackTrace(e));
        return exceptionResolver(RestResponse.error(CommonCodeEnum.PARAM_ERROR.getCode(), message.toString()));
    }

}
