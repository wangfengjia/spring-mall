package com.mall.admin.enums;


public enum AdminUserStatusEnum {

    /*
    * 后台账号的状态:禁用
    */
    ADMIN_USER_STATUS_FORBIDDEN("禁用", 1),

    /*
    * 后台账号状态:启用
    */
    ADMIN_USER_STATUS_NORMAL("启用", 2);

    private String description;

    private Integer code;

    AdminUserStatusEnum(String description, Integer code){
        this.description = description;
        this.code = code;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    /*
    * 根据code值得到枚举类
    *
    * @param code 状态值
    * @return 得到枚举类
    */
    public static String getDescByCode(Integer code){
        String desc = null;
        for (AdminUserStatusEnum statusEnum : AdminUserStatusEnum.values()){
            if (code.equals(statusEnum.getCode())){
                desc = statusEnum.getDescription();
            }
        }

        return desc;
    }
}
