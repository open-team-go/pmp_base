package com.arz.pmp.base.api.bo.user;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * description 学员检索
 *
 * @author chen wei
 * @version 1.0
 *          <p>
 *          Copyright: Copyright (c) 2019
 *          </p>
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

    @ApiModelProperty("身份证号")
    private String identityNo;

    @ApiModelProperty("学员类型（1、内部学员，2、外部续证学员，3、联系中未报名）")
    private Integer userType;

    @ApiModelProperty("注册开始时间")
    private Long startTime;

    @ApiModelProperty("注册结束时间")
    private Long endTime;

    @ApiModelProperty("是否开票")
    private Boolean invoiceOn;

    @ApiModelProperty("PMI ID号")
    private String certNo;

    @ApiModelProperty("职位")
    private String comPosition;

    @ApiModelProperty("公司名称")
    private String comName;

    @ApiModelProperty("付款金额")
    private Double payTotal;

    @ApiModelProperty("结业状态（0、未知，1、通过，2、未通过，3、缓考，4、缓读）")
    private Integer graduationStatus;
}
