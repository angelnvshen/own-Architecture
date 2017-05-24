package com.lawcloud.lawper.security;

import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.savedrequest.HttpSessionRequestCache;
import org.springframework.security.web.savedrequest.SavedRequest;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by CHANEL on 2017/4/8.
 */
public class AuthenticationSuccessHandlerImpl implements AuthenticationSuccessHandler {

    public void onAuthenticationSuccess(HttpServletRequest request,
                                        HttpServletResponse response, Authentication authentication)
            throws IOException, ServletException {
        String contextPath = request.getContextPath();
        String defaultTargetUrl = contextPath + "/"; // 默认登陆成功的页面
        String redirectUrl = "";//contextPath + "/login?error=1"; // 默认为登陆错误页面
//        response.sendRedirect(request.getContextPath());
        // 重定向到登陆前的页面
        SavedRequest savedRequest = new HttpSessionRequestCache().getRequest(request, response);
        redirectUrl = (savedRequest != null) ? savedRequest.getRedirectUrl() : defaultTargetUrl;
        response.sendRedirect(redirectUrl);
    }
}