package com.arz.pmp.base.api.bo.user.front;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

@ApiModel
@Data
public class CourseChoosingData {
    @ApiModelProperty("课程ID")
    @NotNull(message = "课程必选")
    @Positive(message = "课程必选")

    private Long id;
    @ApiModelProperty("顾问ID")
    @Positive(message = "课程顾问必选")
    @NotNull(message = "课程顾问必选")
    private Long adminId;
}
