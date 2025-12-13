package com.mx.dev.blog.spring_001_blog.user.services.impl;

import java.io.UnsupportedEncodingException;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.mail.MessagingException;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mx.dev.blog.spring_001_blog.auth.services.email.EmailService;
import com.mx.dev.blog.spring_001_blog.auth.utils.dto.EmailDataRegisterDTO;
import com.mx.dev.blog.spring_001_blog.notifiation.entities.NotificationEntity;
import com.mx.dev.blog.spring_001_blog.notifiation.repository.NotificationRepository;
import com.mx.dev.blog.spring_001_blog.notifiation.services.NotificationService;
import com.mx.dev.blog.spring_001_blog.notifiation.utils.enums.NotificationTargetType;
import com.mx.dev.blog.spring_001_blog.notifiation.utils.enums.NotificationType;
import com.mx.dev.blog.spring_001_blog.user.entities.RoleEntity;
import com.mx.dev.blog.spring_001_blog.user.entities.UserEntity;
import com.mx.dev.blog.spring_001_blog.user.entities.UserInfoEntity;
import com.mx.dev.blog.spring_001_blog.user.repositories.RoleRepository;
import com.mx.dev.blog.spring_001_blog.user.repositories.UserInfoRepository;
import com.mx.dev.blog.spring_001_blog.user.repositories.UserRepository;
import com.mx.dev.blog.spring_001_blog.user.services.UserService;
import com.mx.dev.blog.spring_001_blog.utils.dtos.user.UserCreateRequestDTO;
import com.mx.dev.blog.spring_001_blog.utils.dtos.user.UserFullEngagementDTO;
import com.mx.dev.blog.spring_001_blog.utils.dtos.user.UserInfoCardDTO;
import com.mx.dev.blog.spring_001_blog.utils.dtos.user.UserInfoDTO;
import com.mx.dev.blog.spring_001_blog.utils.dtos.user.UserResponse;
import com.mx.dev.blog.spring_001_blog.utils.dtos.user.UserSearchChatDTO;
import com.mx.dev.blog.spring_001_blog.utils.dtos.user.UserSimpleResponseDTO;
import com.mx.dev.blog.spring_001_blog.utils.dtos.user.UserUpdateInfoRequestDTO;
import com.mx.dev.blog.spring_001_blog.utils.dtos.user.UserUpdateInfoResponseDTO;
import com.mx.dev.blog.spring_001_blog.utils.enums.MethodEnum;
import com.mx.dev.blog.spring_001_blog.utils.enums.ResponseStatus;
import com.mx.dev.blog.spring_001_blog.utils.exceptions.ServiceException;
import com.mx.dev.blog.spring_001_blog.utils.mappers.UserMappers;

@Service
public class UserServiceImpl implements UserService {

	private final UserRepository userRepository;

	private final UserInfoRepository userInfoRepository;

	private final RoleRepository roleRepository;

	private final EmailService emailService;

	private final NotificationService notificationService;

	private final NotificationRepository notificationRepository;

	public UserServiceImpl(UserRepository userRepository, UserInfoRepository userInfoRepository,
			RoleRepository roleRepository, EmailService emailService, NotificationService notificationService,
			NotificationRepository notificationRepository) {
		this.roleRepository = roleRepository;
		this.userInfoRepository = userInfoRepository;
		this.userRepository = userRepository;
		this.emailService = emailService;
		this.notificationService = notificationService;
		this.notificationRepository = notificationRepository;
	}

