package com.mx.dev.blog.spring_001_blog.config.security;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import com.mx.dev.blog.spring_001_blog.utils.exceptions.ServiceException;

public class JwtAuthenticationFilter extends OncePerRequestFilter {

	@Autowired
	private JwtTokenProvider jwtTokenProvider;

	@Autowired
	private CustomUserDetailsService customUserDetailsService;

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {

		// 1. get token from request
		String token = getTokenFromRequest(request);

		try {
			// 2. we check if token is valid
			if (StringUtils.hasText(token) && jwtTokenProvider.validateToken(token)) {

				// 3. get username from token
				String username = jwtTokenProvider.getUsernameFromToken(token);

				// 4. we get details from a user
				UserDetails userDetails = customUserDetailsService.loadUserByUsername(username);

				// 5. create an auth with details and roles or authorities
				UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(
						userDetails, null, userDetails.getAuthorities());

				// 6. we set data
				authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

				// 7. set an auth
				SecurityContextHolder.getContext().setAuthentication(authentication);
			}
		} catch (UsernameNotFoundException | ServiceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// 8. if there is not errors, then continue filter, if there is a error with jwt
		// then request not filter
		filterChain.doFilter(request, response);

	}

	// quit Bearer from token
	private String getTokenFromRequest(HttpServletRequest request) {
		String bearerToken = request.getHeader("Authorization");
		if (StringUtils.hasText(bearerToken) && bearerToken.startsWith("Bearer ")) {
			return bearerToken.substring(7, bearerToken.length());
		}
		return null;
	}
}