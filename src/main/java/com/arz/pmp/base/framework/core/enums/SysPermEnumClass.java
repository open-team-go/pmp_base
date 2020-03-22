package com.arz.pmp.base.framework.core.enums;

public class SysPermEnumClass {

    public enum RoleEnum {
        ADMIN("超级管理员", "ADMIN"),
        OPERATOR("普通管理员", "OPERATOR"),
        SALES("销售人员", "SALES"),
        EDUCATION("教务人员", "EDUCATION"),;
        private String name;

        private String code;

        RoleEnum(String name, String code) {
            this.name = name;
            this.code = code;
        }

        public String getName() {
            return name;
        }

        public String getCode() {
            return code;
        }
    }
    public enum PermissionEnum{
        ADMIN_READ("管理员查看", "admin:read"),
        ADMIN_ADD("管理员添加", "admin:create"),
        ADMIN_UPDATE("管理员编辑", "admin:update"),
        ADMIN_DEL("管理员删除", "admin:delete"),
        ROLE_READ("角色查看", "role:read"),
        ROLE_ADD("角色添加", "role:create"),
        ROLE_UPDATE("角色编辑", "role:update"),
        ROLE_DEL("角色删除", "role:delete"),
        PERM_READ("功能查看", "perm:read"),
        PERM_ADD("功能添加", "perm:create"),
        PERM_UPDATE("功能编辑", "perm:update"),
        PERM_DEL("功能删除", "perm:delete"),
        COURSE_READ("课程查看", "course:read"),
        COURSE_ADD("课程添加", "course:create"),
        COURSE_UPDATE("课程编辑", "course:update"),
        COURSE_DEL("课程删除", "course:delete"),
        PLACE_READ("教学点查看", "place:read"),
        PLACE_ADD("教学点添加", "place:create"),
        PLACE_UPDATE("教学点编辑", "place:update"),
        PLACE_DEL("教学点删除", "place:delete"),
        ROOM_READ("班级查看", "room:read"),
        ROOM_ADD("班级添加", "room:create"),
        ROOM_UPDATE("班级编辑", "room:update"),
        ROOM_DEL("班级删除", "room:delete"),
        USER_READ("学员查看", "user:read"),
        USER_ADD("学员添加", "user:create"),
        USER_UPDATE("学员编辑", "user:update"),
        USER_DEL("学员删除", "user:delete"),
        USER_IMPORT("学员导入", "user:import"),
        USER_EXPORT("学员导出", "user:export"),
        LOG_READ("日志查看", "log:read"),
        LOG_ADD("日志添加", "log:create"),
        LOG_UPDATE("日志编辑", "log:update"),
        LOG_DEL("日志删除", "log:delete"),
        FRONT_USER("前台学员", "user:all"),
        ;
        private String name;

        private String code;

        PermissionEnum(String name, String code) {
            this.name = name;
            this.code = code;
        }

        public String getName() {
            return name;
        }

        public String getCode() {
            return code;
        }
    }
}
