package com.mx.dev.blog.spring_001_blog.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.mx.dev.blog.spring_001_blog.entities.user.UserInfoEntity;
import com.mx.dev.blog.spring_001_blog.utils.dtos.user.UserUpdateInfoResponseDTO;

public interface UserInfoRepository extends JpaRepository<UserInfoEntity, Long> {

	@Query("SELECT u FROM UserInfoEntity u WHERE u.userId = :userId")
	Optional<UserInfoEntity> findUserInfoByUserId(@Param("userId") Long userId);

	@Query("SELECT new com.mx.dev.blog.spring_001_blog.utils.dtos.user.UserUpdateInfoResponseDTO("
			+ "u.name, u.lastName, u.work, u.education, u.pronouns, u.website, "
			+ "u.direction as address, u.city, u.skills, u.bio) " + "FROM UserInfoEntity u WHERE u.userId = :userId")
	UserUpdateInfoResponseDTO findUserUpdateInfoByUserId(@Param("userId") Long userId);
}
