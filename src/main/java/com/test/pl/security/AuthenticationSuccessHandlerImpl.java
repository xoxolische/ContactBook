package com.test.pl.security;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.security.web.savedrequest.HttpSessionRequestCache;
import org.springframework.security.web.savedrequest.SavedRequest;
import org.springframework.stereotype.Component;

import com.test.pl.model.UserAuth;

@Component
public class AuthenticationSuccessHandlerImpl extends SavedRequestAwareAuthenticationSuccessHandler implements AuthenticationSuccessHandler {

//	@Autowired
//	private OpinionMarkService opMarkService;
//
//	@Autowired
//	private VersusMarkService versusMarkService;

	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication auth)
			throws IOException, ServletException {
		SavedRequest savedRequest = new HttpSessionRequestCache().getRequest(request, response);
		UserAuth userAuth = (UserAuth) auth.getPrincipal();
		HttpSession session = request.getSession();
		session.setAttribute("currentUserId", userAuth.getId());
		if(savedRequest != null && savedRequest.getRedirectUrl() != null) {
			response.sendRedirect(savedRequest.getRedirectUrl());
		}else {			
			response.sendRedirect(request.getContextPath()+"/home");
		}
		
	}

}
