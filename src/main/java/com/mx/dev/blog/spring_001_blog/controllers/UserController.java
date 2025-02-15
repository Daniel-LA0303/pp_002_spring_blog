package com.mx.dev.blog.spring_001_blog.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.mx.dev.blog.spring_001_blog.entities.user.UserEntity;
import com.mx.dev.blog.spring_001_blog.services.UserService;
import com.mx.dev.blog.spring_001_blog.utils.dtos.user.UserCreateRequestDTO;
import com.mx.dev.blog.spring_001_blog.utils.dtos.user.UserFullEngagementDTO;
import com.mx.dev.blog.spring_001_blog.utils.dtos.user.UserInfoDTO;
import com.mx.dev.blog.spring_001_blog.utils.dtos.user.UserSimpleResponseDTO;
import com.mx.dev.blog.spring_001_blog.utils.dtos.user.UserUpdateInfoRequestDTO;
import com.mx.dev.blog.spring_001_blog.utils.dtos.user.UserUpdateInfoResponseDTO;
import com.mx.dev.blog.spring_001_blog.utils.enums.MethodEnum;
import com.mx.dev.blog.spring_001_blog.utils.enums.ResponseStatus;
import com.mx.dev.blog.spring_001_blog.utils.exceptions.ServiceException;
import com.mx.dev.blog.spring_001_blog.utils.response.ApiResponse;
import com.mx.dev.blog.spring_001_blog.utils.validators.UserInfoValidator;
import com.mx.dev.blog.spring_001_blog.utils.validators.UserValidator;

@RestController
@RequestMapping("/api/user")
@CrossOrigin(origins = "http://localhost:5173", methods = { RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT })
public class UserController {

	@Autowired
	private UserService userService;

	private UserValidator userValidator = new UserValidator();

	private UserInfoValidator userInfoValidator = new UserInfoValidator();

	/**
	 * get all users with a dto
	 * 
	 * @return
	 */
	@GetMapping
	public ResponseEntity<?> getAllUsers() {

		List<UserSimpleResponseDTO> users = userService.getAllUsers();

		ApiResponse<List<UserSimpleResponseDTO>> apiResponse = new ApiResponse<>(
				ResponseStatus.SUCCESS.getHttpStatusCode(), "/api/user", MethodEnum.GET, "Success method GET", users,
				false);

		return new ResponseEntity<>(apiResponse, HttpStatus.OK);
	}

	/**
	 * get one simple user
	 * 
	 * @return
	 * @throws ServiceException
	 */
	@GetMapping("/{userId}")
	public ResponseEntity<?> getOneSimpleUser(@PathVariable Long userId) throws ServiceException {

		UserSimpleResponseDTO user = userService.getOneUserSimpleInfo(userId);

		ApiResponse<UserSimpleResponseDTO> apiResponse = new ApiResponse<>(ResponseStatus.SUCCESS.getHttpStatusCode(),
				"/api/user", MethodEnum.GET, "Success method GET", user, false);

		return new ResponseEntity<>(apiResponse, HttpStatus.OK);
	}

	@GetMapping("/get-user-info-to-update/{userId}")
	public ResponseEntity<?> getOneUpdateUserInfo(@PathVariable Long userId) throws ServiceException {

		UserUpdateInfoResponseDTO userInfo = userService.getOneUserInfoToUpdate(userId);

		ApiResponse<UserUpdateInfoResponseDTO> apiResponse = new ApiResponse<>(
				ResponseStatus.SUCCESS.getHttpStatusCode(), "/api/user", MethodEnum.GET, "Success method GET", userInfo,
				false);

		return new ResponseEntity<>(apiResponse, HttpStatus.OK);
	}

	@GetMapping("/get-user-info/{userId}")
	public ResponseEntity<?> getOneUserWithInfo(@PathVariable Long userId) throws ServiceException {

		UserInfoDTO user = userService.getOneUserWithInfo(userId);

		ApiResponse<UserInfoDTO> apiResponse = new ApiResponse<>(ResponseStatus.SUCCESS.getHttpStatusCode(),
				"/api/user", MethodEnum.GET, "Success method GET", user, false);

		return new ResponseEntity<>(apiResponse, HttpStatus.OK);
	}

	@GetMapping("/get-user-engagement/{userId}")
	public ResponseEntity<?> getUserFullEngagement(@PathVariable Long userId) throws ServiceException {

		UserFullEngagementDTO userFullEngagementDTO = userService.getUserFullEngagement(userId);

		ApiResponse<UserFullEngagementDTO> apiResponse = new ApiResponse<>(ResponseStatus.SUCCESS.getHttpStatusCode(),
				"/api/user", MethodEnum.GET, "Success method GET", userFullEngagementDTO, false);

		return new ResponseEntity<>(apiResponse, HttpStatus.OK);
	}

	@PostMapping
	public ResponseEntity<?> saveUser(@RequestBody UserCreateRequestDTO userCreateRequestDTO) throws ServiceException {

		userValidator.validate(userCreateRequestDTO);

		UserEntity userEntity = userService.createUser(userCreateRequestDTO);

		ApiResponse<UserEntity> apiResponse = new ApiResponse<>(ResponseStatus.CREATED.getHttpStatusCode(), "/api/user",
				MethodEnum.POST, "Success method POST", userEntity, false);

		return new ResponseEntity<>(apiResponse, HttpStatus.OK);
	}

	@PutMapping("/{userId}")
	public ResponseEntity<?> updateUser(@RequestBody UserUpdateInfoRequestDTO userUpdateInfoRequestDTO,
			@PathVariable Long userId) throws ServiceException {

		// userInfoValidator.validate(userUpdateInfoRequestDTO);

		System.out.println("******");
		System.out.println(userUpdateInfoRequestDTO.getName());
		userService.updateUserInfo(userUpdateInfoRequestDTO, userId);

		ApiResponse<String> apiResponse = new ApiResponse<>(ResponseStatus.UPDATED.getHttpStatusCode(), "/api/user",
				MethodEnum.PUT, "Success method PUT", "User info updated.", false);

		return new ResponseEntity<>(apiResponse, HttpStatus.OK);
	}

}
