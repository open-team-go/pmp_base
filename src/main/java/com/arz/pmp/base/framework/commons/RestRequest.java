package com.arz.pmp.base.framework.commons;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

/**
 * description 请求体信息
 * 
 * @author chen wei
 * @date 2019/11/11
 */
@Data
@ApiModel
public class RestRequest<T> {

    private RestRequest() {

    }

    @ApiModelProperty("消息头部")
    @NotNull
    @Valid
    private RequestHeader header;

    @ApiModelProperty("内容")
    @Valid
    @NotNull
    private T body;

}
