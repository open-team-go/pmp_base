package com.arz.pmp.base.api.bo.adminn;

import lombok.Data;

import java.util.List;

/**
 * description 管理员角色权限类
 *
 * @author chen wei
 * @version 1.0
 *          <p>
 *          Copyright: Copyright (c) 2019
 *          </p>
 * @date 2019/8/8 18:54
 */
@Data
public class AdminPermData {

    /** 角色code */
    private String roleCode;
    /** 权限集合 */
    private List<String> permissionList;

}
