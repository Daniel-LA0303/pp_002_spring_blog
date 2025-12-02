package com.mx.dev.blog.spring_001_blog.auth.services.auth.impl;

import java.io.UnsupportedEncodingException;
import java.util.Optional;

import javax.mail.MessagingException;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.mx.dev.blog.spring_001_blog.auth.services.auth.AuthService;
import com.mx.dev.blog.spring_001_blog.config.security.JwtTokenProvider;
import com.mx.dev.blog.spring_001_blog.user.entities.UserEntity;
import com.mx.dev.blog.spring_001_blog.user.repositories.UserRepository;
import com.mx.dev.blog.spring_001_blog.user.services.UserService;
import com.mx.dev.blog.spring_001_blog.utils.dtos.user.LoginDTO;
import com.mx.dev.blog.spring_001_blog.utils.dtos.user.UserAuthSuccessDTO;
import com.mx.dev.blog.spring_001_blog.utils.dtos.user.UserCreateRequestDTO;
import com.mx.dev.blog.spring_001_blog.utils.enums.MethodEnum;
import com.mx.dev.blog.spring_001_blog.utils.enums.ResponseStatus;
import com.mx.dev.blog.spring_001_blog.utils.exceptions.ServiceException;

@Service
public class AuthServiceImpl implements AuthService {

	private final PasswordEncoder passwordEncoder;

	private final JwtTokenProvider jwtTokenProvider;

	private final UserService userService;

	private final UserRepository userRepository;

	public AuthServiceImpl(PasswordEncoder passwordEncoder, JwtTokenProvider jwtTokenProvider, UserService userService,
			UserRepository userRepository) {
		this.passwordEncoder = passwordEncoder;
		this.jwtTokenProvider = jwtTokenProvider;
		this.userService = userService;
		this.userRepository = userRepository;
	}

	@Override
	public void confirmUser(String token) {

		// 1. get a user by token
		Optional<UserEntity> user = userService.getUserByToken(token);

		// 2. valid
		if (user.get() == null) {
			new ServiceException("User with this token not found", ResponseStatus.NOT_FOUND.getHttpStatusCode(),
					"/api/user-info", MethodEnum.PUT);
		}

		// 3. change info
		UserEntity userEntity = user.get();
		userEntity.setConfirm(true);
		userEntity.setToken(null);

		userRepository.save(userEntity);

	}

	@Override
	public UserAuthSuccessDTO loginUser(LoginDTO loginDTO) throws ServiceException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void registerUser(UserCreateRequestDTO userCreateRequestDTO)
			throws ServiceException, UnsupportedEncodingException, MessagingException {

		// 1. has password
		String pass = passwordEncoder.encode(userCreateRequestDTO.getPassword());
		userCreateRequestDTO.setPassword(pass);

		// 2. call new user service
		userService.createUser(userCreateRequestDTO);

	}

}
