package com.guruguruzom.security.handler;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

//import kr.liferecord.disease.service.mapper.PractitionerMapper;

@Component
public class LoginSuccessHandler extends SavedRequestAwareAuthenticationSuccessHandler {

	private static final Logger LOGGER = LoggerFactory.getLogger(LoginSuccessHandler.class);
	
	//@Autowired private PractitionerMapper practitionerMapper;
	
	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
			Authentication authentication) throws IOException, ServletException {
		
		LOGGER.info("login success");
		
		String username = request.getParameter("username");
		
		//practitionerMapper.updateSuccessFailCountByUsername(username);
		
		String contextPath = request.getContextPath();
		String uri = contextPath+"/result";
		
//		super.onAuthenticationSuccess(request, response, authentication);
		
		response.sendRedirect(uri);
	}
}
