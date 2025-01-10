package com.mx.dev.blog.spring_001_blog.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.mx.dev.blog.spring_001_blog.entities.user.UserEntity;
import com.mx.dev.blog.spring_001_blog.utils.dtos.user.UserInfoCardDTO;
import com.mx.dev.blog.spring_001_blog.utils.dtos.user.UserInfoDTO;
import com.mx.dev.blog.spring_001_blog.utils.dtos.user.UserTopDTO;

public interface UserRepository extends JpaRepository<UserEntity, Long> {

	@Query("SELECT CASE WHEN COUNT(u) > 0 THEN TRUE ELSE FALSE END FROM UserEntity u WHERE u.email = :email")
	boolean existsByEmail(@Param("email") String email);

	@Query("SELECT CASE WHEN COUNT(u) > 0 THEN TRUE ELSE FALSE END FROM UserEntity u WHERE u.username = :username")
	boolean existsByUsername(@Param("username") String username);

	@Query("SELECT u FROM UserEntity u WHERE u.id IN :ids")
	List<UserEntity> findByIds(@Param("ids") List<Long> ids);

	@Query("select ue from UserEntity ue where ue.email = :email")
	Optional<UserEntity> findUserByEmail(@Param("email") String email);

	@Query("select ue from UserEntity ue where ue.username = :username")
	Optional<UserEntity> findUserByUsername(@Param("username") String username);

	@Query("""
			    SELECT new com.mx.dev.blog.spring_001_blog.utils.dtos.user.UserInfoDTO(
			        u.userId,
			        u.username,
			        u.email,
			        ui.bio,
			        ui.work,
			        ui.education,
			        ui.city,
			        ui.profilePicture,
			        ui.skills,
			        COUNT(DISTINCT b.blogId) AS blogsNumber,
			        COUNT(DISTINCT bul.id.blogId) AS likesNumber,
			        COUNT(DISTINCT uf.id.followerId) AS followers,
			        u.createdAt,
			        ui.website
			    )
			    FROM UserEntity u
			    LEFT JOIN UserInfoEntity ui ON u.userId = ui.userId
			    LEFT JOIN BlogEntity b ON u.userId = b.userId
			    LEFT JOIN BlogUserLikeEntity bul ON u.userId = bul.id.userId
			    LEFT JOIN UserFollowsEntity uf ON u.userId = uf.id.followedId
			    WHERE u.userId = :userId
			    GROUP BY u.userId, ui.bio, ui.work, ui.education, ui.profilePicture, ui.skills, ui.city, ui.website
			""")
	Optional<UserInfoDTO> findUserInfoById(@Param("userId") Long userId);

	@Query("""
			    SELECT new com.mx.dev.blog.spring_001_blog.utils.dtos.user.UserTopDTO(
			        u.userId,
			        u.username,
			        ui.profilePicture,
			        COUNT(b.blogId)
			    )
			    FROM UserEntity u
			    LEFT JOIN BlogEntity b ON u.userId = b.userId AND b.status = 'PUBLISHED'
			    LEFT JOIN UserInfoEntity ui ON u.userId = ui.userId
			    GROUP BY u.userId, u.username, ui.profilePicture
			    ORDER BY COUNT(b.blogId) DESC
			""")
	List<UserTopDTO> getTopUsersByPosts();

	@Query("""
			    SELECT new com.mx.dev.blog.spring_001_blog.utils.dtos.user.UserInfoCardDTO(
			        u.userId,
			        u.username,
			        ui.profilePicture,
			        ui.city,
			        COUNT(DISTINCT b.blogId),
			        COUNT(DISTINCT uf1.id.followerId),
			        COUNT(DISTINCT uf2.id.followedId)
			    )
			    FROM UserEntity u
			    JOIN UserInfoEntity ui ON u.userId = ui.userId
			    LEFT JOIN BlogEntity b ON b.userId = u.userId AND b.status = 'PUBLISHED'
			    LEFT JOIN UserFollowsEntity uf1 ON uf1.id.followedId = u.userId
			    LEFT JOIN UserFollowsEntity uf2 ON uf2.id.followerId = u.userId
			    WHERE u.userId = :userId
			    GROUP BY u.userId, u.username, ui.profilePicture, ui.city
			""")
	UserInfoCardDTO getUserInfoCard(@Param("userId") Long userId);

}
