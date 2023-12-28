package com.vere.assign_online.common.handler;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.vere.assign_online.common.Result;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * ClassName:RestAccessDeniedHandler
 * Package:com.vere.demo.common.exception
 * Description:
 *
 * @Date:2022/4/18 16:23
 * @Author:3046990030@qq.com
 */

@Component
public class RestAccessDeniedHandler implements AccessDeniedHandler {
    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException accessDeniedException) throws IOException, ServletException {
        //设置响应编码
        response.setCharacterEncoding("UTF-8");
        //设置响应格式，前后端分离最好是json
        response.setContentType("application/json");
        //获取response输出流
        PrintWriter out = response.getWriter();
        //创建返回结果对象
        Result result = Result.fail(403, "权限不足，请联系管理员");
        //以json字符串形式放到response输出流中
        out.write(new ObjectMapper().writeValueAsString(result));
        out.flush();
        out.close();
    }
}
