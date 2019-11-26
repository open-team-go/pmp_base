package com.arz.pmp.base.api.bo.course;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * description 课程检索
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
public class CourseSearchReq {

    @ApiModelProperty("关键字")
    private String keyWord;

    @ApiModelProperty("是否启用")
    private Boolean useOn;
}
