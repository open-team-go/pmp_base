package com.arz.pmp.base.api.bo.adminn;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * description 述管理员检索类
 *
 * @author chen wei
 * @version 1.0
 *          <p>
 *          Copyright: Copyright (c) 2019
 *          </p>
 * @date 2019/11/12 20:58
 */
@Data
@ApiModel
public class AdminSearchReq {

    @ApiModelProperty("查询关键字")
    private String keyWord;

    @ApiModelProperty("角色ID")
    private Long roleId;

}
