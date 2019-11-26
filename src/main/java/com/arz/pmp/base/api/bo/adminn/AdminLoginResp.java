package com.arz.pmp.base.api.bo.adminn;

import java.util.List;

import com.arz.pmp.base.entity.PmpAdminEntity;
import com.arz.pmp.base.entity.PmpPermissionEntity;
import com.arz.pmp.base.entity.PmpRoleEntity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * description 管理员信息
 *
 * @author chen wei
 * @version 1.0
 *          <p>
 *          Copyright: Copyright (c) 2019
 *          </p>
 * @date 2019/11/12 14:21
 */
@Data
@ApiModel
public class AdminLoginResp {

    @ApiModelProperty("基本信息")
    private Long adminId;

    @ApiModelProperty("基本信息")
    private PmpAdminEntity userInfo;

    @ApiModelProperty("角色信息")
    private PmpRoleEntity roleInfo;

    @ApiModelProperty("权限信息")
    private List<PmpPermissionEntity> permissionList;

    @ApiModelProperty("登录凭证")
    private String authentication;

}
