package com.mx.dev.blog.spring_001_blog.repositories;

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

	@Query("SELECT new com.mx.dev.blog.spring_001_blog.utils.dtos.blog.BlogEngagementDTO("
			+ "COUNT(DISTINCT c.commentId), " + "COUNT(DISTINCT bult.id.userId), " + "COUNT(DISTINCT burt.id.userId)) "
			+ "FROM com.mx.dev.blog.spring_001_blog.entities.comment.CommentEntity c "
			+ "LEFT JOIN com.mx.dev.blog.spring_001_blog.entities.blog.BlogUserLikeEntity bult ON c.blogId = bult.id.blogId "
			+ "LEFT JOIN com.mx.dev.blog.spring_001_blog.entities.blog.BlogUserReadEntity burt ON c.blogId = burt.id.blogId "
			+ "WHERE c.blogId = :blogId")
	BlogEngagementDTO getBlogEngagementData(@Param("blogId") Long blogId);

}
