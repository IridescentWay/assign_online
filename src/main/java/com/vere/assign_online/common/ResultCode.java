package com.vere.assign_online.common;

/**
 * ClassName:ResultCode
 * Package:com.vere.demo.utils
 * Description:
 *
 * @Date:2022/4/17 22:35
 * @Author:3046990030@qq.com
 */
public enum ResultCode {
    /* 成功状态码 */
    SUCCESS(200, "成功"),


    /* 系统500错误*/
    SYSTEM_ERROR(10000, "系统异常，请稍后重试"),
    UNAUTHORIZED(10401, "签名验证失败"),

    /* 参数错误：10001-19999 */
    PARAM_IS_INVALID(10001, "参数无效"),

    /* 用户错误：20000-29999 */
    VERIFICATION_CODE_INCORRECT(20000, "图形验证码错误"),
    USER_HAS_EXISTED(20001, "用户名已存在"),
    USER_NOT_FOUND(20002, "用户名不存在"),
    PWD_INCORRECT(20003, "密码不正确"),
    PWD_OR_USERNAME_INCORRECT(20004, "密码或用户名错误"),
    IDENTITY_ERR(20005, "身份异常"),

    /* 请求错误 */
    BAD_STUDENT_INFO(30000, "学生信息有误");

    private Integer code;
    private String message;

    ResultCode(Integer code, String msg) {
        this.code = code;
        this.message = msg;
    }

    public Integer code() {
        return code;
    }

    public String message() {
        return this.message;
    }
}
