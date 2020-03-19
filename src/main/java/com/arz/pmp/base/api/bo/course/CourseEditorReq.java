package com.arz.pmp.base.api.bo.course;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

/**
 * description 课程编辑类
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
public class CourseEditorReq {

    @ApiModelProperty("课程信息表ID")
    private Long courseId;

    @ApiModelProperty("课程名称")
    @Pattern(regexp = "\\S{1,100}",message = "非空1-100位")
    private String courseName;

    @ApiModelProperty("描述")
    @Length(max = 200)
    private String courseDesc;

    @ApiModelProperty("排序")
    @NotNull
    private Integer sort;

    @ApiModelProperty("是否启用")
    @NotNull
    private Boolean useOn;

    @ApiModelProperty("课程报名地址")
    private String applyUrl;
}
