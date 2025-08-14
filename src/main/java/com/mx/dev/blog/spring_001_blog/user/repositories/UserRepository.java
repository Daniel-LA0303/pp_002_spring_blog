package com.mx.dev.blog.spring_001_blog.user.repositories;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.mx.dev.blog.spring_001_blog.user.entities.UserEntity;
import com.mx.dev.blog.spring_001_blog.utils.dtos.user.UserFullEngagementDTO;
import com.mx.dev.blog.spring_001_blog.utils.dtos.user.UserInfoCardDTO;
import com.mx.dev.blog.spring_001_blog.utils.dtos.user.UserInfoDTO;
import com.mx.dev.blog.spring_001_blog.utils.dtos.user.UserTopDTO;

public interface UserRepository extends JpaRepository<UserEntity, Long> {

	@Transactional
	@Modifying
	@Query("DELETE FROM UserFollowsEntity uf WHERE uf.id.followerId = :followerId AND uf.id.followedId = :followedId")
	void deleteByFollowerIdAndFollowedId(@Param("followerId") Long followerId, @Param("followedId") Long followedId);

	@Query("SELECT CASE WHEN COUNT(u) > 0 THEN TRUE ELSE FALSE END FROM UserEntity u WHERE u.email = :email")
	boolean existsByEmail(@Param("email") String email);

	@Query("SELECT COUNT(uf) > 0 FROM UserFollowsEntity uf WHERE uf.id.followerId = :followerId AND uf.id.followedId = :followedId")
	boolean existsByFollowerIdAndFollowedId(@Param("followerId") Long followerId, @Param("followedId") Long followedId);

	@Query("SELECT CASE WHEN COUNT(u) > 0 THEN TRUE ELSE FALSE END FROM UserEntity u WHERE u.username = :username")
	boolean existsByUsername(@Param("username") String username);

	@Query("SELECT u FROM UserEntity u WHERE u.id IN :ids")
	List<UserEntity> findByIds(@Param("ids") List<Long> ids);

	// search
	@Query("SELECT new com.mx.dev.blog.spring_001_blog.utils.dtos.user.UserInfoCardDTO( "
			+ "u.userId, u.username, ui.profilePicture, ui.city, "
			+ "COUNT(DISTINCT b.blogId), COUNT(DISTINCT uf1.id.followerId), COUNT(DISTINCT uf2.id.followedId)) "
			+ "FROM UserEntity u " + "JOIN UserInfoEntity ui ON u.userId = ui.userId "
			+ "LEFT JOIN BlogEntity b ON b.userId = u.userId AND b.status = 'PUBLISHED' "
			+ "LEFT JOIN UserFollowsEntity uf1 ON uf1.id.followedId = u.userId "
			+ "LEFT JOIN UserFollowsEntity uf2 ON uf2.id.followerId = u.userId "
			+ "WHERE LOWER(u.username) LIKE LOWER(CONCAT('%', :query, '%')) "
			+ "GROUP BY u.userId, u.username, ui.profilePicture, ui.city")
	Page<UserInfoCardDTO> findByUsernameContainingIgnoreCase(@Param("query") String query, Pageable pageable);

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
			        ui.website,
			        COUNT(DISTINCT cuf.id.categoryId) AS categoryFollows
			    )
			    FROM UserEntity u
			    LEFT JOIN UserInfoEntity ui ON u.userId = ui.userId
			    LEFT JOIN BlogEntity b ON u.userId = b.userId
			    LEFT JOIN BlogUserLikeEntity bul ON u.userId = bul.id.userId
			    LEFT JOIN UserFollowsEntity uf ON u.userId = uf.id.followedId
			    LEFT JOIN CategoryUserFollowEntity cuf ON u.userId = cuf.id.userId
			    WHERE u.userId = :userId
			    GROUP BY u.userId, ui.bio, ui.work, ui.education, ui.profilePicture, ui.skills, ui.city, ui.website
			""")
	Optional<UserInfoDTO> findUserInfoById(@Param("userId") Long userId);

	@Query("""
			    SELECT uf.id.followerId
			    FROM UserFollowsEntity uf
			    WHERE uf.id.followedId = :userId
			""")
	List<Long> getFollowersIds(@Param("userId") Long userId);

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
	Page<UserTopDTO> getTopUsersByPosts(Pageable pageable);

	@Query("""
			    SELECT new com.mx.dev.blog.spring_001_blog.utils.dtos.user.UserFullEngagementDTO(
			        COUNT(DISTINCT bt.blogId),
			        COUNT(DISTINCT bult.id.blogId),
			        COUNT(DISTINCT burt.id.blogId),
			        COUNT(DISTINCT ct.commentId),
			        COUNT(DISTINCT CASE WHEN uft.id.followerId = :userId THEN uft.id.followedId END),
			        COUNT(DISTINCT CASE WHEN uft.id.followedId = :userId THEN uft.id.followerId END),
			        COUNT(DISTINCT cuft.id.categoryId)
			    )
			    FROM UserEntity ut
			    LEFT JOIN BlogEntity bt ON ut.userId = bt.userId
			    LEFT JOIN BlogUserLikeEntity bult ON ut.userId = bult.id.userId
			    LEFT JOIN CategoryUserFollowEntity cuft ON ut.userId = cuft.id.userId
			    LEFT JOIN BlogUserReadEntity burt ON ut.userId = burt.id.userId
			    LEFT JOIN CommentEntity ct ON ut.userId = ct.userId
			    LEFT JOIN UserFollowsEntity uft ON ut.userId = uft.id.followerId OR ut.userId = uft.id.followedId
			    WHERE ut.userId = :userId
			""")
	UserFullEngagementDTO getUserEngagementData(@Param("userId") Long userId);

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

	@Query(value = "INSERT INTO user_follows_tbl (follower_id, followed_id, created_at) VALUES (:followerId, :followedId, :createdAt)", nativeQuery = true)
	@Modifying
	void insertUserFollow(@Param("followerId") Long followerId, @Param("followedId") Long followedId,
			@Param("createdAt") LocalDateTime createdAt);

}
