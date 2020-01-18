package com.arz.pmp.base.api.bo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

@Data
@ApiModel
public class CommonDataReq {

    @ApiModelProperty("记录ID")
    private Long id;

    @ApiModelProperty("登录密码")
    @Length(max = 16, min = 6)
    private String password;
}
