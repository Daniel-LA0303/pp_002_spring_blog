package com.mx.dev.blog.spring_001_blog.comment.services;

import java.util.List;

import org.springframework.data.domain.Page;

import com.mx.dev.blog.spring_001_blog.comment.entities.CommentEntity;
import com.mx.dev.blog.spring_001_blog.utils.dtos.comment.CommentCardDTO;
import com.mx.dev.blog.spring_001_blog.utils.dtos.comment.CommentCreateRequestDTO;
import com.mx.dev.blog.spring_001_blog.utils.exceptions.ServiceException;

public interface CommentService {

	CommentCardDTO createComment(CommentCreateRequestDTO commentCreateRequestDTO) throws ServiceException;

	void deleteComment(Long commentId, Long userId, Long blogId) throws ServiceException;

	Page<CommentCardDTO> getAllCommentsByBlog(Long blogId, int page, int size) throws ServiceException;

	List<CommentEntity> getAllCommentsByUser(Long userId) throws ServiceException;

	CommentEntity getCommentByIdOrThrow(Long commentId) throws ServiceException;

	// CommentEntity getOneComment(Long commentId) throws ServiceException;

	CommentCardDTO updateComment(CommentCreateRequestDTO commentCreateRequestDTO, Long commentId)
			throws ServiceException;

}
