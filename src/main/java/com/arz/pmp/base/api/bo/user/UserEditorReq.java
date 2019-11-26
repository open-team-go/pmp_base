package com.arz.pmp.base.api.bo.user;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.Pattern;
import javax.validation.constraints.Positive;

/**
 * description 学员编辑类
 *
 * @author chen wei
 * @version 1.0
 * <p>
 * Copyright: Copyright (c) 2019
 * </p>
 * @date 2019/11/14 18:06
 */
@ApiModel
@Data
public class UserEditorReq {

    @ApiModelProperty("用户表ID")
    private Long userId;

    @ApiModelProperty("班级ID")
    @Positive
    private Long roomId;

    @ApiModelProperty("课程顾问ID")
    @Positive
    private Long adminId;

    @ApiModelProperty("学历Id")
    @Positive
    private Long educationId;

    @ApiModelProperty("付款方式信息表ID")
    @Positive
    private Long payId;

    @ApiModelProperty("姓名")
    @Pattern(regexp = "\\S{1,30}", message = "非空1-100位")
    private String userName;

    @ApiModelProperty("性别（0、女，1、男）")
    private Boolean gender;

    @ApiModelProperty("民族")
    private String nationality;

    @ApiModelProperty("出生时间")
    private Long birthday;

    @ApiModelProperty("联系电话")
    private String phoneNo;

    @ApiModelProperty("联系邮箱")
    private String email;

    @ApiModelProperty("备用邮箱")
    private String backupEmail;

    @ApiModelProperty("微信号码")
    private String wechatNo;

    @ApiModelProperty("QQ号码")
    private String qq;

    @ApiModelProperty("身份证号码")
    private String identityNo;

    @ApiModelProperty("邮编")
    private String zipCode;

    @ApiModelProperty("通讯地址")
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

    @ApiModelProperty("发票抬头")
    private String invoiceHeader;

    @ApiModelProperty("发票税号")
    private String invoiceCode;

    @ApiModelProperty("学员类型（1、内部学员，2、外部续证学员，3、联系中未报名）")
    private Integer userType;

    @ApiModelProperty("课程ID")
    private Long courseId;

    @ApiModelProperty("结业状态（0、未知，1、通过，2、未通过，3、缓考，4、缓读）")
    private Integer graduationStatus;

    @ApiModelProperty("证书号")
    private String certNo;

    @ApiModelProperty("证书用户名")
    private String certUserName;

    @ApiModelProperty("证书密码")
    private String certPassword;

    @ApiModelProperty("备注")
    private String remark;
}
