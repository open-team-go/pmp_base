package com.arz.pmp.base.api.bo.user.front;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@ApiModel
@Data
public class CourseListData {

    @ApiModelProperty("用户选课表ID")
    private Long userRefCourseId;

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

    @ApiModelProperty("课程报名申请页面地址")
    private String applyUrl;
}
