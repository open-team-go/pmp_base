package com.arz.pmp.base.api.bo.user;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel
public class UsersStatisticsResp {

    @ApiModelProperty("今日新增选课数")
    private Integer todayIncrement;

    @ApiModelProperty("未分配课程顾问选课数")
    private Integer noSalesAdmin;

    @ApiModelProperty("今日新增选班数")
    private Integer todayRoomChoosing;

    @ApiModelProperty("未分配班级选课数")
    private Integer noEducationAdmin;
}
