package com.mx.dev.blog.spring_001_blog.config.security;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;

@Component
public class JwtAuthenticationEntryPonit implements AuthenticationEntryPoint {

	/**
	 * when jwt do not exists or fail validation, we return a SC_UNAUTHORIZED code
	 */
	@Override
	public void commence(HttpServletRequest request, HttpServletResponse response,
			AuthenticationException authException) throws IOException, ServletException {

		response.setContentType("application/json");
		response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);

		Map<String, Object> errorDetails = new HashMap<>();
		errorDetails.put("message", "Authentication failed. You need to login to access this resource.");
		errorDetails.put("status", HttpServletResponse.SC_UNAUTHORIZED);
		errorDetails.put("path", request.getRequestURI());
		errorDetails.put("method", request.getMethod());
		// errorDetails.put("timestamp", LocalDateTime.now());

		ObjectMapper mapper = new ObjectMapper();
		response.getWriter().write(mapper.writeValueAsString(errorDetails));
	}
}
