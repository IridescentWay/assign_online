package com.vere.assign_online.common.filter;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.vere.assign_online.common.Result;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * ClassName:RestAuthenticationEntryPoint
 * Package:com.vere.demo.common.exception
 * Description:
 *
 * @Date:2022/4/18 16:28
 * @Author:3046990030@qq.com
 */

@Component
public class RestAuthenticationEntryPoint implements AuthenticationEntryPoint {
    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException {
        //设置响应编码
        response.setCharacterEncoding("UTF-8");
        //设置响应格式，前后端分离最好是json
        response.setContentType("application/json");
        //获取response输出流
        PrintWriter out = response.getWriter();
        //创建返回结果对象
        Result result = Result.fail(401, "请登录！！！");
        //以json字符串形式放到response输出流中
        out.write(new ObjectMapper().writeValueAsString(result));
        out.flush();
        out.close();
    }
}
