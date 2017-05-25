package com.lawcloud.lawper.security;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by CHANEL on 2017/5/25.
 */
public class TestAuthenticationFailureHandler extends SimpleUrlAuthenticationFailureHandler {

    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        request.setAttribute("username",username);
        request.setAttribute("password",password);
        System.out.println();
//        super.setDefaultFailureUrl("/toLogin?auth=failure&username=admin");
        request.getSession().setAttribute("username", username);
//        request.getSession().setAttribute("password", password);
        super.onAuthenticationFailure(request, response, exception);

    }
}
