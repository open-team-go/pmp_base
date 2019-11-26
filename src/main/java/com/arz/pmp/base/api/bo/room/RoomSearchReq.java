package com.arz.pmp.base.api.bo.room;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * description 教学点检索
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
public class RoomSearchReq {

    @ApiModelProperty("关键字")
    private String keyWord;

    @ApiModelProperty("是否启用")
    private Boolean useOn;

    @ApiModelProperty("课程信息表ID")
    private Long courseId;

    @ApiModelProperty("教学点信息表ID")
    private Long placeId;

    @ApiModelProperty("教学点信息表ID")
    private Long typeId;

    @ApiModelProperty("教务员ID")
    private Long adminId;

    @ApiModelProperty("开班开始时间")
    private Long startTime;

    @ApiModelProperty("开班结束时间")
    private Long endTime;
}
