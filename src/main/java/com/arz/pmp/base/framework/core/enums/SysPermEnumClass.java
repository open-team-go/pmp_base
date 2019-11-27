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
}
