package com.arz.pmp.base.api.bo.user;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * description 学员检索
 *
 * @author chen wei
 * @version 1.0
 * <p>
 * Copyright: Copyright (c) 2019
 * </p>
 * @date 2019/11/14 17:34
 */
@ApiModel
@Data
public class UserSearchReq {

    @ApiModelProperty("关键字")
    private String keyWord;

    @ApiModelProperty("教务员ID")
    private Long educationAdminId;

    @ApiModelProperty("顾问员ID")
    private Long salesAdminId;

    @ApiModelProperty("课程信息表ID")
    private Long courseId;

    @ApiModelProperty("教学点信息表ID")
    private Long placeId;

    @ApiModelProperty("支付类型信息表ID")
    private Long typeId;

    @ApiModelProperty("学历信息表ID")
    private Long educationId;

    @ApiModelProperty("班级表ID")
    private Long roomId;

    @ApiModelProperty("性别")
    private Boolean gender;

    @ApiModelProperty("邮箱")
    private String email;

    @ApiModelProperty("微信号")
    private String wechatNo;

    @ApiModelProperty("QQ")
    private String qq;

    @ApiModelProperty("身份证号")
    private String identityNo;

    @ApiModelProperty("学员类型")
    private Integer userType;

    @ApiModelProperty("注册开始时间")
    private String startTime;

    @ApiModelProperty("注册结束时间")
    private Long endTime;
}
