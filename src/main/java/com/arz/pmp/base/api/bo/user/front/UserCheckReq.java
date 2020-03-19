package com.arz.pmp.base.api.bo.user.front;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;

@Api
@Data
public class UserCheckReq {

    @ApiModelProperty("登录名")
    @Pattern(regexp = "\\S{1,30}", message = "非空1-30位")
    @NotBlank
    private String loginName;

    @ApiModelProperty("登录密码")
    @NotEmpty
    @Length(min = 6, max = 16, message = "密码长度限制6-16位")
    private String loginPassword;
}
