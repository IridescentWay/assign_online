package com.vere.assign_online.common.filter;

import org.springframework.security.web.savedrequest.HttpSessionRequestCache;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.util.ContentCachingRequestWrapper;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * ClassName:CachingContentFilter
 * Package:com.vere.assign_online.common.filter
 * Description: 解决 request 不可重复读问题，不过在 spring security 下好像不起作用
 * 修改为 dto 的方式实现
 * @Date:2022/5/2 10:12
 * @Author:3046990030@qq.com
 */
public class CachingContentFilter extends OncePerRequestFilter {
    private static final String FORM_CONTENT_TYPE = "multipart/form-data";

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        HttpServletRequest requestWrapper = new ContentCachingRequestWrapper(request);
        String contentType = request.getContentType();
        if (contentType != null && contentType.contains(FORM_CONTENT_TYPE)) {
            filterChain.doFilter(request, response);
        } else {
            filterChain.doFilter(requestWrapper, response);
        }
    }
}
