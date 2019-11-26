package com.arz.pmp.base.entity;

/**
 * Database Table Remarks:
 *   开课班级信息表
 *
 * This class was generated by MyBatis Generator.
 * This class corresponds to the database table pmp_teaching_room
 */
public class PmpTeachingRoomEntity {
    /**
     * Database Column Remarks:
     *   开课班级信息表ID
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column pmp_teaching_room.room_id
     *
     * @mbg.generated
     */
    private Long roomId;

    /**
     * Database Column Remarks:
     *   课程信息表ID
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column pmp_teaching_room.course_id
     *
     * @mbg.generated
     */
    private Long courseId;

    /**
     * Database Column Remarks:
     *   教学点信息表ID
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column pmp_teaching_room.place_id
     *
     * @mbg.generated
     */
    private Long placeId;

    /**
     * Database Column Remarks:
     *   课程开班教学类型表ID
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column pmp_teaching_room.type_id
     *
     * @mbg.generated
     */
    private Long typeId;

    /**
     * Database Column Remarks:
     *   名称
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column pmp_teaching_room.room_name
     *
     * @mbg.generated
     */
    private String roomName;

    /**
     * Database Column Remarks:
     *   描述
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column pmp_teaching_room.room_desc
     *
     * @mbg.generated
     */
    private String roomDesc;

    /**
     * Database Column Remarks:
     *   详细地址
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column pmp_teaching_room.room_address
     *
     * @mbg.generated
     */
    private String roomAddress;

    /**
     * Database Column Remarks:
     *   教务员ID
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column pmp_teaching_room.admin_id
     *
     * @mbg.generated
     */
    private Long adminId;

    /**
     * Database Column Remarks:
     *   教师名称
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column pmp_teaching_room.teacher_name
     *
     * @mbg.generated
     */
    private String teacherName;

    /**
     * Database Column Remarks:
     *   教师联系方式
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column pmp_teaching_room.teacher_phone
     *
     * @mbg.generated
     */
    private String teacherPhone;

    /**
     * Database Column Remarks:
     *   班级代号
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column pmp_teaching_room.room_code
     *
     * @mbg.generated
     */
    private String roomCode;

    /**
     * Database Column Remarks:
     *   开班开始时间
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column pmp_teaching_room.start_time
     *
     * @mbg.generated
     */
    private Long startTime;

    /**
     * Database Column Remarks:
     *   开班结束时间
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column pmp_teaching_room.end_time
     *
     * @mbg.generated
     */
    private Long endTime;

    /**
     * Database Column Remarks:
     *   排序
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column pmp_teaching_room.sort
     *
     * @mbg.generated
     */
    private Integer sort;

    /**
     * Database Column Remarks:
     *   是否使用
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column pmp_teaching_room.use_on
     *
     * @mbg.generated
     */
    private Boolean useOn;

    /**
     * Database Column Remarks:
     *   创建时间
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column pmp_teaching_room.create_time
     *
     * @mbg.generated
     */
    private Long createTime;

    /**
     * Database Column Remarks:
     *   修改时间
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column pmp_teaching_room.update_time
     *
     * @mbg.generated
     */
    private Long updateTime;

    /**
     * Database Column Remarks:
     *   创建人
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column pmp_teaching_room.create_manager
     *
     * @mbg.generated
     */
    private Long createManager;

    /**
     * Database Column Remarks:
     *   修改人
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column pmp_teaching_room.update_manager
     *
     * @mbg.generated
     */
    private Long updateManager;

