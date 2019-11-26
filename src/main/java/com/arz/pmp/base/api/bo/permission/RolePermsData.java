package com.arz.pmp.base.api.bo.permission;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

/**
 * description java类作用描述
 *
 * @author chen wei
 * @version 1.0
 *          <p>
 *          Copyright: Copyright (c) 2019
 *          </p>
 * @date 2019/11/14 15:45
 */
@ApiModel
@Data
public class RolePermsData {

    @ApiModelProperty("角色ID")
    private Long roleId;
    @ApiModelProperty("权限ID集")
    private List<Long> permIdList;
}
