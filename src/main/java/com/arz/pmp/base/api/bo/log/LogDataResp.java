package com.arz.pmp.base.api.bo.log;

import com.arz.pmp.base.entity.PmpSystemLogEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel
public class LogDataResp extends PmpSystemLogEntity {

    @ApiModelProperty("管理员昵称")
    private String nickName;
}
