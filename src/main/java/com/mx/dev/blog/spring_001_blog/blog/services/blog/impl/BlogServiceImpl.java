package com.mx.dev.blog.spring_001_blog.blog.services.blog.impl;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mx.dev.blog.spring_001_blog.blog.entities.BlogEntity;
import com.mx.dev.blog.spring_001_blog.blog.repositories.BlogRepository;
import com.mx.dev.blog.spring_001_blog.blog.services.blog.BlogService;
import com.mx.dev.blog.spring_001_blog.blog.utils.dto.BlogCreateRequestDTO;
import com.mx.dev.blog.spring_001_blog.blog.utils.dto.BlogEngagementDTO;
import com.mx.dev.blog.spring_001_blog.blog.utils.dto.BlogInfoCardDTO;
import com.mx.dev.blog.spring_001_blog.blog.utils.dto.BlogPageResponseDTO;
import com.mx.dev.blog.spring_001_blog.blog.utils.mappers.BlogMappers;
import com.mx.dev.blog.spring_001_blog.category.entities.CategoryEntity;
import com.mx.dev.blog.spring_001_blog.category.repositories.CategoryRepository;
import com.mx.dev.blog.spring_001_blog.category.services.CategoryService;
import com.mx.dev.blog.spring_001_blog.user.entities.UserEntity;
import com.mx.dev.blog.spring_001_blog.user.repositories.UserRepository;
import com.mx.dev.blog.spring_001_blog.user.services.UserService;
import com.mx.dev.blog.spring_001_blog.utils.dtos.category.CategorySmallInfoDTO;
import com.mx.dev.blog.spring_001_blog.utils.dtos.category.CategoryTopInfoDTO;
import com.mx.dev.blog.spring_001_blog.utils.dtos.info.HomePageResponseDTO;
import com.mx.dev.blog.spring_001_blog.utils.dtos.user.UserInfoCardDTO;
import com.mx.dev.blog.spring_001_blog.utils.dtos.user.UserSimpleResponseDTO;
import com.mx.dev.blog.spring_001_blog.utils.dtos.user.UserTopDTO;
import com.mx.dev.blog.spring_001_blog.utils.enums.MethodEnum;
import com.mx.dev.blog.spring_001_blog.utils.enums.ResponseStatus;
import com.mx.dev.blog.spring_001_blog.utils.exceptions.ServiceException;
import com.mx.dev.blog.spring_001_blog.utils.mappers.CategoryMappers;

@Service
public class BlogServiceImpl implements BlogService {

	@Autowired
	private BlogRepository blogRepository;

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private CategoryRepository categoryRepository;

	@Autowired
	private UserService userService;

	@Autowired
	private CategoryService categoryService;

	/**
	 * static methods of services
	 */
	public static String generateSlug(String title) {

		String slug = title.toLowerCase().replaceAll("[^a-z0-9\\s]", "").replaceAll("\\s+", "-");

		String randomPart = generateRandomString(8);

		return slug + "-" + randomPart;
	}

	private static String generateRandomString(int length) {
		String characters = "abcdefghijklmnopqrstuvwxyz0123456789";
		Random random = new Random();
		StringBuilder sb = new StringBuilder(length);
		for (int i = 0; i < length; i++) {
			sb.append(characters.charAt(random.nextInt(characters.length())));
		}
		return sb.toString();
	}

	private static String generateRandomSuffix() {
		return Long.toHexString(System.nanoTime()); // Generates a random suffix based on nanoTime
	}

	// liked in a blog
	@Override
	@Transactional
	public void blogLiked(Long userId, Long blogId) throws ServiceException {

		if (blogRepository.existsByUserIdAndBlogId(userId, blogId)) {
			throw new ServiceException("Blog error, please come back in a few minutes.",
					ResponseStatus.BAD_REQUEST.getHttpStatusCode(), "/api/blog", MethodEnum.GET);
		}

		blogRepository.insertBlogLike(userId, blogId, LocalDateTime.now());

	}

