package com.arz.pmp.base.framework.commons.response;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * description 返回信息头信息类
 *
 * @author chen wei
 * @date 2019/11/11
 */
@Data
@ApiModel
public class ResponseHeader {

    @ApiModelProperty("返回状态码")
    private String code;
    @ApiModelProperty("返回信息")
    private String msg;

}