    /**
     * Database Column Remarks:
     *   删除状态
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column pmp_teaching_room.del_on
     *
     * @mbg.generated
     */
    private Boolean delOn;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column pmp_teaching_room.room_id
     *
     * @return the value of pmp_teaching_room.room_id
     *
     * @mbg.generated
     */
    public Long getRoomId() {
        return roomId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column pmp_teaching_room.room_id
     *
     * @param roomId the value for pmp_teaching_room.room_id
     *
     * @mbg.generated
     */
    public void setRoomId(Long roomId) {
        this.roomId = roomId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column pmp_teaching_room.course_id
     *
     * @return the value of pmp_teaching_room.course_id
     *
     * @mbg.generated
     */
    public Long getCourseId() {
        return courseId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column pmp_teaching_room.course_id
     *
     * @param courseId the value for pmp_teaching_room.course_id
     *
     * @mbg.generated
     */
    public void setCourseId(Long courseId) {
        this.courseId = courseId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column pmp_teaching_room.place_id
     *
     * @return the value of pmp_teaching_room.place_id
     *
     * @mbg.generated
     */
    public Long getPlaceId() {
        return placeId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column pmp_teaching_room.place_id
     *
     * @param placeId the value for pmp_teaching_room.place_id
     *
     * @mbg.generated
     */
    public void setPlaceId(Long placeId) {
        this.placeId = placeId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column pmp_teaching_room.type_id
     *
     * @return the value of pmp_teaching_room.type_id
     *
     * @mbg.generated
     */
    public Long getTypeId() {
        return typeId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column pmp_teaching_room.type_id
     *
     * @param typeId the value for pmp_teaching_room.type_id
     *
     * @mbg.generated
     */
    public void setTypeId(Long typeId) {
        this.typeId = typeId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column pmp_teaching_room.room_name
     *
     * @return the value of pmp_teaching_room.room_name
     *
     * @mbg.generated
     */
    public String getRoomName() {
        return roomName;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column pmp_teaching_room.room_name
     *
     * @param roomName the value for pmp_teaching_room.room_name
     *
     * @mbg.generated
     */
    public void setRoomName(String roomName) {
        this.roomName = roomName == null ? null : roomName.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column pmp_teaching_room.room_desc
     *
     * @return the value of pmp_teaching_room.room_desc
     *
     * @mbg.generated
     */
    public String getRoomDesc() {
        return roomDesc;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column pmp_teaching_room.room_desc
     *
     * @param roomDesc the value for pmp_teaching_room.room_desc
     *
     * @mbg.generated
     */
    public void setRoomDesc(String roomDesc) {
        this.roomDesc = roomDesc == null ? null : roomDesc.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column pmp_teaching_room.room_address
     *
     * @return the value of pmp_teaching_room.room_address
     *
     * @mbg.generated
     */
    public String getRoomAddress() {
        return roomAddress;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column pmp_teaching_room.room_address
     *
     * @param roomAddress the value for pmp_teaching_room.room_address
     *
     * @mbg.generated
     */
    public void setRoomAddress(String roomAddress) {
        this.roomAddress = roomAddress == null ? null : roomAddress.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column pmp_teaching_room.admin_id
     *
     * @return the value of pmp_teaching_room.admin_id
     *
     * @mbg.generated
     */
    public Long getAdminId() {
        return adminId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column pmp_teaching_room.admin_id
     *
     * @param adminId the value for pmp_teaching_room.admin_id
     *
     * @mbg.generated
     */
    public void setAdminId(Long adminId) {
        this.adminId = adminId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column pmp_teaching_room.teacher_name
     *
     * @return the value of pmp_teaching_room.teacher_name
     *
     * @mbg.generated
     */
    public String getTeacherName() {
        return teacherName;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column pmp_teaching_room.teacher_name
     *
     * @param teacherName the value for pmp_teaching_room.teacher_name
     *
     * @mbg.generated
     */
    public void setTeacherName(String teacherName) {
        this.teacherName = teacherName == null ? null : teacherName.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column pmp_teaching_room.teacher_phone
     *
     * @return the value of pmp_teaching_room.teacher_phone
     *
     * @mbg.generated
     */
    public String getTeacherPhone() {
        return teacherPhone;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column pmp_teaching_room.teacher_phone
     *
     * @param teacherPhone the value for pmp_teaching_room.teacher_phone
     *
     * @mbg.generated
     */
    public void setTeacherPhone(String teacherPhone) {
        this.teacherPhone = teacherPhone == null ? null : teacherPhone.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column pmp_teaching_room.room_code
     *
     * @return the value of pmp_teaching_room.room_code
     *
     * @mbg.generated
     */
    public String getRoomCode() {
        return roomCode;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column pmp_teaching_room.room_code
     *
     * @param roomCode the value for pmp_teaching_room.room_code
     *
     * @mbg.generated
     */
    public void setRoomCode(String roomCode) {
        this.roomCode = roomCode == null ? null : roomCode.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column pmp_teaching_room.start_time
     *
     * @return the value of pmp_teaching_room.start_time
     *
     * @mbg.generated
     */
    public Long getStartTime() {
        return startTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column pmp_teaching_room.start_time
     *
     * @param startTime the value for pmp_teaching_room.start_time
     *
     * @mbg.generated
     */
    public void setStartTime(Long startTime) {
        this.startTime = startTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column pmp_teaching_room.end_time
     *
     * @return the value of pmp_teaching_room.end_time
     *
     * @mbg.generated
     */
    public Long getEndTime() {
        return endTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column pmp_teaching_room.end_time
     *
     * @param endTime the value for pmp_teaching_room.end_time
     *
     * @mbg.generated
     */
    public void setEndTime(Long endTime) {
        this.endTime = endTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column pmp_teaching_room.sort
     *
     * @return the value of pmp_teaching_room.sort
     *
     * @mbg.generated
     */
    public Integer getSort() {
        return sort;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column pmp_teaching_room.sort
     *
     * @param sort the value for pmp_teaching_room.sort
     *
     * @mbg.generated
     */
    public void setSort(Integer sort) {
        this.sort = sort;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column pmp_teaching_room.use_on
     *
     * @return the value of pmp_teaching_room.use_on
     *
     * @mbg.generated
     */
    public Boolean getUseOn() {
        return useOn;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column pmp_teaching_room.use_on
     *
     * @param useOn the value for pmp_teaching_room.use_on
     *
     * @mbg.generated
     */
    public void setUseOn(Boolean useOn) {
        this.useOn = useOn;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column pmp_teaching_room.create_time
     *
     * @return the value of pmp_teaching_room.create_time
     *
     * @mbg.generated
     */
    public Long getCreateTime() {
        return createTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column pmp_teaching_room.create_time
     *
     * @param createTime the value for pmp_teaching_room.create_time
     *
     * @mbg.generated
     */
    public void setCreateTime(Long createTime) {
        this.createTime = createTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column pmp_teaching_room.update_time
     *
     * @return the value of pmp_teaching_room.update_time
     *
     * @mbg.generated
     */
    public Long getUpdateTime() {
        return updateTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column pmp_teaching_room.update_time
     *
     * @param updateTime the value for pmp_teaching_room.update_time
     *
     * @mbg.generated
     */
    public void setUpdateTime(Long updateTime) {
        this.updateTime = updateTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column pmp_teaching_room.create_manager
     *
     * @return the value of pmp_teaching_room.create_manager
     *
     * @mbg.generated
     */
    public Long getCreateManager() {
        return createManager;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column pmp_teaching_room.create_manager
     *
     * @param createManager the value for pmp_teaching_room.create_manager
     *
     * @mbg.generated
     */
    public void setCreateManager(Long createManager) {
        this.createManager = createManager;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column pmp_teaching_room.update_manager
     *
     * @return the value of pmp_teaching_room.update_manager
     *
     * @mbg.generated
     */
    public Long getUpdateManager() {
        return updateManager;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column pmp_teaching_room.update_manager
     *
     * @param updateManager the value for pmp_teaching_room.update_manager
     *
     * @mbg.generated
     */
    public void setUpdateManager(Long updateManager) {
        this.updateManager = updateManager;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column pmp_teaching_room.del_on
     *
     * @return the value of pmp_teaching_room.del_on
     *
     * @mbg.generated
     */
    public Boolean getDelOn() {
        return delOn;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column pmp_teaching_room.del_on
     *
     * @param delOn the value for pmp_teaching_room.del_on
     *
     * @mbg.generated
     */
    public void setDelOn(Boolean delOn) {
        this.delOn = delOn;
    }
}