	@Override
	@Transactional
	public UserEntity createUser(UserCreateRequestDTO userCreateRequestDTO)
			throws ServiceException, UnsupportedEncodingException, MessagingException {

		Map<String, String> errorMap = new HashMap<>();

		// 1. Check if username is already used
		if (userRepository.existsByUsername(userCreateRequestDTO.getUsername())) {
			errorMap.put("username",
					String.format("Username '%s' is already taken", userCreateRequestDTO.getUsername()));
		}

		// 2. Check if email is already used
		if (userRepository.existsByEmail(userCreateRequestDTO.getEmail())) {
			errorMap.put("email", String.format("Email '%s' is already registered", userCreateRequestDTO.getEmail()));
		}

		if (!errorMap.isEmpty()) {
			throw new ServiceException("User registration failed due to validation errors",
					ResponseStatus.BAD_REQUEST.getHttpStatusCode(), "/api/user", MethodEnum.POST, errorMap);
		}

		// 3. Create and save the new user
		UserEntity userEntity = new UserEntity();
		userEntity.setUsername(userCreateRequestDTO.getUsername());
		userEntity.setEmail(userCreateRequestDTO.getEmail());
		userEntity.setPassword(userCreateRequestDTO.getPassword());
		userEntity.setCreatedAt(LocalDateTime.now());
		userEntity.setUpdatedAt(LocalDateTime.now());
		userEntity.setConfirm(false);
		userEntity.setToken(generateStaticToken()); // <- generate a static token to confirm account

		// 4. we asigned role
		Optional<RoleEntity> roleEntity = roleRepository.findRoleByName("ROLE_USER");
		userEntity.setRoles(Collections.singleton(roleEntity.get()));

		// 5. Save UserEntity and get generated userId
		UserEntity savedUser = userRepository.save(userEntity);

		// 6. Create associated UserInfoEntity using userId
		UserInfoEntity userInfoEntity = new UserInfoEntity();
		userInfoEntity.setUserId(savedUser.getUserId());
		userInfoEntity.setIsActive(false);

		userInfoRepository.save(userInfoEntity);

		// 7. build email data
		EmailDataRegisterDTO emailDataRegisterDTO = new EmailDataRegisterDTO(userEntity.getEmail(),
				userEntity.getUsername(), userEntity.getToken());

		// 8. send email
		emailService.sendRegistrationEmail(emailDataRegisterDTO);

		return savedUser;
	}

