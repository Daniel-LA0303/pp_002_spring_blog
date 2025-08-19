package com.mx.dev.blog.spring_001_blog.utils.mappers;

import java.util.List;
import java.util.stream.Collectors;

import com.mx.dev.blog.spring_001_blog.user.entities.UserEntity;
import com.mx.dev.blog.spring_001_blog.user.entities.UserInfoEntity;
import com.mx.dev.blog.spring_001_blog.utils.dtos.user.UserSimpleResponseDTO;
import com.mx.dev.blog.spring_001_blog.utils.dtos.user.UserUpdateInfoRequestDTO;

public class UserMappers {

	private UserMappers() {
		throw new IllegalStateException("Utility class");
	}

	public static List<UserSimpleResponseDTO> toListUserSimpleResponseDTO(List<UserEntity> userEntities) {
		return userEntities.stream().map(UserMappers::toUserSimpleResponseDTO).collect(Collectors.toList());
	}

	public static UserInfoEntity toUserInfoEntity(UserUpdateInfoRequestDTO userUpdateInfoRequestDTO,
			UserInfoEntity userInfoEntityToMap) {

		UserInfoEntity userInfoEntity = new UserInfoEntity();

		userInfoEntity.setBio(userUpdateInfoRequestDTO.getBio());
		userInfoEntity.setCity(userUpdateInfoRequestDTO.getCity());
		userInfoEntity.setDirection(userUpdateInfoRequestDTO.getAddress());
		userInfoEntity.setEducation(userUpdateInfoRequestDTO.getEducation());
		userInfoEntity.setLastName(userUpdateInfoRequestDTO.getLastName());
		userInfoEntity.setName(userUpdateInfoRequestDTO.getName());
		userInfoEntity.setPronouns(userUpdateInfoRequestDTO.getPronouns());
		userInfoEntity.setSkills(userUpdateInfoRequestDTO.getSkills());
		userInfoEntity.setWork(userUpdateInfoRequestDTO.getWork());
		userInfoEntity.setWebsite(userUpdateInfoRequestDTO.getWebsite());

		// no change data
		userInfoEntity.setIsActive(userInfoEntityToMap.getIsActive());
		userInfoEntity.setLastLogin(userInfoEntityToMap.getLastLogin());
		userInfoEntity.setPhone(userInfoEntityToMap.getPhone());
		userInfoEntity.setProfilePicture(userInfoEntityToMap.getProfilePicture());
		userInfoEntity.setUserId(userInfoEntityToMap.getUserId());
		userInfoEntity.setUserInfoId(userInfoEntityToMap.getUserInfoId());

		return userInfoEntity;
	}

	public static UserSimpleResponseDTO toUserSimpleResponseDTO(UserEntity userEntity) {

		UserSimpleResponseDTO userDTO = new UserSimpleResponseDTO();
		userDTO.setCreatedAt(userEntity.getCreatedAt());
		userDTO.setEmail(userEntity.getEmail());
		userDTO.setUserId(userEntity.getUserId());
		userDTO.setUsername(userEntity.getUsername());

		return userDTO;
	}

}
