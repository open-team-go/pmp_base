package com.arz.pmp.base.api.bo.user;

import com.arz.pmp.base.entity.*;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.math.BigDecimal;

/**
 * description 学员信息类
 *
 * @author chen wei
 * @version 1.0
 *          <p>
 *          Copyright: Copyright (c) 2019
 *          </p>
 * @date 2019/11/15 17:18
 */
@ApiModel
@Data
public class UserDataResp {

    @ApiModelProperty("用户选课表ID")
    private Long userRefCourseId;

    @ApiModelProperty("用户表ID")
    private Long userId;

    @ApiModelProperty("学员登录用户名")
    private String loginName;

    @ApiModelProperty("班级名称")
    private String roomName;

    @ApiModelProperty("班级Id")
    private Long roomId;

    @ApiModelProperty("班级教务名")
    private String educationAdminName;

    @ApiModelProperty("课程顾问名称")
    private String salesAdminName;

    @ApiModelProperty("课程顾问ID")
    private Long adminId;

    @ApiModelProperty("课程名称")
    private String courseName;

    @ApiModelProperty("课程Id")
    private Long courseId;

    @ApiModelProperty("来源Id")
    private Long resourceId;

    @ApiModelProperty("来源名称")
    private String resourceName;

    @ApiModelProperty("推荐人")
    private String recommendName;

    @ApiModelProperty("咨询日期")
    private Long consultationTime;

    @ApiModelProperty("咨询城市")
    private String consultationCity;

    @ApiModelProperty("学历")
    private String educationName;

    @ApiModelProperty("学历Id")
    private Long educationId;

    @ApiModelProperty("付款方式Id")
    private Long payId;

    @ApiModelProperty("付款方式")
    private String payName;

    @ApiModelProperty("姓名")
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

    @ApiModelProperty("是否含票")
    private Boolean invoiceOn;

    @ApiModelProperty("发票抬头")
    private String invoiceHeader;

    @ApiModelProperty("发票税号")
    private String invoiceCode;

    @ApiModelProperty("学员类型（1、内部学员，2、外部续证学员，3、联系中未报名）")
    private Integer userType;

    @ApiModelProperty("结业状态（0、未知，1、通过，2、未通过，3、缓考，4、缓读）")
    private Integer graduationStatus;

    @ApiModelProperty("PMI ID号")
    private String certNo;

    @ApiModelProperty("英文网站用户名")
    private String certEnName;

    @ApiModelProperty("英文网站密码")
    private String certEnPasw;

    @ApiModelProperty("中文网站用户名")
    private String certCnName;

    @ApiModelProperty("中文网站密码")
    private String certCnPasw;

    @ApiModelProperty("考试时间")
    private Long examinationTime;

    @ApiModelProperty("付款金额")
    private BigDecimal payTotal;

    @ApiModelProperty("付款时间")
    private Long payTime;

    @ApiModelProperty("付款备注")
    private String payRemarks;

    @ApiModelProperty("备注")
    private String remark;

    @ApiModelProperty("创建时间")
    private Long createTime;

    @ApiModelProperty("修改时间")
    private Long updateTime;

    @ApiModelProperty("选班时间")
    private Long roomChooseTime;
}
