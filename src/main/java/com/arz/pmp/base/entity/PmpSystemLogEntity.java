package com.arz.pmp.base.entity;

/**
 * Database Table Remarks:
 *   系统操作日志表
 *
 * This class was generated by MyBatis Generator.
 * This class corresponds to the database table pmp_system_log
 */
public class PmpSystemLogEntity {
    /**
     * Database Column Remarks:
     *   系统操作日志表ID
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column pmp_system_log.log_id
     *
     * @mbg.generated
     */
    private Long logId;

    /**
     * Database Column Remarks:
     *   管理员信息表ID
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column pmp_system_log.admin_id
     *
     * @mbg.generated
     */
    private Long adminId;

    /**
     * Database Column Remarks:
     *   创建时间
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column pmp_system_log.create_time
     *
     * @mbg.generated
     */
    private Long createTime;

    /**
     * Database Column Remarks:
     *   修改时间
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column pmp_system_log.update_time
     *
     * @mbg.generated
     */
    private Long updateTime;

    /**
     * Database Column Remarks:
     *   创建人
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column pmp_system_log.create_manager
     *
     * @mbg.generated
     */
    private Long createManager;

    /**
     * Database Column Remarks:
     *   修改人
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column pmp_system_log.update_manager
     *
     * @mbg.generated
     */
    private Long updateManager;

    /**
     * Database Column Remarks:
     *   删除状态
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column pmp_system_log.del_on
     *
     * @mbg.generated
     */
    private Boolean delOn;

    /**
     * Database Column Remarks:
     *   操作模块
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column pmp_system_log.opt_module
     *
     * @mbg.generated
     */
    private String optModule;

    /**
     * Database Column Remarks:
     *   操作类型
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column pmp_system_log.opt_type
     *
     * @mbg.generated
     */
    private String optType;

    /**
     * Database Column Remarks:
     *   操作接口
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column pmp_system_log.opt_api
     *
     * @mbg.generated
     */
    private String optApi;

    /**
     * Database Column Remarks:
     *   操作描述
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column pmp_system_log.opt_desc
     *
     * @mbg.generated
     */
    private String optDesc;

    /**
     * Database Column Remarks:
     *   访问IP
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column pmp_system_log.request_ip
     *
     * @mbg.generated
     */
    private String requestIp;

    /**
     * Database Column Remarks:
     *   操作数据
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column pmp_system_log.opt_data
     *
     * @mbg.generated
     */
    private String optData;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column pmp_system_log.log_id
     *
     * @return the value of pmp_system_log.log_id
     *
     * @mbg.generated
     */
    public Long getLogId() {
        return logId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column pmp_system_log.log_id
     *
     * @param logId the value for pmp_system_log.log_id
     *
     * @mbg.generated
     */
    public void setLogId(Long logId) {
        this.logId = logId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column pmp_system_log.admin_id
     *
     * @return the value of pmp_system_log.admin_id
     *
     * @mbg.generated
     */
    public Long getAdminId() {
        return adminId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column pmp_system_log.admin_id
     *
     * @param adminId the value for pmp_system_log.admin_id
     *
     * @mbg.generated
     */
    public void setAdminId(Long adminId) {
        this.adminId = adminId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column pmp_system_log.create_time
     *
     * @return the value of pmp_system_log.create_time
     *
     * @mbg.generated
     */
    public Long getCreateTime() {
        return createTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column pmp_system_log.create_time
     *
     * @param createTime the value for pmp_system_log.create_time
     *
     * @mbg.generated
     */
    public void setCreateTime(Long createTime) {
        this.createTime = createTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column pmp_system_log.update_time
     *
     * @return the value of pmp_system_log.update_time
     *
     * @mbg.generated
     */
    public Long getUpdateTime() {
        return updateTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column pmp_system_log.update_time
     *
     * @param updateTime the value for pmp_system_log.update_time
     *
     * @mbg.generated
     */
    public void setUpdateTime(Long updateTime) {
        this.updateTime = updateTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column pmp_system_log.create_manager
     *
     * @return the value of pmp_system_log.create_manager
     *
     * @mbg.generated
     */
    public Long getCreateManager() {
        return createManager;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column pmp_system_log.create_manager
     *
     * @param createManager the value for pmp_system_log.create_manager
     *
     * @mbg.generated
     */
    public void setCreateManager(Long createManager) {
        this.createManager = createManager;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column pmp_system_log.update_manager
     *
     * @return the value of pmp_system_log.update_manager
     *
     * @mbg.generated
     */
    public Long getUpdateManager() {
        return updateManager;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column pmp_system_log.update_manager
     *
     * @param updateManager the value for pmp_system_log.update_manager
     *
     * @mbg.generated
     */
    public void setUpdateManager(Long updateManager) {
        this.updateManager = updateManager;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column pmp_system_log.del_on
     *
     * @return the value of pmp_system_log.del_on
     *
     * @mbg.generated
     */
    public Boolean getDelOn() {
        return delOn;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column pmp_system_log.del_on
     *
     * @param delOn the value for pmp_system_log.del_on
     *
     * @mbg.generated
     */
    public void setDelOn(Boolean delOn) {
        this.delOn = delOn;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column pmp_system_log.opt_module
     *
     * @return the value of pmp_system_log.opt_module
     *
     * @mbg.generated
     */
    public String getOptModule() {
        return optModule;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column pmp_system_log.opt_module
     *
     * @param optModule the value for pmp_system_log.opt_module
     *
     * @mbg.generated
     */
    public void setOptModule(String optModule) {
        this.optModule = optModule == null ? null : optModule.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column pmp_system_log.opt_type
     *
     * @return the value of pmp_system_log.opt_type
     *
     * @mbg.generated
     */
    public String getOptType() {
        return optType;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column pmp_system_log.opt_type
     *
     * @param optType the value for pmp_system_log.opt_type
     *
     * @mbg.generated
     */
    public void setOptType(String optType) {
        this.optType = optType == null ? null : optType.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column pmp_system_log.opt_api
     *
     * @return the value of pmp_system_log.opt_api
     *
     * @mbg.generated
     */
    public String getOptApi() {
        return optApi;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column pmp_system_log.opt_api
     *
     * @param optApi the value for pmp_system_log.opt_api
     *
     * @mbg.generated
     */
    public void setOptApi(String optApi) {
        this.optApi = optApi == null ? null : optApi.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column pmp_system_log.opt_desc
     *
     * @return the value of pmp_system_log.opt_desc
     *
     * @mbg.generated
     */
    public String getOptDesc() {
        return optDesc;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column pmp_system_log.opt_desc
     *
     * @param optDesc the value for pmp_system_log.opt_desc
     *
     * @mbg.generated
     */
    public void setOptDesc(String optDesc) {
        this.optDesc = optDesc == null ? null : optDesc.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column pmp_system_log.request_ip
     *
     * @return the value of pmp_system_log.request_ip
     *
     * @mbg.generated
     */
    public String getRequestIp() {
        return requestIp;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column pmp_system_log.request_ip
     *
     * @param requestIp the value for pmp_system_log.request_ip
     *
     * @mbg.generated
     */
    public void setRequestIp(String requestIp) {
        this.requestIp = requestIp == null ? null : requestIp.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column pmp_system_log.opt_data
     *
     * @return the value of pmp_system_log.opt_data
     *
     * @mbg.generated
     */
    public String getOptData() {
        return optData;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column pmp_system_log.opt_data
     *
     * @param optData the value for pmp_system_log.opt_data
     *
     * @mbg.generated
     */
    public void setOptData(String optData) {
        this.optData = optData == null ? null : optData.trim();
    }
}