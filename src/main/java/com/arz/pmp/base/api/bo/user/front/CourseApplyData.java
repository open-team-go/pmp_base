package com.arz.pmp.base.api.bo.user.front;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;

@ApiModel
@Data
public class CourseApplyData {

    @ApiModelProperty("选课信息表ID")
    @NotNull
    private Long userRefCourseId;

    @ApiModelProperty("考试报名信息")
    @NotNull
    private String htmlContent;
}
