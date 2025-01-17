package com.mx.dev.blog.spring_001_blog.services;

import java.util.List;

import com.mx.dev.blog.spring_001_blog.entities.user.UserEntity;
import com.mx.dev.blog.spring_001_blog.utils.dtos.user.UserCreateRequestDTO;
import com.mx.dev.blog.spring_001_blog.utils.dtos.user.UserFullEngagementDTO;
import com.mx.dev.blog.spring_001_blog.utils.dtos.user.UserInfoDTO;
import com.mx.dev.blog.spring_001_blog.utils.dtos.user.UserSimpleResponseDTO;
import com.mx.dev.blog.spring_001_blog.utils.dtos.user.UserUpdateInfoRequestDTO;
import com.mx.dev.blog.spring_001_blog.utils.dtos.user.UserUpdateInfoResponseDTO;
import com.mx.dev.blog.spring_001_blog.utils.exceptions.ServiceException;

public interface UserService {

	UserEntity createUser(UserCreateRequestDTO userCreateRequestDTO) throws ServiceException;

	List<UserSimpleResponseDTO> getAllUsers();

	UserEntity getOneUserByEmailOrThrow(String email) throws ServiceException;

	UserEntity getOneUserByUsernameOrThrow(String username) throws ServiceException;

	UserUpdateInfoResponseDTO getOneUserInfoToUpdate(Long id) throws ServiceException;

	UserEntity getOneUserOrThrow(Long id) throws ServiceException;

	UserSimpleResponseDTO getOneUserSimpleInfo(Long id) throws ServiceException;

	UserInfoDTO getOneUserWithInfo(Long id) throws ServiceException;

	UserFullEngagementDTO getUserFullEngagement(Long userId) throws ServiceException;

	void updateUserInfo(UserUpdateInfoRequestDTO userUpdateInfoRequestDTO, Long userId) throws ServiceException;

}
