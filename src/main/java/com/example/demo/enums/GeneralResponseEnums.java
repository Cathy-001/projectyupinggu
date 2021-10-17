package com.example.demo.enums;

import lombok.Getter;

/**
 * @Author: Summerless
 * @Date: 2020/9/28 17:13
 */
@Getter
public enum GeneralResponseEnums implements IResponseEnums {

    /**
     * 请求成功
     */
    SUCCESS(1, "请求成功"),
    /**
     * 服务端异常
     */
    SYSTEM_ERROR(-1, "系统异常"),
    /**
     * 客户端异常
     */
    REQUEST_ERROR(-2, "客户端请求异常"),
    /**
     * 数据库添加数据失败
     */
    ADD_FAILED(-3, "添加失败"),

    /**
     * 读取数据失败
     */
    NOT_LOGIN(-5, "未登录"),
    /**
     * 读取数据失败
     */
    GET_FAILED(-4, "获取失败"),

    /**
     * 上传失败
     */
    UPLOAD_FAILED(-6, "获取失败"),

    /**
     * 删除失败
     */
    DELETE_FAILED(-7,"删除失败"),

    /**
     * 更新失败
     */
    UPDATE_FAILED(-8,"更新失败"),

	
	SEND_FAILED(-9,"短信发送失败"),

    SUBMIT_FAILED(-10,"提交失败"),

    OPERATE_DAILED(-11,"操作失败"),

    INCOMPLETE_EXECUTION(-12,"计划未执行完毕");

    private final Integer code;
    private final String message;

    GeneralResponseEnums(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    @Override
    public Integer getCode() {
        return code;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
