package com.arz.pmp.base.entity;

/**
 * Database Table Remarks:
 *   付款方式信息表
 *
 * This class was generated by MyBatis Generator.
 * This class corresponds to the database table pmp_user_pay_type
 */
public class PmpUserPayTypeEntity {
    /**
     * Database Column Remarks:
     *   付款方式信息表ID
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column pmp_user_pay_type.pay_id
     *
     * @mbg.generated
     */
    private Long payId;

    /**
     * Database Column Remarks:
     *   创建时间
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column pmp_user_pay_type.create_time
     *
     * @mbg.generated
     */
    private Long createTime;

    /**
     * Database Column Remarks:
     *   修改时间
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column pmp_user_pay_type.update_time
     *
     * @mbg.generated
     */
    private Long updateTime;

    /**
     * Database Column Remarks:
     *   创建人
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column pmp_user_pay_type.create_manager
     *
     * @mbg.generated
     */
    private Long createManager;

    /**
     * Database Column Remarks:
     *   修改人
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column pmp_user_pay_type.update_manager
     *
     * @mbg.generated
     */
    private Long updateManager;

    /**
     * Database Column Remarks:
     *   删除状态
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column pmp_user_pay_type.del_on
     *
     * @mbg.generated
     */
    private Boolean delOn;

    /**
     * Database Column Remarks:
     *   名称
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column pmp_user_pay_type.pay_name
     *
     * @mbg.generated
     */
    private String payName;

    /**
     * Database Column Remarks:
     *   排序
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column pmp_user_pay_type.sort
     *
     * @mbg.generated
     */
    private Integer sort;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column pmp_user_pay_type.pay_id
     *
     * @return the value of pmp_user_pay_type.pay_id
     *
     * @mbg.generated
     */
    public Long getPayId() {
        return payId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column pmp_user_pay_type.pay_id
     *
     * @param payId the value for pmp_user_pay_type.pay_id
     *
     * @mbg.generated
     */
    public void setPayId(Long payId) {
        this.payId = payId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column pmp_user_pay_type.create_time
     *
     * @return the value of pmp_user_pay_type.create_time
     *
     * @mbg.generated
     */
    public Long getCreateTime() {
        return createTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column pmp_user_pay_type.create_time
     *
     * @param createTime the value for pmp_user_pay_type.create_time
     *
     * @mbg.generated
     */
    public void setCreateTime(Long createTime) {
        this.createTime = createTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column pmp_user_pay_type.update_time
     *
     * @return the value of pmp_user_pay_type.update_time
     *
     * @mbg.generated
     */
    public Long getUpdateTime() {
        return updateTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column pmp_user_pay_type.update_time
     *
     * @param updateTime the value for pmp_user_pay_type.update_time
     *
     * @mbg.generated
     */
    public void setUpdateTime(Long updateTime) {
        this.updateTime = updateTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column pmp_user_pay_type.create_manager
     *
     * @return the value of pmp_user_pay_type.create_manager
     *
     * @mbg.generated
     */
    public Long getCreateManager() {
        return createManager;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column pmp_user_pay_type.create_manager
     *
     * @param createManager the value for pmp_user_pay_type.create_manager
     *
     * @mbg.generated
     */
    public void setCreateManager(Long createManager) {
        this.createManager = createManager;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column pmp_user_pay_type.update_manager
     *
     * @return the value of pmp_user_pay_type.update_manager
     *
     * @mbg.generated
     */
    public Long getUpdateManager() {
        return updateManager;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column pmp_user_pay_type.update_manager
     *
     * @param updateManager the value for pmp_user_pay_type.update_manager
     *
     * @mbg.generated
     */
    public void setUpdateManager(Long updateManager) {
        this.updateManager = updateManager;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column pmp_user_pay_type.del_on
     *
     * @return the value of pmp_user_pay_type.del_on
     *
     * @mbg.generated
     */
    public Boolean getDelOn() {
        return delOn;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column pmp_user_pay_type.del_on
     *
     * @param delOn the value for pmp_user_pay_type.del_on
     *
     * @mbg.generated
     */
    public void setDelOn(Boolean delOn) {
        this.delOn = delOn;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column pmp_user_pay_type.pay_name
     *
     * @return the value of pmp_user_pay_type.pay_name
     *
     * @mbg.generated
     */
    public String getPayName() {
        return payName;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column pmp_user_pay_type.pay_name
     *
     * @param payName the value for pmp_user_pay_type.pay_name
     *
     * @mbg.generated
     */
    public void setPayName(String payName) {
        this.payName = payName == null ? null : payName.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column pmp_user_pay_type.sort
     *
     * @return the value of pmp_user_pay_type.sort
     *
     * @mbg.generated
     */
    public Integer getSort() {
        return sort;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column pmp_user_pay_type.sort
     *
     * @param sort the value for pmp_user_pay_type.sort
     *
     * @mbg.generated
     */
    public void setSort(Integer sort) {
        this.sort = sort;
    }
}