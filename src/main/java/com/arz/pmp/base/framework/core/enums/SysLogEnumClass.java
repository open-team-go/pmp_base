package com.arz.pmp.base.framework.core.enums;

/**
 * description 管理日志数据库写入枚举类集
 * 
 * @author chen wei
 * @date 2019/11/12
 */
public class SysLogEnumClass {

    /**
     * description 操作类型枚举
     * 
     * @author chen wei
     * @date 2019/11/12
     */
    public static enum OptionTypeEnum {
        /** 更新 */
        UPDATE("UPDATE"),
        /** 新增 */
        ADD("ADD"),
        /** 删除 */
        DELETE("DELETE"),
        /** 查看 */
        QUERY("QUERY"),
        /** 导出 */
        EXPORT("EXPORT"),
        /** 导入 */
        IMPORT("IMPORT"),

        ;
        private String name;

        OptionTypeEnum(String name) {
            this.name = name;
        }

        public String getName() {
            return name;
        }
    }

    /**
     * description 操作模块枚举
     * 
     * @author chen wei
     * @date 2019/11/12
     */
    public enum OptionModuleEnum {
        /** 管理员登录 */
        SYS_LOGIN("SYS_LOGIN_IN"),
        /** 管理员管理 */
        SYS_ADMIN("SYS_ADMIN"),
        /** 权限管理 */
        SYS_PERMISSION("SYS_PERMISSION"),
        /** 权限管理 */
        SYS_ROLE("SYS_ROLE"),
        /** 教学点管理 */
        SYS_TEACHING_PLACE("SYS_TEACHING_PLACE"),
        /** 课程管理 */
        SYS_COURSE("SYS_COURSE"),
        /** 班级管理 */
        SYS_ROOM("SYS_ROOM"),
        /** 学员管理 */
        SYS_USER("SYS_USER"),
        /** 日志管理 */
        SYS_LOG("SYS_LOG"),

        ;
        private String name;

        OptionModuleEnum(String name) {
            this.name = name;
        }

        public String getName() {
            return name;
        }
    }

}
