package com.arz.pmp.base.api.bo.user.front;

import javax.validation.constraints.*;

import com.arz.pmp.base.framework.commons.constants.Constants;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * description 学员注册信息类
 *
 * @author chen wei
 * @version 1.0
 *          <p>
 *          Copyright: Copyright (c) 2019
 *          </p>
 * @date 2019/11/14 18:06
 */
@ApiModel
@Data
public class UserRegistReq {

    @ApiModelProperty("学历Id")
    @Positive
    private Long educationId;

    @ApiModelProperty("姓名")
    @Pattern(regexp = "\\S{1,30}", message = "非空1-100位")
    @NotBlank
    private String userName;

    @ApiModelProperty("性别（0、女，1、男）")
    @NotNull
    private Boolean gender;

    @ApiModelProperty("民族")
    private String nationality;

    @ApiModelProperty("出生时间")
    @Positive
    @NotNull
    private Long birthday;

    @ApiModelProperty("联系电话")
    @NotEmpty
    @Pattern(regexp = Constants.REGEX_PHONE_NO_STR, message = Constants.REGEX_PHONE_NO_MESSAGE)
    private String phoneNo;

    @ApiModelProperty("固定电话")
    private String fixedTelephone;

    @ApiModelProperty("联系邮箱")
    private String email;

    @ApiModelProperty("备用邮箱")
    private String backupEmail;

    @ApiModelProperty("微信号码")
    private String wechatNo;

    @ApiModelProperty("QQ号码")
    private String qq;

    @ApiModelProperty("身份证号码")
    @NotEmpty
    @Pattern(regexp = Constants.REGEX_IDENTITY_NO, message = Constants.REGEX_IDENTITY_NO_MESSAGE)
    private String identityNo;

    @ApiModelProperty("邮编")
    private String zipCode;

    @ApiModelProperty("通讯地址")
    @NotEmpty
    private String userAddress;

    @ApiModelProperty("毕业时间")
    private Long graduationTime;

    @ApiModelProperty("毕业学校")
    private String graduationSchool;

    @ApiModelProperty("所学专业")
    private String schoolMajor;

    @ApiModelProperty("从事行业")
    private String industry;

    @ApiModelProperty("公司名称")
    private String comName;

    @ApiModelProperty("公司部门名称")
    private String comDepartment;

    @ApiModelProperty("公司职位名称")
    private String comPosition;
}
