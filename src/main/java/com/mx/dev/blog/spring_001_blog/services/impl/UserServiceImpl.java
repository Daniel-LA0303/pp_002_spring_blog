package com.mx.dev.blog.spring_001_blog.services.impl;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mx.dev.blog.spring_001_blog.entities.user.RoleEntity;
import com.mx.dev.blog.spring_001_blog.entities.user.UserEntity;
import com.mx.dev.blog.spring_001_blog.entities.user.UserInfoEntity;
import com.mx.dev.blog.spring_001_blog.repositories.RoleRepository;
import com.mx.dev.blog.spring_001_blog.repositories.UserInfoRepository;
import com.mx.dev.blog.spring_001_blog.repositories.UserRepository;
import com.mx.dev.blog.spring_001_blog.services.UserService;
import com.mx.dev.blog.spring_001_blog.utils.dtos.user.UserCreateRequestDTO;
import com.mx.dev.blog.spring_001_blog.utils.dtos.user.UserInfoDTO;
import com.mx.dev.blog.spring_001_blog.utils.dtos.user.UserSimpleResponseDTO;
import com.mx.dev.blog.spring_001_blog.utils.dtos.user.UserUpdateInfoRequestDTO;
import com.mx.dev.blog.spring_001_blog.utils.enums.MethodEnum;
import com.mx.dev.blog.spring_001_blog.utils.enums.ResponseStatus;
import com.mx.dev.blog.spring_001_blog.utils.exceptions.ServiceException;
import com.mx.dev.blog.spring_001_blog.utils.mappers.UserMappers;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private UserInfoRepository userInfoRepository;

	@Autowired
	private RoleRepository roleRepository;

	@Override
	@Transactional
	public UserEntity createUser(UserCreateRequestDTO userCreateRequestDTO) throws ServiceException {

		// 1. Check if username is already used
		if (userRepository.existsByUsername(userCreateRequestDTO.getUsername())) {
			throw new ServiceException(
					String.format("Username '%s' is already taken", userCreateRequestDTO.getUsername()),
					ResponseStatus.BAD_REQUEST.getHttpStatusCode(), "/api/user", MethodEnum.POST);
		}

		// 2. Check if email is already used
		if (userRepository.existsByEmail(userCreateRequestDTO.getEmail())) {
			throw new ServiceException(
					String.format("Email '%s' is already registered", userCreateRequestDTO.getEmail()),
					ResponseStatus.BAD_REQUEST.getHttpStatusCode(), "/api/user", MethodEnum.POST);
		}

		// 3. Create and save the new user
		UserEntity userEntity = new UserEntity();
		userEntity.setUsername(userCreateRequestDTO.getUsername());
		userEntity.setEmail(userCreateRequestDTO.getEmail());
		userEntity.setPassword(userCreateRequestDTO.getPassword());
		userEntity.setCreatedAt(LocalDateTime.now());
		userEntity.setUpdatedAt(LocalDateTime.now());

		// we asigned role
		Optional<RoleEntity> roleEntity = roleRepository.findRoleByName("ROLE_USER");
		userEntity.setRoles(Collections.singleton(roleEntity.get()));

		// 4. Save UserEntity and get generated userId
		UserEntity savedUser = userRepository.save(userEntity);

		// 5. Create associated UserInfoEntity using userId
		UserInfoEntity userInfoEntity = new UserInfoEntity();
		userInfoEntity.setUserId(savedUser.getUserId()); // Set userId in user_info_tbl
		userInfoEntity.setIsActive(false);

		userInfoRepository.save(userInfoEntity);

		return savedUser;
	}

	@Override
	public List<UserSimpleResponseDTO> getAllUsers() {

		return UserMappers.toListUserSimpleResponseDTO(userRepository.findAll());
	}

	@Override
	public UserEntity getOneUserByEmailOrThrow(String email) throws ServiceException {
		return userRepository.findUserByEmail(email)
				.orElseThrow(() -> new ServiceException(String.format("User with email '%s' not found", email),
						ResponseStatus.NOT_FOUND.getHttpStatusCode(), "/api/user", MethodEnum.GET));
	}

	@Override
	public UserEntity getOneUserByUsernameOrThrow(String username) throws ServiceException {
		return userRepository.findUserByUsername(username)
				.orElseThrow(() -> new ServiceException(String.format("User with username '%s' not found", username),
						ResponseStatus.NOT_FOUND.getHttpStatusCode(), "/api/user", MethodEnum.GET));
	}

	@Override
	public UserEntity getOneUserOrThrow(Long id) throws ServiceException {
		return userRepository.findById(id).orElseThrow(() -> new ServiceException("User with ID " + id + " not found.",
				ResponseStatus.NOT_FOUND.getHttpStatusCode(), "/api/users", MethodEnum.GET));
	}

	@Override
	public UserSimpleResponseDTO getOneUserSimpleInfo(Long id) throws ServiceException {

		return UserMappers.toUserSimpleResponseDTO(getOneUserOrThrow(id));
	}

	@Override
	public UserInfoDTO getOneUserWithInfo(Long id) throws ServiceException {

		return userRepository.findUserInfoById(id).orElseThrow(() -> new ServiceException("There was a problem",
				ResponseStatus.NOT_FOUND.getHttpStatusCode(), "/api/user", MethodEnum.GET));
	}

	@Override
	@Transactional
	public UserInfoEntity updateUserInfo(UserUpdateInfoRequestDTO userUpdateInfoRequestDTO, Long userId)
			throws ServiceException {

		// 1. check if user exists
		UserEntity userEntity = getOneUserOrThrow(userId);

		// 2. get user info
		UserInfoEntity userInfoEntity = userInfoRepository.findUserInfoByUserId(userEntity.getUserId())
				.orElseThrow(() -> new ServiceException(String.format("User info for user ID '%d' not found", userId),
						ResponseStatus.NOT_FOUND.getHttpStatusCode(), "/api/user-info", MethodEnum.PUT));

		// 3. update info
		userInfoEntity.setBio(userUpdateInfoRequestDTO.getBio());
		userInfoEntity.setDirection(userUpdateInfoRequestDTO.getDirection());
		userInfoEntity.setPhone(userUpdateInfoRequestDTO.getPhone());
		userInfoEntity.setProfilePicture(userUpdateInfoRequestDTO.getProfilePicture());

		userEntity.setUpdatedAt(LocalDateTime.now());
		userRepository.save(userEntity);

		return userInfoRepository.save(userInfoEntity);
	}

}
