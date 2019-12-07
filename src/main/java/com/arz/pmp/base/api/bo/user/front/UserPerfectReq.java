package com.arz.pmp.base.api.bo.user.front;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;

@Api
@Data
public class UserPerfectReq {

    @ApiModelProperty("姓名")
    @Pattern(regexp = "\\S{1,30}", message = "非空1-100位")
    @NotBlank
    private String userName;

    @ApiModelProperty("身份证号码")
    @NotEmpty
    private String identityNo;

}
