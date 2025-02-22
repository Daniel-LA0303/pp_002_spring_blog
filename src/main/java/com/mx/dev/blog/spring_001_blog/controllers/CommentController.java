package com.mx.dev.blog.spring_001_blog.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.mx.dev.blog.spring_001_blog.entities.comment.CommentEntity;
import com.mx.dev.blog.spring_001_blog.services.CommentService;
import com.mx.dev.blog.spring_001_blog.utils.dtos.comment.CommentCardDTO;
import com.mx.dev.blog.spring_001_blog.utils.dtos.comment.CommentCreateRequestDTO;
import com.mx.dev.blog.spring_001_blog.utils.enums.MethodEnum;
import com.mx.dev.blog.spring_001_blog.utils.enums.ResponseStatus;
import com.mx.dev.blog.spring_001_blog.utils.exceptions.ServiceException;
import com.mx.dev.blog.spring_001_blog.utils.response.ApiResponse;
import com.mx.dev.blog.spring_001_blog.utils.validators.CommentValidator;

@RestController
@RequestMapping("/api/comment")
public class CommentController {

	/**
	 * service
	 */
	@Autowired
	private CommentService commentService;

	/**
	 * validator
	 */
	CommentValidator commentValidator = new CommentValidator();

	@DeleteMapping("/{commentId}")
	public ResponseEntity<?> deleteComment(@PathVariable Long commentId, @RequestParam Long userId,
			@RequestParam Long blogId) throws ServiceException {

		// Primero, validamos la solicitud
		// commentValidator.validateDelete(commentId, userId, blogId); // Asegúrate de
		// implementar este método en el validador

		// Llamamos al servicio para eliminar el comentario
		commentService.deleteComment(commentId, userId, blogId);

		// Preparamos la respuesta
		ApiResponse<String> apiResponse = new ApiResponse<>(ResponseStatus.DELETED.getHttpStatusCode(), "/api/comment",
				MethodEnum.DELETE, "Comment deleted successfully", null, false);

		return new ResponseEntity<>(apiResponse, HttpStatus.OK);
	}

	/**
	 * get comment by blog
	 * 
	 * @param blogId
	 * @return
	 * @throws ServiceException
	 */
	@GetMapping("/get-comments-by-blog/{blogId}")
	public ResponseEntity<?> getCommentsByBlog(@PathVariable Long blogId, @RequestParam(defaultValue = "0") int page,
			@RequestParam(defaultValue = "5") int size) throws ServiceException {

		Page<CommentCardDTO> comments = commentService.getAllCommentsByBlog(blogId, page, size);

		ApiResponse<Page<CommentCardDTO>> apiResponse = new ApiResponse<>(ResponseStatus.SUCCESS.getHttpStatusCode(),
				"/api/comment", MethodEnum.GET, "Success method GET", comments, false);

		return new ResponseEntity<>(apiResponse, HttpStatus.OK);
	}

	/**
	 * get comments by user
	 * 
	 * @param userId
	 * @return
	 * @throws ServiceException
	 */
	@GetMapping("/get-comments-by-user/{userId}")
	public ResponseEntity<?> getCommentsByUser(@PathVariable Long userId) throws ServiceException {

		List<CommentEntity> comments = commentService.getAllCommentsByUser(userId);

		ApiResponse<List<CommentEntity>> apiResponse = new ApiResponse<>(ResponseStatus.SUCCESS.getHttpStatusCode(),
				"/api/comment", MethodEnum.GET, "Success method GET", comments, false);

		return new ResponseEntity<>(apiResponse, HttpStatus.OK);
	}

	/**
	 * save a comment
	 * 
	 * @param commentCreateRequestDTO
	 * @return
	 * @throws ServiceException
	 */
	@PostMapping
	public ResponseEntity<?> saveComment(@RequestBody CommentCreateRequestDTO commentCreateRequestDTO)
			throws ServiceException {

		commentValidator.validate(commentCreateRequestDTO);

		CommentCardDTO commentEntity = commentService.createComment(commentCreateRequestDTO);

		ApiResponse<CommentCardDTO> apiResponse = new ApiResponse<>(ResponseStatus.CREATED.getHttpStatusCode(),
				"/api/comment", MethodEnum.POST, "Success method POST", commentEntity, false);

		return new ResponseEntity<>(apiResponse, HttpStatus.OK);
	}

	/**
	 * update a comment
	 * 
	 * @param commentCreateRequestDTO
	 * @param commentId
	 * @return
	 * @throws ServiceException
	 */
	@PutMapping("/{commentId}")
	public ResponseEntity<?> updateComment(@RequestBody CommentCreateRequestDTO commentCreateRequestDTO,
			@PathVariable Long commentId) throws ServiceException {

		commentValidator.validate(commentCreateRequestDTO);

		CommentEntity commentEntity = commentService.updateComment(commentCreateRequestDTO, commentId);

		ApiResponse<CommentEntity> apiResponse = new ApiResponse<>(ResponseStatus.UPDATED.getHttpStatusCode(),
				"/api/comment", MethodEnum.PUT, "Success method PUT", commentEntity, false);

		return new ResponseEntity<>(apiResponse, HttpStatus.OK);
	}

}