	@Override
	public List<UserResponse> finAllUsersExceptSelf(Authentication connectedUser) {

		String userEmail = connectedUser.getName();

		return userRepository.findAllUsersExceptSelf(userEmail).stream().map(UserMappers::toUserResponse).toList();
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
	public UserUpdateInfoResponseDTO getOneUserInfoToUpdate(Long id) throws ServiceException {

		// 1. check if user exists
		UserEntity userEntity = getOneUserOrThrow(id);

		// 2. get only info to update
		UserUpdateInfoResponseDTO userUpdateInfoResponseDTO = userInfoRepository
				.findUserUpdateInfoByUserId(userEntity.getUserId());

		return userUpdateInfoResponseDTO;
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

	@Transactional
	@Override
	public UserInfoDTO getOneUserWithInfo(Long id) throws ServiceException {
		// Obtener los seguidores del usuario
		List<Long> usersFollowers = userRepository.getFollowersIds(id);

		UserInfoDTO userInfo = userRepository.findUserInfoById(id)
				.orElseThrow(() -> new ServiceException("There was a problem",
						ResponseStatus.NOT_FOUND.getHttpStatusCode(), "/api/user", MethodEnum.GET));

		userInfo.setUsersFollowers(usersFollowers);

		return userInfo;
	}

	@Override
	public UserEntity getUserByToken(String token) throws ServiceException {

		Optional<UserEntity> user = userRepository.findByToken(token);

		if (!user.isPresent()) {
			throw new ServiceException(
					"User with this token not found, please check your email or send the request again.",
					ResponseStatus.NOT_FOUND.getHttpStatusCode(), "/api/user-info", MethodEnum.PUT);
		}

		return user.get();
	}

	@Override
	public UserFullEngagementDTO getUserFullEngagement(Long userId) throws ServiceException {

		// 1. first we should search user
		UserEntity userEntity = getOneUserOrThrow(userId);

		// 2. if user exists then we search user engagement
		UserFullEngagementDTO userFullEngagementDTO = userRepository.getUserEngagementData(userEntity.getUserId());

		return userFullEngagementDTO;
	}

	@Override
	public List<UserSearchChatDTO> getUserSearchToCreateAChat(String query, String excludeUserId) {

		Pageable pageable = PageRequest.of(0, 8);

		return userRepository.searchUsersForChat(query, excludeUserId, pageable).stream().toList();
	}

	@Override
	public Page<UserInfoCardDTO> searchUsers(String query, int page, int size) {

		Pageable pageable = PageRequest.of(page, size);

		// 1. get user paginated
		Page<UserInfoCardDTO> basePage = userRepository.findByUsernameContainingIgnoreCase(query, pageable);

		// 2. mappers with id user followers
		List<UserInfoCardDTO> enrichedUsers = basePage.getContent().stream().map(user -> {
			List<Long> followerIds = userRepository.getFollowersIds(user.getUserId());
			user.setUsersFollowers(followerIds);
			return user;
		}).toList();

		// 3. return info
		return new PageImpl<>(enrichedUsers, pageable, basePage.getTotalElements());
	}

	@Override
	@Transactional
	public void updateUserInfo(UserUpdateInfoRequestDTO userUpdateInfoRequestDTO, Long userId) throws ServiceException {

		// 1. check if user exists
		UserEntity userEntity = getOneUserOrThrow(userId);

		// 2. get user info
		UserInfoEntity userInfoEntity = userInfoRepository.findUserInfoByUserId(userEntity.getUserId())
				.orElseThrow(() -> new ServiceException(String.format("User info for user ID '%d' not found", userId),
						ResponseStatus.NOT_FOUND.getHttpStatusCode(), "/api/user-info", MethodEnum.PUT));

		System.out.println(userUpdateInfoRequestDTO.getProfilePicture());

		// 3. update info
		UserInfoEntity userEntityToUpdate = UserMappers.toUserInfoEntity(userUpdateInfoRequestDTO, userInfoEntity);

		userEntity.setUpdatedAt(LocalDateTime.now());
		userRepository.save(userEntity);
		userInfoRepository.save(userEntityToUpdate);

	}

	@Override
	@Transactional
	public void userFollowed(Long followerId, Long followedId) throws ServiceException {

		// 1. Prevent duplicate follow
		if (userRepository.existsByFollowerIdAndFollowedId(followerId, followedId)) {
			throw new ServiceException("User is already following this user.",
					ResponseStatus.BAD_REQUEST.getHttpStatusCode(), "/api/follow", MethodEnum.POST);
		}

		// 2. Fetch follower and followed users
		UserEntity follower = getOneUserOrThrow(followerId);
		UserEntity followed = getOneUserOrThrow(followedId);

		// 3. Prevent following yourself
		if (followerId.equals(followedId)) {
			throw new ServiceException("You cannot follow yourself.", ResponseStatus.NOT_FOUND.getHttpStatusCode(),
					"/api/users", MethodEnum.GET);
		}

		// 4. Check if follow notification already exists
		Optional<NotificationEntity> notificationExists = notificationRepository
				.findNotificationByUserFromIdAndTargetId(followerId, followedId);

		if (!notificationExists.isPresent()) {
			// Build new notification
			NotificationEntity notification = new NotificationEntity();
			notification.setContent(follower.getUsername() + " started following you.");
			notification.setDelivered(false);
			notification.setCreatedAt(LocalDateTime.now());
			notification.setNotificationType(NotificationType.FOLLOW);
			notification.setRead(false);
			notification.setUserFromId(follower.getUserId());
			notification.setUserToId(followed.getUserId());
			notification.setUpdatedAt(LocalDateTime.now());

			// Set target type and ID
			notification.setTargetId(follower.getUserId());
			notification.setTargetType(NotificationTargetType.USER);
			notification.setTargetExtra(null);

			// Persist the notification
			notificationService.createNotificationStorage(notification);
		}

		// 5. Insert follow in database
		userRepository.insertUserFollow(followerId, followedId, LocalDateTime.now());
	}

	@Override
	public void userUnfollowed(Long followerId, Long followedId) throws ServiceException {

		if (!userRepository.existsByFollowerIdAndFollowedId(followerId, followedId)) {
			throw new ServiceException("User is not following this user.",
					ResponseStatus.BAD_REQUEST.getHttpStatusCode(), "/api/follow", MethodEnum.DELETE);
		}

		userRepository.deleteByFollowerIdAndFollowedId(followerId, followedId);

	}

	private String generateStaticToken() {
		String random = Long.toString((long) (Math.random() * Long.MAX_VALUE), 32);
		String date = Long.toString(System.currentTimeMillis(), 32);
		return random + date;
	}

}
