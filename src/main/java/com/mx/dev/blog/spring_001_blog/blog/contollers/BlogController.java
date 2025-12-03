package com.mx.dev.blog.spring_001_blog.blog.contollers;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
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

import com.mx.dev.blog.spring_001_blog.blog.entities.BlogEntity;
import com.mx.dev.blog.spring_001_blog.blog.services.blog.BlogService;
import com.mx.dev.blog.spring_001_blog.blog.services.dashboard.orchestator.DashboardOrchestratorService;
import com.mx.dev.blog.spring_001_blog.blog.utils.dto.BlogCreateRequestDTO;
import com.mx.dev.blog.spring_001_blog.blog.utils.dto.BlogInfoCardDTO;
import com.mx.dev.blog.spring_001_blog.blog.utils.dto.BlogPageResponseDTO;
import com.mx.dev.blog.spring_001_blog.utils.dtos.info.HomePageResponseDTO;
import com.mx.dev.blog.spring_001_blog.utils.enums.MethodEnum;
import com.mx.dev.blog.spring_001_blog.utils.enums.ResponseStatus;
import com.mx.dev.blog.spring_001_blog.utils.exceptions.ServiceException;
import com.mx.dev.blog.spring_001_blog.utils.response.ApiResponse;
import com.mx.dev.blog.spring_001_blog.utils.validators.BlogValidator;

@RestController
@RequestMapping("/api/blog")
public class BlogController {

	private final BlogService blogService;

	private final DashboardOrchestratorService dashboardOrchestratorService;

	/**
	 * validator blog
	 */
	private final BlogValidator blogValidator = new BlogValidator();

	public BlogController(BlogService blogService, DashboardOrchestratorService dashboardOrchestratorService) {
		this.blogService = blogService;
		this.dashboardOrchestratorService = dashboardOrchestratorService;
	}

	@PostMapping("/{blogId}/like")
	public ResponseEntity<?> blogLiked(@PathVariable Long blogId, @RequestParam Long userId) throws ServiceException {

		blogService.blogLiked(userId, blogId);

		ApiResponse<String> apiResponse = new ApiResponse<>(ResponseStatus.CREATED.getHttpStatusCode(),
				"/api/blog/" + blogId + "/like?userId=" + userId, MethodEnum.POST, "Blog liked successfully", "Success",
				false);

		return new ResponseEntity<>(apiResponse, HttpStatus.CREATED);
	}

	@PostMapping("/{blogId}/read")
	public ResponseEntity<?> blogRead(@PathVariable Long blogId, @RequestParam Long userId) throws ServiceException {
		blogService.blogRead(userId, blogId);

		ApiResponse<String> apiResponse = new ApiResponse<>(ResponseStatus.CREATED.getHttpStatusCode(),
				"/api/blog/" + blogId + "/read?userId=" + userId, MethodEnum.POST, "Blog marked as read successfully",
				"Success", false);

		return new ResponseEntity<>(apiResponse, HttpStatus.CREATED);
	}

	@DeleteMapping("/{blogId}/unlike")
	public ResponseEntity<?> blogUnliked(@PathVariable Long blogId, @RequestParam Long userId) throws ServiceException {

		blogService.blogUnliked(userId, blogId);

		ApiResponse<String> apiResponse = new ApiResponse<>(ResponseStatus.SUCCESS.getHttpStatusCode(),
				"/api/blog/" + blogId + "/unlike?userId=" + userId, MethodEnum.DELETE, "Blog unliked successfully",
				"Success", false);

		return new ResponseEntity<>(apiResponse, HttpStatus.OK);
	}

	@DeleteMapping("/{blogId}/read")
	public ResponseEntity<?> blogUnread(@PathVariable Long blogId, @RequestParam Long userId) throws ServiceException {
		blogService.blogUnread(userId, blogId);

		ApiResponse<String> apiResponse = new ApiResponse<>(ResponseStatus.SUCCESS.getHttpStatusCode(),
				"/api/blog/" + blogId + "/read?userId=" + userId, MethodEnum.DELETE,
				"Blog unmarked as read successfully", "Success", false);

		return new ResponseEntity<>(apiResponse, HttpStatus.OK);
	}

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

