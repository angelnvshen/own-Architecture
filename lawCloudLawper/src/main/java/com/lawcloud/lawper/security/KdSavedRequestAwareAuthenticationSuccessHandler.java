package com.lawcloud.lawper.security;

import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class KdSavedRequestAwareAuthenticationSuccessHandler extends SavedRequestAwareAuthenticationSuccessHandler {

	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
                                        Authentication authentication) throws ServletException, IOException {
		 HttpSession session = request.getSession(false);

         if (session != null ) {
             request.getSession().setAttribute(KdSimpleUrlAuthenticationFailureHandler.SESSION_FAIL_TIMES, 0);
         }
		super.onAuthenticationSuccess(request, response, authentication);
	}
	

}
