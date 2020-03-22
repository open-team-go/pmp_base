package com.arz.pmp.base.api.bo.user.front;

import com.arz.pmp.base.framework.commons.constants.Constants;
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
    @Pattern(regexp = "\\S{1,30}", message = "非空1-30位")
    @NotBlank(message = "姓名格式不正确")
    private String userName;

    @ApiModelProperty("身份证号码")
    @NotEmpty(message = Constants.REGEX_IDENTITY_NO_MESSAGE)
    @Pattern(regexp = Constants.REGEX_IDENTITY_NO, message = Constants.REGEX_IDENTITY_NO_MESSAGE)
    private String identityNo;

    @ApiModelProperty("性别（0、女，1、男）")
    private Boolean gender;

    @ApiModelProperty("民族")
    private String nationality;

    @ApiModelProperty("出生时间")
    private Long birthday;

    @ApiModelProperty("学历")
    private String educationName;

    @ApiModelProperty("学历Id")
    private Long educationId;

    @ApiModelProperty("毕业时间")
    private Long graduationTime;

    @ApiModelProperty("毕业学校")
    private String graduationSchool;

    @ApiModelProperty("所学专业")
    private String schoolMajor;

    @ApiModelProperty("从事行业")
    private String industry;

    @ApiModelProperty("公司部门名称")
    private String comDepartment;

    @ApiModelProperty("公司职位名称")
    private String comPosition;

    @ApiModelProperty("手机号码")
    @Pattern(regexp = Constants.REGEX_PHONE_NO_STR, message = Constants.REGEX_PHONE_NO_MESSAGE)
    @NotEmpty(message = Constants.REGEX_PHONE_NO_MESSAGE)
    private String phoneNo;

    @ApiModelProperty("联系邮箱")
    private String email;

    @ApiModelProperty("备用邮箱")
    private String backupEmail;

    @ApiModelProperty("微信号码")
    private String wechatNo;

    @ApiModelProperty("QQ号码")
    private String qq;

    @ApiModelProperty("固定电话")
    private String fixedTelephone;

    @ApiModelProperty("备注")
    private String remark;

}
