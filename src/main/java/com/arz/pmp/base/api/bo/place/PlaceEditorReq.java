package com.arz.pmp.base.api.bo.place;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

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
public class PlaceEditorReq {

    @ApiModelProperty("教学点信息表ID")
    private Long placeId;

    @ApiModelProperty("名称")
    @Pattern(regexp = "\\S{1,100}", message = "非空1-100位")
    private String placeName;

    @ApiModelProperty("描述")
    @Length(max = 200)
    private String placeDesc;

    @ApiModelProperty("详细地址")
    @Length(max = 300)
    private String address;

    @ApiModelProperty("联系人名称")
    @Length(max = 30)
    private String contactName;

    @ApiModelProperty("联系手机/座机")
    @Length(max = 30)
    private String contactPhone;

    @ApiModelProperty("排序")
    @NotNull
    private Integer sort;

    @ApiModelProperty("是否启用")
    @NotNull
    private Boolean useOn;
}
