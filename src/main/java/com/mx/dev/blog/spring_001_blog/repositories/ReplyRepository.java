package com.mx.dev.blog.spring_001_blog.repositories;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.mx.dev.blog.spring_001_blog.entities.reply.ReplyEntity;
import com.mx.dev.blog.spring_001_blog.utils.dtos.reply.ReplyCardDTO;

public interface ReplyRepository extends JpaRepository<ReplyEntity, Long> {

	/**
	 * get all replies by comment
	 * 
	 * @param commentId
	 * @return
	 */
	@Query("SELECT new com.mx.dev.blog.spring_001_blog.utils.dtos.reply.ReplyCardDTO( "
			+ "r.replyId, r.content, r.blogId, r.commentId, r.userId, u.username, ui.profilePicture, r.updatedAt) "
			+ "FROM ReplyEntity r " + "JOIN UserEntity u ON r.userId = u.userId "
			+ "LEFT JOIN UserInfoEntity ui ON u.userId = ui.userId " + "WHERE r.commentId = :commentId "
			+ "ORDER BY r.updatedAt DESC")
	Page<ReplyCardDTO> findAllRepliesByCommentId(@Param("commentId") Long commentId, Pageable pageable);

	@Query("SELECT new com.mx.dev.blog.spring_001_blog.utils.dtos.reply.ReplyCardDTO( "
			+ "r.replyId, r.content, r.blogId, r.commentId, r.userId, u.username, ui.profilePicture, r.updatedAt) "
			+ "FROM ReplyEntity r " + "JOIN UserEntity u ON r.userId = u.userId "
			+ "LEFT JOIN UserInfoEntity ui ON u.userId = ui.userId " + "WHERE r.replyId = :replyId")
	Optional<ReplyCardDTO> findReplyById(@Param("replyId") Long replyId);

}