	// read later in a blog
	@Override
	@Transactional
	public void blogRead(Long userId, Long blogId) throws ServiceException {

		if (blogRepository.existsByUserIdAndBlogIdRead(userId, blogId)) {
			throw new ServiceException("Blog error, please come back in a few minutes.",
					ResponseStatus.BAD_REQUEST.getHttpStatusCode(), "/api/blog/read", MethodEnum.POST);
		}

		blogRepository.insertBlogRead(userId, blogId, LocalDateTime.now());

	}

	// user unlike blog
	@Override
	public void blogUnliked(Long userId, Long blogId) throws ServiceException {

		if (!blogRepository.existsByUserIdAndBlogId(userId, blogId)) {
			throw new ServiceException("Blog error in generate slug, please come back in a few minutes.",
					ResponseStatus.BAD_REQUEST.getHttpStatusCode(), "/api/blog", MethodEnum.GET);
		}

		blogRepository.deleteByUserIdAndBlogId(userId, blogId);
	}

	// user unread later blog
	@Override
	public void blogUnread(Long userId, Long blogId) throws ServiceException {

		if (!blogRepository.existsByUserIdAndBlogIdRead(userId, blogId)) {
			throw new ServiceException("El blog no ha sido leído por el usuario.",
					ResponseStatus.BAD_REQUEST.getHttpStatusCode(), "/api/blog/read", MethodEnum.DELETE);
		}

		blogRepository.deleteByUserIdAndBlogIdRead(userId, blogId);
	}

	/**
	 * create new blog service
	 */
	@Transactional
	@Override
	public BlogEntity createBlog(BlogCreateRequestDTO blogCreateRequestDTO) throws ServiceException {
		// 1. first check if user exists
		UserSimpleResponseDTO user = userService.getOneUserSimpleInfo(blogCreateRequestDTO.getUserId());

		// 2. check if categories exists and get them
		List<CategoryEntity> categoryEntities = categoryService.getListCategories(blogCreateRequestDTO.getCategories());

		// 3. we create a unique slug
		String slug = generateSlug(blogCreateRequestDTO.getTitle());

		// 4. Check if the generated slug already exists in the database
		int attempts = 0;
		int maxAttempts = 5; // Max attempts to avoid infinite loop
		while (blogRepository.existsBySlug(slug) && attempts < maxAttempts) {
			slug = generateSlug(blogCreateRequestDTO.getTitle()) + "-" + generateRandomSuffix();
			attempts++;
		}
		if (attempts >= maxAttempts) {
			throw new ServiceException("Blog error in generate slug, please come back in a few minutes.",
					ResponseStatus.BAD_REQUEST.getHttpStatusCode(), "/api/blog", MethodEnum.GET);
		}

		// 5. Now proceed with creating the Blog entity
		BlogEntity blogEntity = BlogMappers.toCreateABlog(blogCreateRequestDTO, slug, user.getUserId());

		// Save and flush to make sure blog_id is generated before creating the relation
		blogRepository.saveAndFlush(blogEntity);

		// 6. Verificar que el blog_id está generado y asignado
		Long blogId = blogEntity.getBlogId();
		System.out.println("Blog ID generado: " + blogId); // Esto te asegura que el blog_id fue generado correctamente

		// 7. Asignar categorías al blog
		blogEntity.setCategories(categoryEntities);

		// 8. Ahora guardamos el blog, incluyendo las relaciones de categorías
		blogRepository.save(blogEntity);

		return blogEntity;
	}

	// dash board services
	@Override
	public Page<BlogInfoCardDTO> dashboardGetBlogsByLikedUserPaginated(Long userId, Pageable pageable)
			throws ServiceException {

		Page<Object[]> result = blogRepository.findBlogCardsLikedByUser(userId, pageable);

		List<BlogInfoCardDTO> content = result.getContent().stream().map(BlogMappers::mapRow).toList();

		return new PageImpl<>(content, pageable, result.getTotalElements());
	}

