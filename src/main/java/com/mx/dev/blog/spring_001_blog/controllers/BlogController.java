package com.mx.dev.blog.spring_001_blog.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mx.dev.blog.spring_001_blog.services.BlogService;
import com.mx.dev.blog.spring_001_blog.utils.dtos.blog.BlogCreateRequestDTO;
import com.mx.dev.blog.spring_001_blog.utils.dtos.blog.BlogResponseDTO;
import com.mx.dev.blog.spring_001_blog.utils.dtos.blog.BlogResponsePageDTO;
import com.mx.dev.blog.spring_001_blog.utils.enums.MethodEnum;
import com.mx.dev.blog.spring_001_blog.utils.enums.ResponseStatus;
import com.mx.dev.blog.spring_001_blog.utils.exceptions.ServiceException;
import com.mx.dev.blog.spring_001_blog.utils.response.ApiResponse;
import com.mx.dev.blog.spring_001_blog.utils.validators.BlogValidator;

@RestController
@RequestMapping("/api/blog")
public class BlogController {

	@Autowired
	private BlogService blogService;

	/**
	 * validator blog
	 */
	private final BlogValidator blogValidator = new BlogValidator();

	/**
	 * delete blog
	 * 
	 * @param blogId
	 * @return
	 * @throws ServiceException
	 */
	@DeleteMapping("/{blogId}/{userId}")
	public ResponseEntity<?> deleteBlog(@PathVariable Long blogId, @PathVariable Long userId) throws ServiceException {

		blogService.deleteBlog(blogId, userId);

		ApiResponse<String> apiResponse = new ApiResponse<>(ResponseStatus.DELETED.getHttpStatusCode(), "/api/blog",
				MethodEnum.DELETE, "Success method DELETED", "Blog deleted.", false);

		return new ResponseEntity<>(apiResponse, HttpStatus.OK);
	}

	/**
	 * get all categories with a dto
	 * 
	 * @return
	 */
	@GetMapping
	public ResponseEntity<?> getAllBlogs() {

		List<BlogResponseDTO> blogs = blogService.getAllBlogs();

		ApiResponse<List<BlogResponseDTO>> apiResponse = new ApiResponse<>(ResponseStatus.SUCCESS.getHttpStatusCode(),
				"/api/blog", MethodEnum.GET, "Success method GET", blogs, false);

		return new ResponseEntity<>(apiResponse, HttpStatus.OK);
	}

	/**
	 * get one blog
	 * 
	 * @param blogId
	 * @return
	 * @throws ServiceException
	 */
	@GetMapping("/{blogId}")
	public ResponseEntity<?> getOneBlog(@PathVariable Long blogId) throws ServiceException {

		BlogResponsePageDTO blogResponsePageDTO = blogService.getOneBlog(blogId);

		ApiResponse<BlogResponsePageDTO> apiResponse = new ApiResponse<>(ResponseStatus.SUCCESS.getHttpStatusCode(),
				"/api/blog", MethodEnum.GET, "Success method GET", blogResponsePageDTO, false);

		return new ResponseEntity<>(apiResponse, HttpStatus.OK);
	}

	/**
	 * save a new blog
	 * 
	 * @param blogCreateRequestDTO
	 * @return
	 * @throws ServiceException
	 */
	@PostMapping
	public ResponseEntity<?> saveBlog(@RequestBody BlogCreateRequestDTO blogCreateRequestDTO) throws ServiceException {

		blogValidator.validate(blogCreateRequestDTO);

		BlogResponseDTO blogResponseDTO = blogService.createBlog(blogCreateRequestDTO);

		ApiResponse<BlogResponseDTO> apiResponse = new ApiResponse<>(ResponseStatus.CREATED.getHttpStatusCode(),
				"/api/blog", MethodEnum.POST, "Success method POST", blogResponseDTO, false);

		return new ResponseEntity<>(apiResponse, HttpStatus.OK);
	}

	/**
	 * update a blog
	 * 
	 * @param blogCreateRequestDTO
	 * @param blogId
	 * @return
	 * @throws ServiceException
	 */
	@PutMapping("/{blogId}")
	public ResponseEntity<?> updateBlog(@RequestBody BlogCreateRequestDTO blogCreateRequestDTO,
			@PathVariable Long blogId) throws ServiceException {

		blogValidator.validate(blogCreateRequestDTO);

		BlogResponseDTO blogResponseDTO = blogService.updateBlog(blogCreateRequestDTO, blogId);

		ApiResponse<BlogResponseDTO> apiResponse = new ApiResponse<>(ResponseStatus.UPDATED.getHttpStatusCode(),
				"/api/blog", MethodEnum.PUT, "Success method PUT", blogResponseDTO, false);

		return new ResponseEntity<>(apiResponse, HttpStatus.OK);
	}
}
