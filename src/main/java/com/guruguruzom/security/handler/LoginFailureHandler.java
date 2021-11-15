package com.guruguruzom.security.handler;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;


@Component
public class LoginFailureHandler implements AuthenticationFailureHandler {
	
	//@Autowired private PractitionerMapper practitionerMapper;

	@Override
	public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response,
			AuthenticationException exception) throws IOException, ServletException {
		
		String username = request.getParameter("username");
	
		//String msg = loginFailureCount(username);
		String msg = "·Î±×ÀÎ";
		
		String contextPath = request.getContextPath();
		String uri = contextPath + "/login?msg=" + msg;
		
		response.sendRedirect(uri);
		
	}
	
//	public String loginFailureCount(String username) {
//		Integer failCount = practitionerMapper.findFailCountByUsername(username);
//		if(failCount != null) {
//			if(failCount >= 5) {
//				return "locked";
//			} else {
//				practitionerMapper.updateFailCountByUsername(username);
//				return "failure";
//			}
//		} else {
//			return "failure";
//		}
//	}
}