	@Override
	public Page<BlogInfoCardDTO> dashboardGetBlogsByReadLaterUserPaginated(Long userId, Pageable pageable)
			throws ServiceException {

		Page<Object[]> result = blogRepository.findBlogCardsLikedByUser(userId, pageable);

		List<BlogInfoCardDTO> content = result.getContent().stream().map(BlogMappers::mapRow).toList();

		return new PageImpl<>(content, pageable, result.getTotalElements());
	}

	@Override
	public Page<BlogInfoCardDTO> dashboardGetBlogsByUserPaginated(Long userId, Pageable pageable)
			throws ServiceException {

		Page<Object[]> result = blogRepository.findBlogCardsLikedByUser(userId, pageable);

		List<BlogInfoCardDTO> content = result.getContent().stream().map(BlogMappers::mapRow).toList();

		return new PageImpl<>(content, pageable, result.getTotalElements());
	}

	@Override
	public Page<CategoryEntity> dashboardGetCategoriesFollowedByUserPaginated(Long userId, Pageable pageable)
			throws ServiceException {

		return blogRepository.findCategoriesByFollowedUser(userId, pageable);
	}

	@Override
	public Page<UserEntity> dashboardGetFollowedsByUserPaginated(Long userId, Pageable pageable)
			throws ServiceException {

		return blogRepository.findUserFollowedsByUserId(userId, pageable);
	}

	@Override
	public Page<UserEntity> dashboardGetFollowersByUserPaginated(Long userId, Pageable pageable)
			throws ServiceException {

		return blogRepository.findUserFollowersByUserId(userId, pageable);
	}

	/**
	 * delete a blog service
	 */
	@Transactional
	@Override
	public void deleteBlog(Long blogId, Long userId) throws ServiceException {

		// 1. check if blog exists
		BlogEntity blogEntity = getBlogByIdOrThrow(blogId);

		// 2. check if user have permissions to delete
		if (!blogEntity.getUserId().equals(userId)) {
			throw new ServiceException("You do not have permissions to delete this blog",
					ResponseStatus.BAD_REQUEST.getHttpStatusCode(), "/api/blog", MethodEnum.DELETE);
		}

		// 3. delete or clear categories
		blogEntity.getCategories().clear();

		// 4. if user is same, then we delete blog
		blogRepository.delete(blogEntity);

	}

	/**
	 * get blog by id, if blog doesn't exists we catch in a exception
	 * 
	 */
	@Override
	public BlogEntity getBlogByIdOrThrow(Long blogId) throws ServiceException {

		return blogRepository.findById(blogId).orElseThrow(() -> new ServiceException("Blog not found",
				ResponseStatus.NOT_FOUND.getHttpStatusCode(), "/api/blog", MethodEnum.GET));
	}

	// get blog by category name paginated
	@Override
	public Page<BlogInfoCardDTO> getBlogsByCategoryNamePaginated(String categoryName, int page, int size) {

		Pageable pageable = PageRequest.of(page, size);

		Page<Object[]> result = blogRepository.findBlogCardsByCategory(categoryName, pageable);

		List<BlogInfoCardDTO> content = result.getContent().stream().map(BlogMappers::mapRow).toList();

		return new PageImpl<>(content, pageable, result.getTotalElements());
	}

	// get info user to page profile
	@Override
	public Page<BlogInfoCardDTO> getBlogsByUserIdPaginated(Long userId, int page, int size) {
		Pageable pageable = PageRequest.of(page, size);

		Page<Object[]> result = blogRepository.findBlogCardsByUserId(userId, pageable);

		List<BlogInfoCardDTO> content = result.getContent().stream().map(BlogMappers::mapRow).toList();

		return new PageImpl<>(content, pageable, result.getTotalElements());
	}

