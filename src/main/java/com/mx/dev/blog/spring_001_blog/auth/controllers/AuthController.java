package com.mx.dev.blog.spring_001_blog.auth.controllers;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import javax.mail.MessagingException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.cors.CorsConfigurationSource;

import com.mx.dev.blog.spring_001_blog.auth.services.auth.AuthService;
import com.mx.dev.blog.spring_001_blog.config.security.CustomUserDetailsService;
import com.mx.dev.blog.spring_001_blog.config.security.JwtTokenProvider;
import com.mx.dev.blog.spring_001_blog.user.entities.UserEntity;
import com.mx.dev.blog.spring_001_blog.user.repositories.UserRepository;
import com.mx.dev.blog.spring_001_blog.utils.dtos.user.JWTAuthResponseDto;
import com.mx.dev.blog.spring_001_blog.utils.dtos.user.LoginDTO;
import com.mx.dev.blog.spring_001_blog.utils.dtos.user.UserAuthSuccessDTO;
import com.mx.dev.blog.spring_001_blog.utils.dtos.user.UserCreateRequestDTO;
import com.mx.dev.blog.spring_001_blog.utils.enums.MethodEnum;
import com.mx.dev.blog.spring_001_blog.utils.enums.ResponseStatus;
import com.mx.dev.blog.spring_001_blog.utils.exceptions.ServiceException;
import com.mx.dev.blog.spring_001_blog.utils.response.ApiResponse;
import com.mx.dev.blog.spring_001_blog.utils.validators.AuthLoginValidator;
import com.mx.dev.blog.spring_001_blog.utils.validators.UserValidator;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

	private final CustomUserDetailsService customUserDetailsService;

	private final CorsConfigurationSource corsConfigurationSource;

	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private JwtTokenProvider jwtTokenProvider;

	@Autowired
	private UserRepository userRepository;

	private final AuthService authService;

	private UserValidator userValidator = new UserValidator();

	private AuthLoginValidator authLoginValidator = new AuthLoginValidator();

	public AuthController(AuthService authService, CorsConfigurationSource corsConfigurationSource,
			CustomUserDetailsService customUserDetailsService) {
		this.authService = authService;
		this.corsConfigurationSource = corsConfigurationSource;
		this.customUserDetailsService = customUserDetailsService;
	}

	@PostMapping("/login")
	public ResponseEntity<?> authenticateUser(@RequestBody LoginDTO loginDTO) throws ServiceException {
		try {
			authLoginValidator.validate(loginDTO);

			// 1. Validar si el usuario existe
			Optional<UserEntity> userEntity = userRepository.findUserByEmail(loginDTO.getEmail());
			if (!userEntity.isPresent()) {
				Map<String, String> errorMap = new HashMap<>();
				errorMap.put("email", "User not found");

				ApiResponse<Map<String, String>> response = new ApiResponse<>(HttpStatus.UNAUTHORIZED.value(),
						"/api/auth", MethodEnum.POST, "Email not found", errorMap, true);

				return new ResponseEntity<>(response, HttpStatus.UNAUTHORIZED);
			}

			System.out.println("************login******************");
			System.out.println(userEntity.get().getConfirm());

			if (userEntity.get().getConfirm() == false) {
				ApiResponse<Map<String, String>> response = new ApiResponse<>(HttpStatus.UNAUTHORIZED.value(),
						"/api/auth", MethodEnum.POST, "This user is not confirmed, please check your email", null,
						true);

				return new ResponseEntity<>(response, HttpStatus.UNAUTHORIZED);
			}

			// 2. Autenticar
			Authentication authentication = authenticationManager
					.authenticate(new UsernamePasswordAuthenticationToken(loginDTO.getEmail(), loginDTO.getPassword()));

			SecurityContextHolder.getContext().setAuthentication(authentication);

			// 3. Generar token
			String token = jwtTokenProvider.generateToken(authentication);

			UserAuthSuccessDTO userAuthLoginSuccessDTO = new UserAuthSuccessDTO(userEntity.get().getUserId(),
					userEntity.get().getUsername(), userEntity.get().getEmail(), new JWTAuthResponseDto(token));

			ApiResponse<UserAuthSuccessDTO> apiResponse = new ApiResponse<>(ResponseStatus.SUCCESS.getHttpStatusCode(),
					"/api/auth", MethodEnum.POST, "Success method POST", userAuthLoginSuccessDTO, false);

			return new ResponseEntity<>(apiResponse, HttpStatus.OK);

		} catch (BadCredentialsException ex) {
			Map<String, String> errorMap = new HashMap<>();
			errorMap.put("password", "Invalid credentials");

			ApiResponse<Map<String, String>> response = new ApiResponse<>(HttpStatus.UNAUTHORIZED.value(), "/api/auth",
					MethodEnum.POST, "Invalid password", errorMap, true);

			return new ResponseEntity<>(response, HttpStatus.UNAUTHORIZED);
		}
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
