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
import com.mx.dev.blog.spring_001_blog.utils.dtos.blog.BlogEngagementDTO;

public interface BlogRepository extends JpaRepository<BlogEntity, Long> {

	@Transactional
	@Modifying
	@Query("DELETE FROM BlogUserLikeEntity bul WHERE bul.id.userId = :userId AND bul.id.blogId = :blogId")
	void deleteByUserIdAndBlogId(@Param("userId") Long userId, @Param("blogId") Long blogId);

	@Transactional
	@Modifying
	@Query("DELETE FROM BlogUserReadEntity bur WHERE bur.id.userId = :userId AND bur.id.blogId = :blogId")
	void deleteByUserIdAndBlogIdRead(@Param("userId") Long userId, @Param("blogId") Long blogId);

	/**
	 * query to get a boolean if exists one result of slug
	 * 
	 * @param slug
	 * @return
	 */
	@Query("SELECT COUNT(b) > 0 FROM BlogEntity b WHERE b.slug = :slug")
	boolean existsBySlug(String slug);

	@Query("SELECT COUNT(bul) > 0 FROM BlogUserLikeEntity bul WHERE bul.id.userId = :userId AND bul.id.blogId = :blogId")
	boolean existsByUserIdAndBlogId(@Param("userId") Long userId, @Param("blogId") Long blogId);

	@Query("SELECT COUNT(bur) > 0 FROM BlogUserReadEntity bur WHERE bur.id.userId = :userId AND bur.id.blogId = :blogId")
	boolean existsByUserIdAndBlogIdRead(@Param("userId") Long userId, @Param("blogId") Long blogId);

	@Query("SELECT b FROM BlogEntity b " + "JOIN CategoryBlogEntity bc ON b.blogId = bc.id.blogId "
			+ "JOIN CategoryEntity c ON bc.id.categoryId = c.categoryId " + "WHERE c.name = :categoryName "
			+ "ORDER BY b.createdAt DESC")
	Page<BlogEntity> findBlogsByCategoryName(@Param("categoryName") String categoryName, Pageable pageable);

	@Query("SELECT b FROM BlogEntity b WHERE b.userId = :userId ORDER BY b.createdAt DESC")
	Page<BlogEntity> findBlogsByUserId(@Param("userId") Long userId, Pageable pageable);

	@Query("""
			    SELECT b
			    FROM BlogEntity b
			    JOIN BlogUserLikeEntity bult ON b.blogId = bult.id.blogId
			    WHERE bult.id.userId = :userId
			    ORDER BY b.createdAt DESC
			""")
	Page<BlogEntity> findBlogsLikedByUser(@Param("userId") Long userId, Pageable pageable);

	@Query("SELECT b.id.userId FROM BlogUserLikeEntity b WHERE b.id.blogId = :blogId")
	List<Long> findUserIdsLikeByBlogId(@Param("blogId") Long blogId);

	@Query("SELECT b.id.blogId, b.id.userId FROM BlogUserLikeEntity b WHERE b.id.blogId IN :blogIds")
	List<Object[]> findUserIdsLikeByBlogIds(@Param("blogIds") List<Long> blogIds);

	@Query("SELECT b.id.userId FROM BlogUserReadEntity b WHERE b.id.blogId = :blogId")
	List<Long> findUserIdsReadByBlogId(@Param("blogId") Long blogId);

	/*
	 * @Query("SELECT b FROM BlogEntity b JOIN BlogUserReadEntity bur ON b.blogId = bur.blogId WHERE bur.userId = :userId ORDER BY b.createdAt DESC"
	 * ) Page<BlogEntity> findBlogsReadByUser(@Param("userId") Long userId, Pageable
	 * pageable);
	 */

	@Query("SELECT b.id.blogId, b.id.userId FROM BlogUserReadEntity b WHERE b.id.blogId IN :blogIds")
	List<Object[]> findUserIdsReadByBlogIds(@Param("blogIds") List<Long> blogIds);

	@Query("SELECT new com.mx.dev.blog.spring_001_blog.utils.dtos.blog.BlogEngagementDTO(" + "COALESCE(b.id, 0), "
			+ "COALESCE(COUNT(DISTINCT bult.id.userId), 0), " + "COALESCE(COUNT(DISTINCT c.commentId), 0), "
			+ "COALESCE(COUNT(DISTINCT burt.id.userId), 0)) "
			+ "FROM com.mx.dev.blog.spring_001_blog.blog.entities.BlogEntity b "
			+ "LEFT JOIN com.mx.dev.blog.spring_001_blog.comment.entities.CommentEntity c ON b.id = c.blogId "
			+ "LEFT JOIN com.mx.dev.blog.spring_001_blog.blog.entities.BlogUserLikeEntity bult ON b.id = bult.id.blogId "
			+ "LEFT JOIN com.mx.dev.blog.spring_001_blog.blog.entities.BlogUserReadEntity burt ON b.id = burt.id.blogId "
			+ "WHERE b.id = :blogId " + "GROUP BY b.id")
	BlogEngagementDTO getBlogEngagementData(@Param("blogId") Long blogId);

	@Query("SELECT new com.mx.dev.blog.spring_001_blog.utils.dtos.blog.BlogEngagementDTO(" + "b.blogId, "
			+ "COUNT(DISTINCT bult.id.userId), " + "COUNT(DISTINCT c.commentId), " + "COUNT(DISTINCT burt.id.userId)) "
			+ "FROM com.mx.dev.blog.spring_001_blog.blog.entities.BlogEntity b "
			+ "LEFT JOIN com.mx.dev.blog.spring_001_blog.comment.entities.CommentEntity c ON b.blogId = c.blogId "
			+ "LEFT JOIN com.mx.dev.blog.spring_001_blog.blog.entities.BlogUserLikeEntity bult ON b.blogId = bult.id.blogId "
			+ "LEFT JOIN com.mx.dev.blog.spring_001_blog.blog.entities.BlogUserReadEntity burt ON b.blogId = burt.id.blogId "
			+ "WHERE b.blogId IN :blogIds " + "GROUP BY b.blogId")
	List<BlogEngagementDTO> getBlogEngagementDataForBlogs(@Param("blogIds") List<Long> blogIds);

	@Query(value = "INSERT INTO blog_user_like_tbl (user_id, blog_id, created_at) VALUES (:userId, :blogId, :createdAt)", nativeQuery = true)
	@Modifying
	void insertBlogLike(@Param("userId") Long userId, @Param("blogId") Long blogId,
			@Param("createdAt") LocalDateTime createdAt);

	@Query(value = "INSERT INTO blog_user_reada_tbl (user_id, blog_id, created_at) VALUES (:userId, :blogId, :createdAt)", nativeQuery = true)
	@Modifying
	void insertBlogRead(@Param("userId") Long userId, @Param("blogId") Long blogId,
			@Param("createdAt") LocalDateTime createdAt);

}
