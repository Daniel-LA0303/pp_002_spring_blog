package com.mx.dev.blog.spring_001_blog.comment.services.impl;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mx.dev.blog.spring_001_blog.blog.entities.BlogEntity;
import com.mx.dev.blog.spring_001_blog.blog.services.blog.BlogService;
import com.mx.dev.blog.spring_001_blog.comment.entities.CommentEntity;
import com.mx.dev.blog.spring_001_blog.comment.repositories.CommentRepository;
import com.mx.dev.blog.spring_001_blog.comment.services.CommentService;
import com.mx.dev.blog.spring_001_blog.user.entities.UserEntity;
import com.mx.dev.blog.spring_001_blog.user.entities.UserInfoEntity;
import com.mx.dev.blog.spring_001_blog.user.repositories.UserInfoRepository;
import com.mx.dev.blog.spring_001_blog.user.services.UserService;
import com.mx.dev.blog.spring_001_blog.utils.dtos.comment.CommentCardDTO;
import com.mx.dev.blog.spring_001_blog.utils.dtos.comment.CommentCreateRequestDTO;
import com.mx.dev.blog.spring_001_blog.utils.enums.MethodEnum;
import com.mx.dev.blog.spring_001_blog.utils.enums.ResponseStatus;
import com.mx.dev.blog.spring_001_blog.utils.exceptions.ServiceException;

@Service
public class CommentServiceImpl implements CommentService {

	@Autowired
	private CommentRepository commentRepository;

	// @Autowired
	// private UserRepository userRepository;

	@Autowired
	private UserInfoRepository userInfoRepository;

	@Autowired
	private BlogService blogService;

	@Autowired
	private UserService userService;

	@Override
	@Transactional
	public CommentCardDTO createComment(CommentCreateRequestDTO commentCreateRequestDTO) throws ServiceException {

		// 1. first we check if blog exists
		BlogEntity blogEntity = blogService.getBlogByIdOrThrow(commentCreateRequestDTO.getBlogId());

		// 2.
		UserEntity userEntity = userService.getOneUserOrThrow(commentCreateRequestDTO.getUserId());

		// 3.
		CommentEntity commentEntity = new CommentEntity();
		commentEntity.setBlogId(blogEntity.getBlogId());
		commentEntity.setContent(commentCreateRequestDTO.getContent());
		commentEntity.setCreatedAt(LocalDateTime.now());
		commentEntity.setUpdatedAt(LocalDateTime.now());
		commentEntity.setUserId(userEntity.getUserId());

		commentEntity = commentRepository.save(commentEntity);

		// 4.
		UserInfoEntity userInfoEntity = userInfoRepository.findUserInfoByUserId(userEntity.getUserId()).orElse(null);

		// 5.
		return new CommentCardDTO(commentEntity.getCommentId(), userEntity.getUserId(), blogEntity.getBlogId(),
				commentEntity.getContent(), userInfoEntity != null ? userInfoEntity.getProfilePicture() : null,
				userEntity.getUsername(), commentEntity.getUpdatedAt());
	}

	@Override
	public void deleteComment(Long commentId, Long userId, Long blogId) throws ServiceException {

		// 1. Primero verificamos si el comentario existe
		CommentEntity commentEntity = getCommentByIdOrThrow(commentId);

		// 2. Verificamos si el blog existe (si no existe, no se puede eliminar un
		// comentario asociado a un blog inexistente)
		blogService.getBlogByIdOrThrow(blogId);

		// 3. Verificamos si el usuario existe
		UserEntity userEntity = userService.getOneUserOrThrow(userId);

		// 4. Comprobamos si el usuario tiene permisos para eliminar el comentario
		if (!commentEntity.getUserId().equals(userEntity.getUserId())) {
			throw new ServiceException("You do not have permissions to delete this comment",
					ResponseStatus.BAD_REQUEST.getHttpStatusCode(), "/api/comment", MethodEnum.DELETE);
		}

		// 5. Si todo es correcto, eliminamos el comentario
		commentRepository.deleteById(commentId);
	}

	@Override
	public Page<CommentCardDTO> getAllCommentsByBlog(Long blogId, int page, int size) throws ServiceException {
		// 1. Verificar si el blog existe
		blogService.getBlogByIdOrThrow(blogId);

		// 2. Crear un objeto Pageable para la paginación
		Pageable pageable = PageRequest.of(page, size);

		// 3. Obtener los comentarios paginados
		return commentRepository.findAllCommentsByBlogId(blogId, pageable);
	}

	@Override
	public List<CommentEntity> getAllCommentsByUser(Long userId) throws ServiceException {

		// 1. first check if user exists
		userService.getOneUserOrThrow(userId);

		return commentRepository.findAllCommentsByUseId(userId);
	}

	@Override
	public CommentEntity getCommentByIdOrThrow(Long commentId) throws ServiceException {

		return commentRepository.findById(commentId).orElseThrow(() -> new ServiceException("Comment not found",
				ResponseStatus.NOT_FOUND.getHttpStatusCode(), "/api/comment", MethodEnum.GET));
	}

	/*
	 * @Override public CommentEntity getOneComment(Long commentId) throws
	 * ServiceException {
	 * 
	 * return getCommentByIdOrThrow(commentId); }
	 */

	@Override
	public CommentCardDTO updateComment(CommentCreateRequestDTO commentCreateRequestDTO, Long commentId)
			throws ServiceException {

		// 1. first check if comment exists
		CommentEntity commentEntity = getCommentByIdOrThrow(commentId);

		// 2. first we check if blog exists
		BlogEntity blogEntity = blogService.getBlogByIdOrThrow(commentCreateRequestDTO.getBlogId());

		// 3. we check if user exists
		UserEntity userEntity = userService.getOneUserOrThrow(commentCreateRequestDTO.getUserId());

		// 4. we need check if user have permissions
		if (!commentEntity.getUserId().equals(userEntity.getUserId())) {
			throw new ServiceException("You do not have permissions to update this comment",
					ResponseStatus.BAD_REQUEST.getHttpStatusCode(), "/api/comment", MethodEnum.PUT);
		}

		// 5. update fields
		commentEntity.setContent(commentCreateRequestDTO.getContent());
		commentEntity.setUpdatedAt(LocalDateTime.now());

		commentEntity = commentRepository.save(commentEntity);

		// 6. get extra info for DTO
		UserInfoEntity userInfoEntity = userInfoRepository.findUserInfoByUserId(userEntity.getUserId()).orElse(null);

		// 7. return DTO instead of entity
		return new CommentCardDTO(commentEntity.getCommentId(), userEntity.getUserId(), blogEntity.getBlogId(),
				commentEntity.getContent(), userInfoEntity != null ? userInfoEntity.getProfilePicture() : null,
				userEntity.getUsername(), commentEntity.getUpdatedAt());
	}

}
