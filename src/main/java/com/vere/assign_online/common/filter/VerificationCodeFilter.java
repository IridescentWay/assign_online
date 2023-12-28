package com.vere.assign_online.common.filter;

import com.vere.assign_online.common.exception.VerificationCodeException;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * ClassName:VerificationCodeFilter
 * Package:com.vere.demo.common.exception
 * Description:
 *
 * @Date:2022/4/19 22:53
 * @Author:3046990030@qq.com
 */
public class VerificationCodeFilter extends OncePerRequestFilter {
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        // 非登录请求不去校验验证码
        if ("/student/login".equals(request.getRequestURI()) || "/teacher/login".equals(request.getRequestURI())) {
            // 验证验证码的正确与否, 测试期间不管
            // verificationCode(request);
        }
        filterChain.doFilter(request, response);
    }

    private void verificationCode(HttpServletRequest request) throws VerificationCodeException {
        // 在form表单中获取对应输入的值
        String captcha = request.getParameter("captcha");
        System.out.println("验证码拦截器：" + request.getSession().getId());
        HttpSession session = request.getSession();
        String saveCode = (String) session.getAttribute("captcha");
        if (StringUtils.hasLength(saveCode)) {
            session.removeAttribute("captcha");
        }
        System.out.println(captcha + " " + saveCode);
        if (!StringUtils.hasLength(captcha) || !StringUtils.hasLength(saveCode) || !captcha.equals(saveCode)) {
            throw new VerificationCodeException();
        }
    }
}
