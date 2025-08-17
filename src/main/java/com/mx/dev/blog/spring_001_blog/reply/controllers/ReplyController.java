package com.mx.dev.blog.spring_001_blog.reply.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.mx.dev.blog.spring_001_blog.reply.services.ReplyService;
import com.mx.dev.blog.spring_001_blog.utils.dtos.reply.ReplyCardDTO;
import com.mx.dev.blog.spring_001_blog.utils.dtos.reply.ReplyCreateRequestDTO;
import com.mx.dev.blog.spring_001_blog.utils.enums.MethodEnum;
import com.mx.dev.blog.spring_001_blog.utils.enums.ResponseStatus;
import com.mx.dev.blog.spring_001_blog.utils.exceptions.ServiceException;
import com.mx.dev.blog.spring_001_blog.utils.response.ApiResponse;
import com.mx.dev.blog.spring_001_blog.utils.validators.ReplyValidator;

@RestController
@RequestMapping("/api/reply")
public class ReplyController {

	@Autowired
	private ReplyService replyService;

	private ReplyValidator replyValidator = new ReplyValidator();

	@GetMapping("/get-replies-by-comment/{commentId}")
	public ResponseEntity<?> getCommentsByBlog(@PathVariable Long commentId, @RequestParam(defaultValue = "0") int page,
			@RequestParam(defaultValue = "5") int size) throws ServiceException {

		Page<ReplyCardDTO> replies = replyService.getRepliesByComment(commentId, page, size);

		ApiResponse<Page<ReplyCardDTO>> apiResponse = new ApiResponse<>(ResponseStatus.SUCCESS.getHttpStatusCode(),
				"/api/comment", MethodEnum.GET, "Success method GET", replies, false);

		return new ResponseEntity<>(apiResponse, HttpStatus.OK);
	}

	@PostMapping
	public ResponseEntity<?> saveReply(@RequestBody ReplyCreateRequestDTO replyCreateRequestDTO)
			throws ServiceException {

		replyValidator.validate(replyCreateRequestDTO);

		ReplyCardDTO replyEntity = replyService.createReply(replyCreateRequestDTO);

		ApiResponse<ReplyCardDTO> apiResponse = new ApiResponse<>(ResponseStatus.CREATED.getHttpStatusCode(),
				"/api/reply", MethodEnum.POST, "Success method POST", replyEntity, false);

		return new ResponseEntity<>(apiResponse, HttpStatus.OK);
	}

	@PutMapping("/{replyId}")
	public ResponseEntity<?> updateComment(@RequestBody ReplyCreateRequestDTO replyCreateRequestDTO,
			@PathVariable Long replyId) throws ServiceException {

		replyValidator.validate(replyCreateRequestDTO);

		ReplyCardDTO replyUpdated = replyService.updateReply(replyCreateRequestDTO, replyId);

		ApiResponse<ReplyCardDTO> apiResponse = new ApiResponse<>(ResponseStatus.SUCCESS.getHttpStatusCode(),
				"/api/reply", MethodEnum.PUT, "Success method POST", replyUpdated, false);

		return new ResponseEntity<>(apiResponse, HttpStatus.OK);
	}

}
