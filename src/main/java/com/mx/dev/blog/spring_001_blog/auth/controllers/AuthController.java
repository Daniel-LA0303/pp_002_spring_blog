package com.mx.dev.blog.spring_001_blog.auth.controllers;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mx.dev.blog.spring_001_blog.config.security.JwtTokenProvider;
import com.mx.dev.blog.spring_001_blog.user.entities.UserEntity;
import com.mx.dev.blog.spring_001_blog.user.repositories.UserRepository;
import com.mx.dev.blog.spring_001_blog.user.services.UserService;
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

	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private JwtTokenProvider jwtTokenProvider;

	@Autowired
	private UserService userService;

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private PasswordEncoder passwordEncoder;

	private UserValidator userValidator = new UserValidator();

	private AuthLoginValidator authLoginValidator = new AuthLoginValidator();

	@PostMapping("/login")
	public ResponseEntity<?> authenticateUser(@RequestBody LoginDTO loginDTO) throws ServiceException {
		try {
			authLoginValidator.validate(loginDTO);

			Authentication authentication = authenticationManager
					.authenticate(new org.springframework.security.authentication.UsernamePasswordAuthenticationToken(
							loginDTO.getEmail(), loginDTO.getPassword()));

			SecurityContextHolder.getContext().setAuthentication(authentication);

			String token = jwtTokenProvider.generateToken(authentication);

			String email = authentication.getName();
			Optional<UserEntity> userEntity = userRepository.findUserByEmail(email);

			if (!userEntity.isPresent()) {
				Map<String, String> errorMap = new HashMap<>();
				errorMap.put("email", "User not found");

				ApiResponse<Map<String, String>> response = new ApiResponse<>(HttpStatus.UNAUTHORIZED.value(),
						"/api/auth", MethodEnum.POST, "Email not found", errorMap, true);

				return new ResponseEntity<>(response, HttpStatus.UNAUTHORIZED);
			}

			UserAuthSuccessDTO userAuthLoginSuccessDTO = new UserAuthSuccessDTO(userEntity.get().getUserId(),
					userEntity.get().getUsername(), userEntity.get().getEmail(), new JWTAuthResponseDto(token));

			ApiResponse<UserAuthSuccessDTO> apiResponse = new ApiResponse<>(ResponseStatus.SUCCESS.getHttpStatusCode(),
					"/api/auth", MethodEnum.POST, "Success method POST", userAuthLoginSuccessDTO, false);

			return new ResponseEntity<>(apiResponse, HttpStatus.OK);

		} catch (BadCredentialsException ex) {
			Map<String, String> errorMap = new HashMap<>();
			errorMap.put("password", "Invalid credentials");

			ApiResponse<Map<String, String>> response = new ApiResponse<>(
					ResponseStatus.BAD_REQUEST.getHttpStatusCode(), "/api/auth", MethodEnum.POST, "Invalid password",
					errorMap, true);

			return new ResponseEntity<>(response, HttpStatus.UNAUTHORIZED);
		}
	}

	@PostMapping("/register")
	public ResponseEntity<?> saveUser(@RequestBody UserCreateRequestDTO userCreateRequestDTO) throws ServiceException {

		// Validaciones de los datos de entrada
		// userValidator.validate(userCreateRequestDTO);

		// Cifrar la contraseña del usuario
		String pass = passwordEncoder.encode(userCreateRequestDTO.getPassword());
		userCreateRequestDTO.setPassword(pass);

		// Crear el usuario a través del servicio
		UserEntity userEntity = userService.createUser(userCreateRequestDTO);

		// Generar el token para el usuario registrado
		String token = jwtTokenProvider.generateTokenForUser(userEntity);

		// Crear el DTO de respuesta con la información del usuario y el token generado
		UserAuthSuccessDTO userAuthLoginSuccessDTO = new UserAuthSuccessDTO(userEntity.getUserId(),
				userEntity.getUsername(), userEntity.getEmail(), new JWTAuthResponseDto(token) // Incluye el token
																								// generado
		);

		System.out.println("*******Login prepara salida de datos********");

		// Crear la respuesta API con el DTO del usuario registrado y el token
		ApiResponse<UserAuthSuccessDTO> apiResponse = new ApiResponse<>(ResponseStatus.CREATED.getHttpStatusCode(),
				"/api/user", MethodEnum.POST, "User successfully created and token generated", userAuthLoginSuccessDTO,
				false);

		return new ResponseEntity<>(apiResponse, HttpStatus.CREATED);
	}

}
