package com.arz.pmp.base.api.bo.room;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Pattern;
import javax.validation.constraints.Positive;

/**
 * description 教学点编辑类
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
public class RoomEditorReq {

    @ApiModelProperty("开课班级信息表ID")
    @Positive
    private Long roomId;

    @ApiModelProperty("课程信息表ID")
    @Positive
    private Long courseId;

    @ApiModelProperty("教学点信息表ID")
    @Positive
    private Long placeId;

    @ApiModelProperty("教学点信息表ID")
    @Positive
    private Long typeId;

    @ApiModelProperty("教务员ID")
    @Positive
    private Long adminId;

    @ApiModelProperty("名称")
    @Pattern(regexp = "\\S{1,100}", message = "非空1-100位")
    private String roomName;

    @ApiModelProperty("描述")
    @Length(max = 200)
    private String roomDesc;

    @ApiModelProperty("详细地址")
    @Length(max = 300)
    private String roomAddress;

    @ApiModelProperty("教师名称")
    @Length(max = 30)
    private String teacherName;

    @ApiModelProperty("教师联系方式")
    @Length(max = 30)
    private String teacherPhone;

    @ApiModelProperty("开班开始时间")
    private Long startTime;

    @ApiModelProperty("开班结束时间")
    private Long endTime;

    @ApiModelProperty("排序")
    private Integer sort;

    @ApiModelProperty("是否使用")
    private Boolean useOn;
}
