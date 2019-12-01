package com.arz.pmp.base.api.bo.excel;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel
public class UserImportResp {

    @ApiModelProperty("新增记录数")
    private int addCount;

    @ApiModelProperty("修改记录数")
    private int updateCount;
}
