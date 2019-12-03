package com.arz.pmp.base.entity;

/**
 * Database Table Remarks:
 *   管理员权限表
 *
 * This class was generated by MyBatis Generator.
 * This class corresponds to the database table pmp_permission
 */
public class PmpPermissionEntity {
    /**
     * Database Column Remarks:
     *   管理员权限表ID
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column pmp_permission.perm_id
     *
     * @mbg.generated
     */
    private Long permId;

    /**
     * Database Column Remarks:
     *   级别
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column pmp_permission.perm_level
     *
     * @mbg.generated
     */
    private Integer permLevel;

    /**
     * Database Column Remarks:
     *   名称
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column pmp_permission.perm_name
     *
     * @mbg.generated
     */
    private String permName;

    /**
     * Database Column Remarks:
     *   上级ID
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column pmp_permission.pid
     *
     * @mbg.generated
     */
    private Long pid;

    /**
     * Database Column Remarks:
     *   编码
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column pmp_permission.perm_value
     *
     * @mbg.generated
     */
    private String permValue;

    /**
     * Database Column Remarks:
     *   接口路径
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column pmp_permission.perm_uri
     *
     * @mbg.generated
     */
    private String permUri;

    /**
     * Database Column Remarks:
     *   排序
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column pmp_permission.sort
     *
     * @mbg.generated
     */
    private Integer sort;

    /**
     * Database Column Remarks:
     *   创建时间
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column pmp_permission.create_time
     *
     * @mbg.generated
     */
    private Long createTime;

    /**
     * Database Column Remarks:
     *   修改时间
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column pmp_permission.update_time
     *
     * @mbg.generated
     */
    private Long updateTime;

    /**
     * Database Column Remarks:
     *   创建人
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column pmp_permission.create_manager
     *
     * @mbg.generated
     */
    private Long createManager;

    /**
     * Database Column Remarks:
     *   修改人
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column pmp_permission.update_manager
     *
     * @mbg.generated
     */
    private Long updateManager;

    /**
     * Database Column Remarks:
     *   删除状态
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column pmp_permission.del_on
     *
     * @mbg.generated
     */
    private Boolean delOn;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column pmp_permission.perm_id
     *
     * @return the value of pmp_permission.perm_id
     *
     * @mbg.generated
     */
    public Long getPermId() {
        return permId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column pmp_permission.perm_id
     *
     * @param permId the value for pmp_permission.perm_id
     *
     * @mbg.generated
     */
    public void setPermId(Long permId) {
        this.permId = permId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column pmp_permission.perm_level
     *
     * @return the value of pmp_permission.perm_level
     *
     * @mbg.generated
     */
    public Integer getPermLevel() {
        return permLevel;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column pmp_permission.perm_level
     *
     * @param permLevel the value for pmp_permission.perm_level
     *
     * @mbg.generated
     */
    public void setPermLevel(Integer permLevel) {
        this.permLevel = permLevel;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column pmp_permission.perm_name
     *
     * @return the value of pmp_permission.perm_name
     *
     * @mbg.generated
     */
    public String getPermName() {
        return permName;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column pmp_permission.perm_name
     *
     * @param permName the value for pmp_permission.perm_name
     *
     * @mbg.generated
     */
    public void setPermName(String permName) {
        this.permName = permName == null ? null : permName.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column pmp_permission.pid
     *
     * @return the value of pmp_permission.pid
     *
     * @mbg.generated
     */
    public Long getPid() {
        return pid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column pmp_permission.pid
     *
     * @param pid the value for pmp_permission.pid
     *
     * @mbg.generated
     */
    public void setPid(Long pid) {
        this.pid = pid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column pmp_permission.perm_value
     *
     * @return the value of pmp_permission.perm_value
     *
     * @mbg.generated
     */
    public String getPermValue() {
        return permValue;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column pmp_permission.perm_value
     *
     * @param permValue the value for pmp_permission.perm_value
     *
     * @mbg.generated
     */
    public void setPermValue(String permValue) {
        this.permValue = permValue == null ? null : permValue.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column pmp_permission.perm_uri
     *
     * @return the value of pmp_permission.perm_uri
     *
     * @mbg.generated
     */
    public String getPermUri() {
        return permUri;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column pmp_permission.perm_uri
     *
     * @param permUri the value for pmp_permission.perm_uri
     *
     * @mbg.generated
     */
    public void setPermUri(String permUri) {
        this.permUri = permUri == null ? null : permUri.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column pmp_permission.sort
     *
     * @return the value of pmp_permission.sort
     *
     * @mbg.generated
     */
    public Integer getSort() {
        return sort;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column pmp_permission.sort
     *
     * @param sort the value for pmp_permission.sort
     *
     * @mbg.generated
     */
    public void setSort(Integer sort) {
        this.sort = sort;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column pmp_permission.create_time
     *
     * @return the value of pmp_permission.create_time
     *
     * @mbg.generated
     */
    public Long getCreateTime() {
        return createTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column pmp_permission.create_time
     *
     * @param createTime the value for pmp_permission.create_time
     *
     * @mbg.generated
     */
    public void setCreateTime(Long createTime) {
        this.createTime = createTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column pmp_permission.update_time
     *
     * @return the value of pmp_permission.update_time
     *
     * @mbg.generated
     */
    public Long getUpdateTime() {
        return updateTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column pmp_permission.update_time
     *
     * @param updateTime the value for pmp_permission.update_time
     *
     * @mbg.generated
     */
    public void setUpdateTime(Long updateTime) {
        this.updateTime = updateTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column pmp_permission.create_manager
     *
     * @return the value of pmp_permission.create_manager
     *
     * @mbg.generated
     */
    public Long getCreateManager() {
        return createManager;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column pmp_permission.create_manager
     *
     * @param createManager the value for pmp_permission.create_manager
     *
     * @mbg.generated
     */
    public void setCreateManager(Long createManager) {
        this.createManager = createManager;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column pmp_permission.update_manager
     *
     * @return the value of pmp_permission.update_manager
     *
     * @mbg.generated
     */
    public Long getUpdateManager() {
        return updateManager;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column pmp_permission.update_manager
     *
     * @param updateManager the value for pmp_permission.update_manager
     *
     * @mbg.generated
     */
    public void setUpdateManager(Long updateManager) {
        this.updateManager = updateManager;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column pmp_permission.del_on
     *
     * @return the value of pmp_permission.del_on
     *
     * @mbg.generated
     */
    public Boolean getDelOn() {
        return delOn;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column pmp_permission.del_on
     *
     * @param delOn the value for pmp_permission.del_on
     *
     * @mbg.generated
     */
    public void setDelOn(Boolean delOn) {
        this.delOn = delOn;
    }
}