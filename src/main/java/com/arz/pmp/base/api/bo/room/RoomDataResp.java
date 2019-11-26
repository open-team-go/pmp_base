package com.arz.pmp.base.api.bo.room;

import com.arz.pmp.base.entity.*;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * description java类作用描述
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
public class RoomDataResp {

    @ApiModelProperty("班级ID")
    private Long roomId;

    @ApiModelProperty("班级信息")
    private PmpTeachingRoomEntity roomInfo;

    @ApiModelProperty("教务员信息")
    private PmpAdminEntity adminInfo;

    @ApiModelProperty("班级教学方式信息")
    private PmpTeachingTypeEntity typeInfo;

    @ApiModelProperty("教学点信息")
    private PmpTeachingPlaceEntity placeInfo;

    @ApiModelProperty("课程信息")
    private PmpCourseEntity courseInfo;

}
