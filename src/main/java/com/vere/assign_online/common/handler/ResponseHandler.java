package com.vere.assign_online.common.handler;

import com.alibaba.fastjson.JSON;
import com.vere.assign_online.common.Result;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

/**
 * ClassName:ResponseHandler
 * Package:com.vere.demo.utils
 * Description:
 *
 * @Date:2022/4/18 10:40
 * @Author:3046990030@qq.com
 */

@RestControllerAdvice(basePackages = "com.vere.assign_online.controller")
public class ResponseHandler implements ResponseBodyAdvice {
    @Override
    public boolean supports(MethodParameter returnType, Class converterType) {
        return true;
    }

    @Override
    public Object beforeBodyWrite(Object body, MethodParameter returnType, MediaType selectedContentType, Class selectedConverterType, ServerHttpRequest request, ServerHttpResponse response) {
        if (body instanceof String) {
            return JSON.toJSONString(Result.suc(body));
        }
        return Result.suc(body);
    }
}
