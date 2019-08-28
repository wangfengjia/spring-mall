package com.mall.admin.enums;

/**
 * 记录是否被删除的枚举类
 * @author wangyongchun
 * @version 1.0
 * @date 2019/03/10 11:15
 */
public enum AdminRecordStatusEnum {

    /**
     * 是否被删除:是
     */
    DELETED("被删除", 1),

    /**
     * 是否被删除:否
     */
    NOEMAL("正常", 2);


    private String description;
    private Integer code;

    AdminRecordStatusEnum(String description, Integer code){
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
    }}
