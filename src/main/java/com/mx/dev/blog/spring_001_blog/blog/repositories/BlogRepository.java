package com.mx.dev.blog.spring_001_blog.blog.repositories;

import java.time.LocalDateTime;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.mx.dev.blog.spring_001_blog.blog.entities.BlogEntity;
import com.mx.dev.blog.spring_001_blog.blog.utils.dto.BlogEngagementDTO;
import com.mx.dev.blog.spring_001_blog.category.entities.CategoryEntity;
import com.mx.dev.blog.spring_001_blog.user.entities.UserEntity;

public interface BlogRepository extends JpaRepository<BlogEntity, Long> {

	// delete like from table
	@Transactional
	@Modifying
	@Query("DELETE FROM BlogUserLikeEntity bul WHERE bul.id.userId = :userId AND bul.id.blogId = :blogId")
	void deleteByUserIdAndBlogId(@Param("userId") Long userId, @Param("blogId") Long blogId);

	// delete read later from table
	@Transactional
	@Modifying
	@Query("DELETE FROM BlogUserReadEntity bur WHERE bur.id.userId = :userId AND bur.id.blogId = :blogId")
	void deleteByUserIdAndBlogIdRead(@Param("userId") Long userId, @Param("blogId") Long blogId);

	// exists blog by slug
	@Query("SELECT COUNT(b) > 0 FROM BlogEntity b WHERE b.slug = :slug")
	boolean existsBySlug(String slug);

	// check if exists like
	@Query("SELECT COUNT(bul) > 0 FROM BlogUserLikeEntity bul WHERE bul.id.userId = :userId AND bul.id.blogId = :blogId")
	boolean existsByUserIdAndBlogId(@Param("userId") Long userId, @Param("blogId") Long blogId);

	// check if exists read later
	@Query("SELECT COUNT(bur) > 0 FROM BlogUserReadEntity bur WHERE bur.id.userId = :userId AND bur.id.blogId = :blogId")
	boolean existsByUserIdAndBlogIdRead(@Param("userId") Long userId, @Param("blogId") Long blogId);

	// get blogs by category pageables
	@Query("SELECT b FROM BlogEntity b " + "JOIN CategoryBlogEntity bc ON b.blogId = bc.id.blogId "
			+ "JOIN CategoryEntity c ON bc.id.categoryId = c.categoryId " + "WHERE c.name = :categoryName "
			+ "ORDER BY b.createdAt DESC")
	Page<BlogEntity> findBlogsByCategoryName(@Param("categoryName") String categoryName, Pageable pageable);

	// get blogs by read later by user pageables
	@Query("""
				SELECT b
			    FROM BlogEntity b
			    JOIN BlogUserReadEntity bure ON b.blogId = bure.id.blogId
			    WHERE bure.id.userId = :userId
			    ORDER BY bure.createdAt DESC
			""")
	Page<BlogEntity> findBlogsByReadeLaterByUser(@Param("userId") Long userId, Pageable pageable);

	//

	// get blogs by user pageables
	@Query("SELECT b FROM BlogEntity b WHERE b.userId = :userId ORDER BY b.createdAt DESC")
	Page<BlogEntity> findBlogsByUserId(@Param("userId") Long userId, Pageable pageable);

	// get blogs liked by user pageables
	@Query("""
			    SELECT b
			    FROM BlogEntity b
			    JOIN BlogUserLikeEntity bult ON b.blogId = bult.id.blogId
			    WHERE bult.id.userId = :userId
			    ORDER BY bult.createdAt DESC
			""")
	Page<BlogEntity> findBlogsLikedByUser(@Param("userId") Long userId, Pageable pageable);

	// get categories by user followed pageables
	@Query("""
				SELECT c
			    FROM CategoryEntity c
			    JOIN CategoryUserFollowEntity cufe ON c.categoryId = cufe.id.categoryId
			    WHERE cufe.id.userId = :userId
			    ORDER BY cufe.createdAt DESC
			""")
	Page<CategoryEntity> findCategoriesByFollowedUser(@Param("userId") Long userId, Pageable pageable);

	// get followed from one user by id pageable
	@Query("""
				SELECT u
			    FROM UserEntity u
			    JOIN UserFollowsEntity ufe ON u.userId = ufe.id.followedId
			    WHERE ufe.id.followerId = :userId
			    ORDER BY ufe.createdAt DESC
			""")
	Page<UserEntity> findUserFollowedsByUserId(@Param("userId") Long userId, Pageable pageable);