	@Override
	public Page<BlogInfoCardDTO> getBlogsPaginated(int page, int size) {
		Pageable pageable = PageRequest.of(page, size);

		Page<Object[]> result = blogRepository.findAllBlogCards(pageable);

		List<BlogInfoCardDTO> content = result.getContent().stream().map(BlogMappers::mapRow).toList();

		return new PageImpl<>(content, pageable, result.getTotalElements());

	}

	@Override
	public HomePageResponseDTO getHomePageInfo() {

		Pageable pageable = PageRequest.of(0, 4);

		Page<UserTopDTO> usersTopPage = userRepository.getTopUsersByPosts(pageable);

		List<UserTopDTO> usersTop = usersTopPage.getContent();

		Page<CategoryTopInfoDTO> categoriesTopPage = categoryRepository.findTopCategories(pageable);
		List<CategoryTopInfoDTO> categoriesTop = categoriesTopPage.getContent();

		HomePageResponseDTO homePageResponseDTO = new HomePageResponseDTO();
		homePageResponseDTO.setCategoriesTop(categoriesTop);
		homePageResponseDTO.setUsersTop(usersTop);

		return homePageResponseDTO;
	}

	/**
	 * get one blog service by page view blog
	 */
	@Override
	@Transactional(readOnly = true)
	public BlogPageResponseDTO getOneBlog(Long blogId) throws ServiceException {

		// 1. first search blog by id
		BlogEntity blogEntity = getBlogByIdOrThrow(blogId);

		// 2. if blog exists then we need blog engagement info
		BlogEngagementDTO blogEngagementDTO = blogRepository.getBlogEngagementData(blogEntity.getBlogId());

		if (blogEngagementDTO == null) {
			blogEngagementDTO = new BlogEngagementDTO(blogEntity.getBlogId(), 0L, 0L, 0L);
		}

		// 3. we need some info from user
		UserInfoCardDTO userInfoCardDTO = userRepository.getUserInfoCard(blogEntity.getUserId());

		// 4. get users list
		List<Long> usersFollwers = userRepository.getFollowersIds(blogEntity.getUserId());
		userInfoCardDTO.setUsersFollowers(usersFollwers);

		// 4. prepare info
		List<CategorySmallInfoDTO> categories = CategoryMappers.toListCategorySmallInfo(blogEntity.getCategories());

		// 5. get users tahn liked
		List<Long> usersLiked = blogRepository.findUserIdsLikeByBlogId(blogId);

		// 6. get users tahn saved
		List<Long> usersReaded = blogRepository.findUserIdsReadByBlogId(blogId);

		return BlogMappers.toViewBlogResponse(blogEntity, categories, userInfoCardDTO, usersLiked, usersReaded,
				blogEngagementDTO);
	}

	@Override
	@Transactional
	public BlogEntity updateBlog(BlogCreateRequestDTO blogCreateRequestDTO, Long blogId) throws ServiceException {
		// 1. first check if user exists
		UserSimpleResponseDTO user = userService.getOneUserSimpleInfo(blogCreateRequestDTO.getUserId());

		// 2. check if blog exists
		BlogEntity blogEntity = getBlogByIdOrThrow(blogId);

		// 3. check if user haver permissions
		if (!blogEntity.getUserId().equals(user.getUserId())) {
			throw new ServiceException("You do not have permissions to update this blog",
					ResponseStatus.BAD_REQUEST.getHttpStatusCode(), "/api/blog", MethodEnum.PUT);
		}

		// 4. check if categories exists and get it
		List<CategoryEntity> categoryEntities = categoryService.getListCategories(blogCreateRequestDTO.getCategories());

		// 5. Now proceed with creating the Blog entity
		blogEntity.setCategories(categoryEntities);
		blogEntity.setContent(blogCreateRequestDTO.getContent());
		blogEntity.setDescription(blogCreateRequestDTO.getDescription());
		blogEntity.setTitle(blogCreateRequestDTO.getTitle());
		blogEntity.setUpdatedAt(LocalDateTime.now());

		// 6. Save to the database
		blogRepository.save(blogEntity);

		return blogEntity;
	}

}
