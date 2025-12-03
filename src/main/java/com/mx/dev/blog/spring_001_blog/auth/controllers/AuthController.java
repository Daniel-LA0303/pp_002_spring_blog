package com.mx.dev.blog.spring_001_blog.auth.controllers;

import java.io.UnsupportedEncodingException;

import javax.mail.MessagingException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mx.dev.blog.spring_001_blog.auth.services.auth.AuthService;
import com.mx.dev.blog.spring_001_blog.auth.utils.dto.EmailRecueratePasswordDTO;
import com.mx.dev.blog.spring_001_blog.utils.dtos.user.LoginDTO;
import com.mx.dev.blog.spring_001_blog.utils.dtos.user.UserAuthSuccessDTO;
import com.mx.dev.blog.spring_001_blog.utils.dtos.user.UserCreateRequestDTO;
import com.mx.dev.blog.spring_001_blog.utils.enums.MethodEnum;
import com.mx.dev.blog.spring_001_blog.utils.enums.ResponseStatus;
import com.mx.dev.blog.spring_001_blog.utils.exceptions.ServiceException;
import com.mx.dev.blog.spring_001_blog.utils.response.ApiResponse;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

	private final AuthService authService;

	public AuthController(AuthService authService) {
		this.authService = authService;
	}

	@PostMapping("/login")
	public ResponseEntity<?> authenticateUser(@RequestBody LoginDTO loginDTO) throws ServiceException {

		UserAuthSuccessDTO data = authService.loginUser(loginDTO);

		ApiResponse<UserAuthSuccessDTO> response = new ApiResponse<>(ResponseStatus.SUCCESS.getHttpStatusCode(),
				"/api/auth", MethodEnum.POST, "Login successful", data, false);

		return ResponseEntity.ok(response);
	}

	@PostMapping("/confirm-user/{token}")
	public ResponseEntity<?> confirmUser(@PathVariable String token)
			throws ServiceException, UnsupportedEncodingException, MessagingException {

		// 1. call service
		authService.confirmUser(token);
		// 2. build response
		ApiResponse<UserAuthSuccessDTO> apiResponse = new ApiResponse<>(ResponseStatus.CREATED.getHttpStatusCode(),
				"/api/user", MethodEnum.POST, "User successfully confirmed, please login to public blogs!", null,
				false);

		return new ResponseEntity<>(apiResponse, HttpStatus.OK);
	}

	@PostMapping("/reset-password-confirm")
	public ResponseEntity<?> restePasswordPost(@RequestBody EmailRecueratePasswordDTO emailRecueratePasswordDTO)
			throws ServiceException, UnsupportedEncodingException, MessagingException {

		// 1. call service
		authService.recuperatePasswordConfirm(emailRecueratePasswordDTO);

		// 2. build response
		ApiResponse<UserAuthSuccessDTO> apiResponse = new ApiResponse<>(ResponseStatus.CREATED.getHttpStatusCode(),
				"/api/user", MethodEnum.POST, "New passowrd changed please login.", null, false);

		return new ResponseEntity<>(apiResponse, HttpStatus.CREATED);
	}

	@PostMapping("/reset-password-post/{email}")
	public ResponseEntity<?> restePasswordPost(@PathVariable String email)
			throws ServiceException, UnsupportedEncodingException, MessagingException {

		// 1. call service
		authService.recuparatePassword(email);

		// 2. build response
		ApiResponse<UserAuthSuccessDTO> apiResponse = new ApiResponse<>(ResponseStatus.CREATED.getHttpStatusCode(),
				"/api/user", MethodEnum.POST, "Request to cheange password accept, please check your email.", null,
				false);

		return new ResponseEntity<>(apiResponse, HttpStatus.CREATED);
	}

	@PostMapping("/register")
	public ResponseEntity<?> saveUser(@RequestBody UserCreateRequestDTO userCreateRequestDTO)
			throws ServiceException, UnsupportedEncodingException, MessagingException {

		// 1. call service
		authService.registerUser(userCreateRequestDTO);

		// 2. build response
		ApiResponse<UserAuthSuccessDTO> apiResponse = new ApiResponse<>(ResponseStatus.CREATED.getHttpStatusCode(),
				"/api/user", MethodEnum.POST,
				"User successfully registered, please check your email to confirm this account", null, false);

		return new ResponseEntity<>(apiResponse, HttpStatus.CREATED);
	}

}
