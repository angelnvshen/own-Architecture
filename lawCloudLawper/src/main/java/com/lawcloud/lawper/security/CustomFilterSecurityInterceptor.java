package com.lawcloud.lawper.security;

import org.springframework.security.web.access.intercept.FilterSecurityInterceptor;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

public class CustomFilterSecurityInterceptor extends FilterSecurityInterceptor {

    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {

        HttpServletRequest httpRequest = (HttpServletRequest) request;

        // 过资源(URL)白名单：如果为公共页面，直接执行
        if (SecurityMetadataSourceTrustListHolder.isTrustSecurityMetadataSource(httpRequest)) {
            chain.doFilter(request, response);
            return;
        }

        super.doFilter(httpRequest, response, chain);
    }

}