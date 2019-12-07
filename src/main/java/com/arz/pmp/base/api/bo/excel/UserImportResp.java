package com.arz.pmp.base.api.bo.excel;

import com.arz.pmp.base.entity.PmpUserEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

@Data
@ApiModel
public class UserImportResp {

    @ApiModelProperty("新增记录数")
    private int addCount;

    @ApiModelProperty("修改记录数")
    private int updateCount;

    @ApiModelProperty("导入失败记录")
    private List<PmpUserEntity> errorList;
}
