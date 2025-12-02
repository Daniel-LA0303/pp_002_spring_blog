package com.mx.dev.blog.spring_001_blog.auth.services.auth;

import java.io.UnsupportedEncodingException;

import javax.mail.MessagingException;

import com.mx.dev.blog.spring_001_blog.utils.dtos.user.LoginDTO;
import com.mx.dev.blog.spring_001_blog.utils.dtos.user.UserAuthSuccessDTO;
import com.mx.dev.blog.spring_001_blog.utils.dtos.user.UserCreateRequestDTO;
import com.mx.dev.blog.spring_001_blog.utils.exceptions.ServiceException;

public interface AuthService {

	void confirmUser(String token);

	UserAuthSuccessDTO loginUser(LoginDTO loginDTO) throws ServiceException;

	void registerUser(UserCreateRequestDTO userCreateRequestDTO)
			throws ServiceException, UnsupportedEncodingException, MessagingException;

}
