package com.arz.pmp.base.api.bo.user.front;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@ApiModel
@Data
public class UserPerfectData {

    @ApiModelProperty("姓名")
    private String userName;

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

    @ApiModelProperty("身份证号码")
    private String identityNo;

    @ApiModelProperty("从事行业")
    private String industry;

    @ApiModelProperty("公司部门名称")
    private String comDepartment;

    @ApiModelProperty("公司职位名称")
    private String comPosition;

    @ApiModelProperty("手机号码")
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
