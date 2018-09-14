package com.github.Duankan.base;

public enum ResponseEnum {

    SUCCESS("0","成功",""),
    FAIL("-1","失败",""),
    EXCEPTION_SYSTEM_INNER_ERROR("0005", "系统繁忙", "系统内部异常"),
    CODE_ERROR("0005", "系统繁忙", ""),
    DATABASE_EXCEPTION("0005", "系统繁忙", "数据库操作异常"),
    PARAMETER_ERROR("0003", "参数有误", ""),
    ES_ERROR("0005", "系统繁忙", "ES查询异常");

    private String code;//状态码
    private String displayName;//显示的名称
    private  String desc;//描述

    private ResponseEnum(String code, String displayName, String desc){
        this.code=code;
        this.displayName = displayName;
        this.desc = desc;

    }
    public String getDisplayName() {
        return displayName;
    }
    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }
    public String getDesc() {
        return desc;
    }
    public void setDesc(String desc) {
        this.desc = desc;
    }
    public String getCode() {
        return code;
    }
}
