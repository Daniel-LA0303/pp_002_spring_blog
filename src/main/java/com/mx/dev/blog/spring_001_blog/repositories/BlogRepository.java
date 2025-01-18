package com.mx.dev.blog.spring_001_blog.repositories;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.mx.dev.blog.spring_001_blog.entities.blog.BlogEntity;
import com.mx.dev.blog.spring_001_blog.utils.dtos.blog.BlogEngagementDTO;

public interface BlogRepository extends JpaRepository<BlogEntity, Long> {

	/**
	 * query to get a boolean if exists one result of slug
	 * 
	 * @param slug
	 * @return
	 */
	@Query("SELECT COUNT(b) > 0 FROM BlogEntity b WHERE b.slug = :slug")
	boolean existsBySlug(String slug);

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

	/*
	 * @Query("SELECT b FROM BlogEntity b JOIN BlogUserReadEntity bur ON b.blogId = bur.blogId WHERE bur.userId = :userId ORDER BY b.createdAt DESC"
	 * ) Page<BlogEntity> findBlogsReadByUser(@Param("userId") Long userId, Pageable
	 * pageable);
	 */

	@Query("SELECT new com.mx.dev.blog.spring_001_blog.utils.dtos.blog.BlogEngagementDTO(" + "COALESCE(c.blogId, 0), "
			+ "COALESCE(COUNT(DISTINCT c.commentId), 0), " + "COALESCE(COUNT(DISTINCT bult.id.userId), 0), "
			+ "COALESCE(COUNT(DISTINCT burt.id.userId), 0)) "
			+ "FROM com.mx.dev.blog.spring_001_blog.entities.comment.CommentEntity c "
			+ "LEFT JOIN com.mx.dev.blog.spring_001_blog.entities.blog.BlogUserLikeEntity bult ON c.blogId = bult.id.blogId "
			+ "LEFT JOIN com.mx.dev.blog.spring_001_blog.entities.blog.BlogUserReadEntity burt ON c.blogId = burt.id.blogId "
			+ "WHERE c.blogId = :blogId " + "GROUP BY c.blogId")
	BlogEngagementDTO getBlogEngagementData(@Param("blogId") Long blogId);

	@Query("SELECT new com.mx.dev.blog.spring_001_blog.utils.dtos.blog.BlogEngagementDTO(" + "b.blogId, "
			+ "COUNT(DISTINCT c.commentId), " + "COUNT(DISTINCT bult.id.userId), " + "COUNT(DISTINCT burt.id.userId)) "
			+ "FROM com.mx.dev.blog.spring_001_blog.entities.blog.BlogEntity b "
			+ "LEFT JOIN com.mx.dev.blog.spring_001_blog.entities.comment.CommentEntity c ON b.blogId = c.blogId "
			+ "LEFT JOIN com.mx.dev.blog.spring_001_blog.entities.blog.BlogUserLikeEntity bult ON b.blogId = bult.id.blogId "
			+ "LEFT JOIN com.mx.dev.blog.spring_001_blog.entities.blog.BlogUserReadEntity burt ON b.blogId = burt.id.blogId "
			+ "WHERE b.blogId IN :blogIds " + "GROUP BY b.blogId")
	List<BlogEngagementDTO> getBlogEngagementDataForBlogs(@Param("blogIds") List<Long> blogIds);

}