	// get followers from one user by id pageable
	@Query("""
				SELECT u
			    FROM UserEntity u
			    JOIN UserFollowsEntity ufe ON u.userId = ufe.id.followerId
			    WHERE ufe.id.followedId = :userId
			    ORDER BY ufe.createdAt DESC
			""")
	Page<UserEntity> findUserFollowersByUserId(@Param("userId") Long userId, Pageable pageable);

	// get a list of ids from likes
	@Query("SELECT b.id.userId FROM BlogUserLikeEntity b WHERE b.id.blogId = :blogId")
	List<Long> findUserIdsLikeByBlogId(@Param("blogId") Long blogId);

	// find ids
	@Query("SELECT b.id.blogId, b.id.userId FROM BlogUserLikeEntity b WHERE b.id.blogId IN :blogIds")
	List<Object[]> findUserIdsLikeByBlogIds(@Param("blogIds") List<Long> blogIds);

	// get a list of ids from read later
	@Query("SELECT b.id.userId FROM BlogUserReadEntity b WHERE b.id.blogId = :blogId")
	List<Long> findUserIdsReadByBlogId(@Param("blogId") Long blogId);

	// find ids
	@Query("SELECT b.id.blogId, b.id.userId FROM BlogUserReadEntity b WHERE b.id.blogId IN :blogIds")
	List<Object[]> findUserIdsReadByBlogIds(@Param("blogIds") List<Long> blogIds);

	// get engagement data from one blog by id
	@Query("SELECT new com.mx.dev.blog.spring_001_blog.blog.utils.dto.BlogEngagementDTO(" + "COALESCE(b.id, 0), "
			+ "COALESCE(COUNT(DISTINCT bult.id.userId), 0), " + "COALESCE(COUNT(DISTINCT c.commentId), 0), "
			+ "COALESCE(COUNT(DISTINCT burt.id.userId), 0)) "
			+ "FROM com.mx.dev.blog.spring_001_blog.blog.entities.BlogEntity b "
			+ "LEFT JOIN com.mx.dev.blog.spring_001_blog.comment.entities.CommentEntity c ON b.id = c.blogId "
			+ "LEFT JOIN com.mx.dev.blog.spring_001_blog.blog.entities.BlogUserLikeEntity bult ON b.id = bult.id.blogId "
			+ "LEFT JOIN com.mx.dev.blog.spring_001_blog.blog.entities.BlogUserReadEntity burt ON b.id = burt.id.blogId "
			+ "WHERE b.id = :blogId " + "GROUP BY b.id")
	BlogEngagementDTO getBlogEngagementData(@Param("blogId") Long blogId);

	// get engagement from multiples blogs by ids
	@Query("SELECT new com.mx.dev.blog.spring_001_blog.blog.utils.dto.BlogEngagementDTO(" + "b.blogId, "
			+ "COUNT(DISTINCT bult.id.userId), " + "COUNT(DISTINCT c.commentId), " + "COUNT(DISTINCT burt.id.userId)) "
			+ "FROM com.mx.dev.blog.spring_001_blog.blog.entities.BlogEntity b "
			+ "LEFT JOIN com.mx.dev.blog.spring_001_blog.comment.entities.CommentEntity c ON b.blogId = c.blogId "
			+ "LEFT JOIN com.mx.dev.blog.spring_001_blog.blog.entities.BlogUserLikeEntity bult ON b.blogId = bult.id.blogId "
			+ "LEFT JOIN com.mx.dev.blog.spring_001_blog.blog.entities.BlogUserReadEntity burt ON b.blogId = burt.id.blogId "
			+ "WHERE b.blogId IN :blogIds " + "GROUP BY b.blogId")
	List<BlogEngagementDTO> getBlogEngagementDataForBlogs(@Param("blogIds") List<Long> blogIds);

	// blog like
	@Query(value = "INSERT INTO blog_user_like_tbl (user_id, blog_id, created_at) VALUES (:userId, :blogId, :createdAt)", nativeQuery = true)
	@Modifying
	void insertBlogLike(@Param("userId") Long userId, @Param("blogId") Long blogId,
			@Param("createdAt") LocalDateTime createdAt);

	// blog read later
	@Query(value = "INSERT INTO blog_user_reada_tbl (user_id, blog_id, created_at) VALUES (:userId, :blogId, :createdAt)", nativeQuery = true)
	@Modifying
	void insertBlogRead(@Param("userId") Long userId, @Param("blogId") Long blogId,
			@Param("createdAt") LocalDateTime createdAt);

}
