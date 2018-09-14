package com.github.Duankan.base;

public class ResponsePojo<T> {
    private String code;//返回状态码
    private String msg;//响应信息
    private T object;//返回的对象

    public ResponsePojo(){}
    public ResponsePojo(ResponseEnum responseCodeType){
        this.code=responseCodeType.getCode();
        this.msg=responseCodeType.getDisplayName();
    }
    public ResponsePojo(ResponseEnum responseEnum,T object){
        this.code=responseEnum.getCode();
        this.msg=responseEnum.getDisplayName();
        this.object=object;
    }
    public ResponsePojo(String code,String msg,T object){
        this.code=code;
        this.msg=msg;
        this.object=object;
    }
    public String getCode() {
        return code;
    }
    public void setCode(String code) {
        this.code = code;
    }
    public String getMsg() {
        return msg;
    }
    public void setMsg(String msg) {
        this.msg = msg;
    }
    public T getObject() {
        return object;
    }
    public void setObject(T object) {
        this.object = object;
    }
}
