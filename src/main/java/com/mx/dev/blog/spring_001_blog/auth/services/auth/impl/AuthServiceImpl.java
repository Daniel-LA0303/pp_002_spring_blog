package com.mx.dev.blog.spring_001_blog.auth.services.auth.impl;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.mx.dev.blog.spring_001_blog.auth.services.auth.AuthService;
import com.mx.dev.blog.spring_001_blog.config.security.JwtTokenProvider;
import com.mx.dev.blog.spring_001_blog.user.services.UserService;
import com.mx.dev.blog.spring_001_blog.utils.dtos.user.LoginDTO;
import com.mx.dev.blog.spring_001_blog.utils.dtos.user.UserAuthSuccessDTO;
import com.mx.dev.blog.spring_001_blog.utils.dtos.user.UserCreateRequestDTO;
import com.mx.dev.blog.spring_001_blog.utils.exceptions.ServiceException;

@Service
public class AuthServiceImpl implements AuthService {

	private final PasswordEncoder passwordEncoder;

	private final JwtTokenProvider jwtTokenProvider;

	private final UserService userService;

	public AuthServiceImpl(PasswordEncoder passwordEncoder, JwtTokenProvider jwtTokenProvider,
			UserService userService) {
		this.passwordEncoder = passwordEncoder;
		this.jwtTokenProvider = jwtTokenProvider;
		this.userService = userService;
	}

	@Override
	public UserAuthSuccessDTO loginUser(LoginDTO loginDTO) throws ServiceException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void registerUser(UserCreateRequestDTO userCreateRequestDTO) throws ServiceException {

		// 1. has password
		String pass = passwordEncoder.encode(userCreateRequestDTO.getPassword());
		userCreateRequestDTO.setPassword(pass);

		// 2. call new user service
		userService.createUser(userCreateRequestDTO);

		// Generar el token para el usuario registrado
		// String token = jwtTokenProvider.generateTokenForUser(userEntity);

		// Crear el DTO de respuesta con la información del usuario y el token generado
		// UserAuthSuccessDTO userAuthLoginSuccessDTO = new
		// UserAuthSuccessDTO(userEntity.getUserId(),
		// userEntity.getUsername(), userEntity.getEmail(), new
		// JWTAuthResponseDto(token));
	}

}
