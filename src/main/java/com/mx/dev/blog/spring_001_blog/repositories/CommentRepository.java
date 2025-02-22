package com.mx.dev.blog.spring_001_blog.repositories;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.mx.dev.blog.spring_001_blog.entities.comment.CommentEntity;
import com.mx.dev.blog.spring_001_blog.utils.dtos.comment.CommentCardDTO;

public interface CommentRepository extends JpaRepository<CommentEntity, Long> {

	/**
	 * get all comment by one blog
	 * 
	 * @param blogId
	 * @return
	 */
	@Query("SELECT new com.mx.dev.blog.spring_001_blog.utils.dtos.comment.CommentCardDTO( "
			+ " c.commentId, c.userId, c.blogId, c.content, ui.profilePicture, u.username, c.updatedAt) "
			+ "FROM CommentEntity c " + "JOIN UserEntity u ON c.userId = u.userId "
			+ "LEFT JOIN UserInfoEntity ui ON u.userId = ui.userId " + "WHERE c.blogId = :blogId "
			+ "ORDER BY c.updatedAt DESC")
	Page<CommentCardDTO> findAllCommentsByBlogId(@Param("blogId") Long blogId, Pageable pageable);

	/**
	 * get all comment by one user
	 * 
	 * @param userId
	 * @return
	 */
	@Query("select ce from CommentEntity ce where ce.userId = :userId")
	List<CommentEntity> findAllCommentsByUseId(Long userId);
}
