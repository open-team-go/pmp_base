package com.arz.pmp.base.framework.commons.response;

import com.alibaba.fastjson.annotation.JSONField;
import com.arz.pmp.base.framework.commons.enums.CommonCodeEnum;
import com.arz.pmp.base.framework.commons.exception.SystemException;
import com.arz.pmp.base.framework.commons.exception.builder.ExceptionBuilder;
import com.fasterxml.jackson.annotation.JsonIgnore;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * description 返回消息体类
 * 
 * @author chen wei
 * @date 2019/11/11
 */
@Data
@ApiModel
public class RestResponse<T> {

    @ApiModelProperty("返回信息状态")
    private ResponseHeader header = new ResponseHeader();

    @ApiModelProperty("返回消息主题")
    private T body;

    private RestResponse() {}

    /**
     * 请求成功
     *
     * @return
     */
    public static RestResponse sucess() {
        return builder().buildWithStatusCodeEnum(CommonCodeEnum.SUCCESS).build();
    }

    /**
     * 请求成功
     *
     * @param data
     * @param <T>
     * @return
     */
    public static <T> RestResponse success(T data) {
        return builder().buildWithStatusCodeEnum(CommonCodeEnum.SUCCESS).buildWithData(data).build();
    }

    /**
     * 请求成功
     *
     * @return
     */
    public static RestResponse success() {
        return builder().buildWithStatusCodeEnum(CommonCodeEnum.SUCCESS).build();
    }

    /**
     * 返回错误
     *
     * @param statusCodeEnum
     * @return
     */
    public static RestResponse error(CommonCodeEnum statusCodeEnum) {
        return builder().buildWithStatusCodeEnum(statusCodeEnum).build();
    }

    /**
     * 返回错误
     *
     * @param code
     * @param msg
     * @return
     */
    public static RestResponse error(String code, String msg) {
        return builder().buildWithCode(code).buildWithMsg(msg).build();
    }

    public void assertSuccess() {
        if (this.getHeader() == null) {
            throw new SystemException(CommonCodeEnum.SYSTEM_ERROR_ROMOTE_SERVICE_ERROR);
        }
        if (!CommonCodeEnum.SUCCESS.getCode().equals(this.getHeader().getCode())) {
            throw ExceptionBuilder.build(this.getHeader().getCode(), this.getHeader().getMsg());
        }
    }

    public boolean confirmSuccess() {
        if (this.getHeader() == null) {
            return false;
        }
        return CommonCodeEnum.SUCCESS.getCode().equals(this.getHeader().getCode());
    }

    @JsonIgnore
    @JSONField(serialize = false)
    public T getBodyWithSuccess() {
        assertSuccess();
        return this.getBody();
    }

    private static RestRequestBuilder builder() {
        return new RestRequestBuilder();
    }

    private static class RestRequestBuilder<T> {
        private RestResponse<T> restResponse = new RestResponse();

        private RestRequestBuilder buildWithStatusCodeEnum(CommonCodeEnum statusCodeEnum) {
            restResponse.header.setCode(statusCodeEnum.getCode());
            restResponse.header.setMsg(statusCodeEnum.getMsg());
            return this;
        }

        private RestRequestBuilder buildWithCode(String code) {
            restResponse.header.setCode(code);
            return this;
        }

        private RestRequestBuilder buildWithMsg(String msg) {
            restResponse.header.setMsg(msg);
            return this;
        }

        private RestRequestBuilder<T> buildWithData(T data) {
            restResponse.body = data;
            return this;
        }

        public RestResponse<T> build() {
            return restResponse;
        }

    }

}
