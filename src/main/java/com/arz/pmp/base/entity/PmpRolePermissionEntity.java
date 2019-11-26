package com.arz.pmp.base.entity;

/**
 * Database Table Remarks:
 *   管理员角色权限关联表
 *
 * This class was generated by MyBatis Generator.
 * This class corresponds to the database table pmp_role_permission
 */
public class PmpRolePermissionEntity {
    /**
     * Database Column Remarks:
     *   管理员角色权限表ID
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column pmp_role_permission.id
     *
     * @mbg.generated
     */
    private Long id;

    /**
     * Database Column Remarks:
     *   管理员角色表ID
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column pmp_role_permission.role_id
     *
     * @mbg.generated
     */
    private Long roleId;

    /**
     * Database Column Remarks:
     *   管理员权限表ID
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column pmp_role_permission.perm_id
     *
     * @mbg.generated
     */
    private Long permId;

    /**
     * Database Column Remarks:
     *   创建时间
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column pmp_role_permission.create_time
     *
     * @mbg.generated
     */
    private Long createTime;

    /**
     * Database Column Remarks:
     *   修改时间
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column pmp_role_permission.update_time
     *
     * @mbg.generated
     */
    private Long updateTime;

    /**
     * Database Column Remarks:
     *   创建人
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column pmp_role_permission.create_manager
     *
     * @mbg.generated
     */
    private Long createManager;

    /**
     * Database Column Remarks:
     *   修改人
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column pmp_role_permission.update_manager
     *
     * @mbg.generated
     */
    private Long updateManager;

    /**
     * Database Column Remarks:
     *   删除状态
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column pmp_role_permission.del_on
     *
     * @mbg.generated
     */
    private Boolean delOn;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column pmp_role_permission.id
     *
     * @return the value of pmp_role_permission.id
     *
     * @mbg.generated
     */
    public Long getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column pmp_role_permission.id
     *
     * @param id the value for pmp_role_permission.id
     *
     * @mbg.generated
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column pmp_role_permission.role_id
     *
     * @return the value of pmp_role_permission.role_id
     *
     * @mbg.generated
     */
    public Long getRoleId() {
        return roleId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column pmp_role_permission.role_id
     *
     * @param roleId the value for pmp_role_permission.role_id
     *
     * @mbg.generated
     */
    public void setRoleId(Long roleId) {
        this.roleId = roleId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column pmp_role_permission.perm_id
     *
     * @return the value of pmp_role_permission.perm_id
     *
     * @mbg.generated
     */
    public Long getPermId() {
        return permId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column pmp_role_permission.perm_id
     *
     * @param permId the value for pmp_role_permission.perm_id
     *
     * @mbg.generated
     */
    public void setPermId(Long permId) {
        this.permId = permId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column pmp_role_permission.create_time
     *
     * @return the value of pmp_role_permission.create_time
     *
     * @mbg.generated
     */
    public Long getCreateTime() {
        return createTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column pmp_role_permission.create_time
     *
     * @param createTime the value for pmp_role_permission.create_time
     *
     * @mbg.generated
     */
    public void setCreateTime(Long createTime) {
        this.createTime = createTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column pmp_role_permission.update_time
     *
     * @return the value of pmp_role_permission.update_time
     *
     * @mbg.generated
     */
    public Long getUpdateTime() {
        return updateTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column pmp_role_permission.update_time
     *
     * @param updateTime the value for pmp_role_permission.update_time
     *
     * @mbg.generated
     */
    public void setUpdateTime(Long updateTime) {
        this.updateTime = updateTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column pmp_role_permission.create_manager
     *
     * @return the value of pmp_role_permission.create_manager
     *
     * @mbg.generated
     */
    public Long getCreateManager() {
        return createManager;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column pmp_role_permission.create_manager
     *
     * @param createManager the value for pmp_role_permission.create_manager
     *
     * @mbg.generated
     */
    public void setCreateManager(Long createManager) {
        this.createManager = createManager;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column pmp_role_permission.update_manager
     *
     * @return the value of pmp_role_permission.update_manager
     *
     * @mbg.generated
     */
    public Long getUpdateManager() {
        return updateManager;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column pmp_role_permission.update_manager
     *
     * @param updateManager the value for pmp_role_permission.update_manager
     *
     * @mbg.generated
     */
    public void setUpdateManager(Long updateManager) {
        this.updateManager = updateManager;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column pmp_role_permission.del_on
     *
     * @return the value of pmp_role_permission.del_on
     *
     * @mbg.generated
     */
    public Boolean getDelOn() {
        return delOn;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column pmp_role_permission.del_on
     *
     * @param delOn the value for pmp_role_permission.del_on
     *
     * @mbg.generated
     */
    public void setDelOn(Boolean delOn) {
        this.delOn = delOn;
    }
}