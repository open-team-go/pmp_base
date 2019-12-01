package com.arz.pmp.base.api.bo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel
public class CommonDataReq {

    @ApiModelProperty("记录ID")
    private Long id;
}
