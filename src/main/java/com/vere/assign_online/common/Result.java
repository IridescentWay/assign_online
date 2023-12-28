package com.vere.assign_online.common;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * ClassName:Result
 * Package:com.vere.demo.utils
 * Description:
 *
 * @Date:2022/4/17 22:32
 * @Author:3046990030@qq.com
 */

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Result<T> {
    private Integer status;
    private String desc;
    private T data;

    private void setResultCode(ResultCode code) {
        this.status = code.code();
        this.desc = code.message();
    }

    private void setData(T data) {
        this.data = data;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public static Result suc() {
        Result result = new Result();
        result.setResultCode(ResultCode.SUCCESS);
        return result;
    }

    public static Result suc(Object data) {
        Result result = new Result();
        result.setResultCode(ResultCode.SUCCESS);
        result.setData(data);
        return result;
    }

    public static Result fail(Integer status, String desc) {
        Result result = new Result();
        result.setStatus(status);
        result.setDesc(desc);
        return result;
    }

    public static Result fail(ResultCode resultCode) {
        Result result = new Result();
        result.setResultCode(resultCode);
        return result;
    }
}
