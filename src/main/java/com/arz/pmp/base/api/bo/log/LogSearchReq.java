package com.arz.pmp.base.api.bo.log;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * description 日志检索
 *
 * @author chen wei
 * @version 1.0
 * <p>
 * Copyright: Copyright (c) 2019
 * </p>
 * @date 2019/11/14 17:34
 */
@ApiModel
@Data
public class LogSearchReq {

    @ApiModelProperty("关键字")
    private String keyWord;

    @ApiModelProperty("管理员ID")
    private Long adminId;

    @ApiModelProperty("操作模块SYS_LOGIN_IN，SYS_ADMIN，SYS_PERMISSION，SYS_ROLE，SYS_TEACHING_PLACE，SYS_COURSE，SYS_ROOM，SYS_USER，SYS_LOG")
    private String optModule;

    @ApiModelProperty("操作类型UPDATE，ADD，DELETE，QUERY，EXPORT，IMPORT")
    private String optType;

    @ApiModelProperty("访问IP")
    private String ip;

    @ApiModelProperty("开始时间")
    private Long startTime;

    @ApiModelProperty("结束时间")
    private Long endTime;

}