	@GetMapping("/{categoryName}/blogs")
	public ResponseEntity<ApiResponse<Page<BlogInfoCardDTO>>> getBlogsByCategoryName(@PathVariable String categoryName,
			@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size) {
		Page<BlogInfoCardDTO> blogs = blogService.getBlogsByCategoryNamePaginated(categoryName, page, size);

		Page<BlogInfoCardDTO> blogsPage = blogService.getBlogsByCategoryNamePaginated(categoryName, page, size);

		ApiResponse<Page<BlogInfoCardDTO>> apiResponse = new ApiResponse<>(ResponseStatus.SUCCESS.getHttpStatusCode(),
				"/api/blog/" + categoryName + "/blogs", MethodEnum.GET, "Blogs obtenidos correctamente", blogsPage,
				false);

		return new ResponseEntity<>(apiResponse, HttpStatus.OK);
	}

	@GetMapping("/pagination")
	public ResponseEntity<?> getBlogsPaginated(@RequestParam(defaultValue = "0") int page,
			@RequestParam(defaultValue = "10") int size) {

		Page<BlogInfoCardDTO> blogsPage = blogService.getBlogsPaginated(page, size);

		ApiResponse<Page<BlogInfoCardDTO>> apiResponse = new ApiResponse<>(ResponseStatus.SUCCESS.getHttpStatusCode(),
				"/api/blog", MethodEnum.GET, "Success method GET", blogsPage, false);

		return new ResponseEntity<>(apiResponse, HttpStatus.OK);
	}

	@GetMapping("/pagination-by-user")
	public ResponseEntity<?> getBlogsPaginatedByUser(@RequestParam Long userId,
			@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "5") int size) {

		Page<BlogInfoCardDTO> blogsPage = blogService.getBlogsByUserIdPaginated(userId, page, size);

		// Crear respuesta con los blogs y metadatos de éxito
		ApiResponse<Page<BlogInfoCardDTO>> apiResponse = new ApiResponse<>(ResponseStatus.SUCCESS.getHttpStatusCode(),
				"/api/blog", MethodEnum.GET, "Success method GET", blogsPage, false);

		return new ResponseEntity<>(apiResponse, HttpStatus.OK);
	}

	/**
	 * example to use this endpoint -> GET
	 * /api/blog/dashboard?userId=1&type=FOLLOWERS&page=0&size=10
	 */
	@GetMapping("/dashboard")
	public ResponseEntity<?> getDashboard(@RequestParam Long userId, @RequestParam String type, @RequestParam int page,
			@RequestParam int size) throws ServiceException {
		Pageable pageable = PageRequest.of(page, size);

		Page<?> res = dashboardOrchestratorService.executeDashboardQuery(userId, type, pageable);

		ApiResponse<Page<?>> apiResponse = new ApiResponse<>(ResponseStatus.SUCCESS.getHttpStatusCode(), "/api/blog",
				MethodEnum.GET, "Success method GET", res, false);

		return new ResponseEntity<>(apiResponse, HttpStatus.OK);
	}

	@GetMapping("/home-page-info")
	public ResponseEntity<?> getHomePageInfo() {

		HomePageResponseDTO blogs = blogService.getHomePageInfo();

		ApiResponse<HomePageResponseDTO> apiResponse = new ApiResponse<>(ResponseStatus.SUCCESS.getHttpStatusCode(),
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

		BlogPageResponseDTO blogResponsePageDTO = blogService.getOneBlog(blogId);

		ApiResponse<BlogPageResponseDTO> apiResponse = new ApiResponse<>(ResponseStatus.SUCCESS.getHttpStatusCode(),
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
	@PostMapping(
	// consumes = MediaType.MULTIPART_FORM_DATA_VALUE
	)
	public ResponseEntity<?> saveBlog(@RequestBody BlogCreateRequestDTO blogCreateRequestDTO
	// @RequestPart(value = "blogImage", required = false) MultipartFile blogImage
	) throws ServiceException {

		// Asignar la imagen al DTO
		// blogCreateRequestDTO.setBlogImage(blogImage);

		// Validar el DTO
		blogValidator.validate(blogCreateRequestDTO);

		// Crear el blog
		BlogEntity blogEntity = blogService.createBlog(blogCreateRequestDTO);

		// Crear la respuesta
		ApiResponse<BlogEntity> apiResponse = new ApiResponse<>(ResponseStatus.CREATED.getHttpStatusCode(), "/api/blog",
				MethodEnum.POST, "Success method POST", blogEntity, false);

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

		BlogEntity blogEntity = blogService.updateBlog(blogCreateRequestDTO, blogId);

		ApiResponse<BlogEntity> apiResponse = new ApiResponse<>(ResponseStatus.UPDATED.getHttpStatusCode(), "/api/blog",
				MethodEnum.PUT, "Success method PUT", blogEntity, false);

		return new ResponseEntity<>(apiResponse, HttpStatus.OK);
	}
}
