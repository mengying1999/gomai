package com.gomai.utils;

public class Result {
    private Integer code;  //状态码
    private Boolean flag;  //成功标志
    private String messages;  //消息
    private Object data;  //数据

    public Result() {
    }

    public Result(Integer code, Boolean flag, String messages) {
        this.code = code;
        this.flag = flag;
        this.messages = messages;
    }

    public Result(Integer code, Boolean flag, String messages, Object data) {
        this.code = code;
        this.flag = flag;
        this.messages = messages;
        this.data = data;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public Boolean getFlag() {
        return flag;
    }

    public void setFlag(Boolean flag) {
        this.flag = flag;
    }

    public String getMessages() {
        return messages;
    }

    public void setMessages(String messages) {
        this.messages = messages;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "Result{" +
                "code=" + code +
                ", flag=" + flag +
                ", messages='" + messages + '\'' +
                ", data=" + data +
                '}';
    }
}
