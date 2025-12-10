package com.mx.dev.blog.spring_001_blog.auth.services.auth.impl;

import java.io.UnsupportedEncodingException;

import javax.mail.MessagingException;
import javax.transaction.Transactional;

import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.mx.dev.blog.spring_001_blog.auth.services.auth.AuthService;
import com.mx.dev.blog.spring_001_blog.auth.services.email.EmailService;
import com.mx.dev.blog.spring_001_blog.auth.utils.dto.EmailDataRegisterDTO;
import com.mx.dev.blog.spring_001_blog.auth.utils.dto.EmailRecueratePasswordDTO;
import com.mx.dev.blog.spring_001_blog.config.security.JwtTokenProvider;
import com.mx.dev.blog.spring_001_blog.user.entities.UserEntity;
import com.mx.dev.blog.spring_001_blog.user.entities.UserInfoEntity;
import com.mx.dev.blog.spring_001_blog.user.repositories.UserInfoRepository;
import com.mx.dev.blog.spring_001_blog.user.repositories.UserRepository;
import com.mx.dev.blog.spring_001_blog.user.services.UserService;
import com.mx.dev.blog.spring_001_blog.utils.dtos.user.JWTAuthResponseDto;
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

	private final AuthenticationManager authenticationManager;

	private final UserService userService;

	private final UserRepository userRepository;

	private final EmailService emailService;

	private final UserInfoRepository userInfoRepository;

	public AuthServiceImpl(PasswordEncoder passwordEncoder, JwtTokenProvider jwtTokenProvider, UserService userService,
			UserRepository userRepository, EmailService emailService, AuthenticationManager authenticationManager,
			UserInfoRepository userInfoRepository) {
		this.passwordEncoder = passwordEncoder;
		this.jwtTokenProvider = jwtTokenProvider;
		this.userService = userService;
		this.userRepository = userRepository;
		this.emailService = emailService;
		this.authenticationManager = authenticationManager;
		this.userInfoRepository = userInfoRepository;
	}

	@Override
	public void confirmUser(String token) throws ServiceException {

		// 1. get a user by token
		UserEntity user = userService.getUserByToken(token);

		// 3. change info
		user.setConfirm(true);
		user.setToken(null);

		userRepository.save(user);

	}

	@Override
	public UserAuthSuccessDTO loginUser(LoginDTO loginDTO) throws ServiceException {

		// 1. validate DTO
		// authLoginValidator.validate(loginDTO);

		// 2. find user
		UserEntity user = userRepository.findUserByEmail(loginDTO.getEmail())
				.orElseThrow(() -> new ServiceException("User not found", HttpStatus.UNAUTHORIZED.value(), "/api/auth",
						MethodEnum.POST));

		// 3. check confirmation
		if (!user.getConfirm()) {
			throw new ServiceException("User is not confirmed, please check your email",
					HttpStatus.UNAUTHORIZED.value(), "/api/auth", MethodEnum.POST);
		}

		// 4. authenticate credentials (solo UNA VEZ y dentro del try)
		Authentication authentication;
		try {
			authentication = authenticationManager
					.authenticate(new UsernamePasswordAuthenticationToken(loginDTO.getEmail(), loginDTO.getPassword()));
		} catch (BadCredentialsException ex) {
			throw new ServiceException("Invalid credentials", HttpStatus.UNAUTHORIZED.value(), "/api/auth",
					MethodEnum.POST);
		}

		// 5. generate token
		String token = jwtTokenProvider.generateToken(authentication);

		// 6. get profile image
		UserInfoEntity userInfoEntity = userInfoRepository.findUserInfoByUserId(user.getUserId())
				.orElseThrow(() -> new ServiceException(
						String.format("User info for user ID '%d' not found", user.getUserId()),
						ResponseStatus.NOT_FOUND.getHttpStatusCode(), "/api/user-info", MethodEnum.PUT));

		// 6. build DTO
		return new UserAuthSuccessDTO(user.getUserId(), user.getUsername(), user.getEmail(),
				new JWTAuthResponseDto(token), userInfoEntity.getProfilePicture());
	}

	@Override
	@Transactional
	public void recuparatePassword(String email)
			throws ServiceException, UnsupportedEncodingException, MessagingException {

		// 1. check if exists email
		UserEntity userEntity = userService.getOneUserByEmailOrThrow(email);

		// 2. valid confirm user
		if (userEntity.getConfirm() == false) {
			throw new ServiceException("This user need confirm accouunt first, please check your email.",
					ResponseStatus.BAD_REQUEST.getHttpStatusCode(), "/api/auth", MethodEnum.POST);
		}

		// 3. create a new token and save
		userEntity.setToken(generateStaticToken());
		userRepository.save(userEntity);

		// 4. send info via email
		emailService.sendPasswordResetEmail(
				new EmailDataRegisterDTO(userEntity.getEmail(), userEntity.getUsername(), userEntity.getToken()));

	}

	@Override
	public void recuperatePasswordConfirm(EmailRecueratePasswordDTO emailRecueratePasswordDTO) throws ServiceException {

		// 1. get a user by token
		UserEntity user = userService.getUserByToken(emailRecueratePasswordDTO.getToken());

		// 2. change password, quit token and save
		String newPass = passwordEncoder.encode(emailRecueratePasswordDTO.getNewPassword());

		user.setPassword(newPass);
		user.setToken(null);

		userRepository.save(user);

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

	private String generateStaticToken() {
		String random = Long.toString((long) (Math.random() * Long.MAX_VALUE), 32);
		String date = Long.toString(System.currentTimeMillis(), 32);
		return random + date;
	}

}
