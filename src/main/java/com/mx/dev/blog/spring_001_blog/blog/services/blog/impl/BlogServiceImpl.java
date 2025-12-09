package com.mx.dev.blog.spring_001_blog.blog.services.blog.impl;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

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
import com.mx.dev.blog.spring_001_blog.cloudstorage.cloudinary.service.CloudinaryService;
import com.mx.dev.blog.spring_001_blog.cloudstorage.cloudinary.utils.dto.ImageResponseCloudinaryDTO;
import com.mx.dev.blog.spring_001_blog.cloudstorage.storageservices.services.MediaService;
import com.mx.dev.blog.spring_001_blog.cloudstorage.storageservices.services.StorageServices;
import com.mx.dev.blog.spring_001_blog.cloudstorage.storageservices.utils.enums.TypeStorage;
import com.mx.dev.blog.spring_001_blog.cloudstorage.storageservices.utils.mappers.CloudStorageMappers;
import com.mx.dev.blog.spring_001_blog.notifiation.entities.NotificationEntity;
import com.mx.dev.blog.spring_001_blog.notifiation.services.NotificationService;
import com.mx.dev.blog.spring_001_blog.notifiation.utils.enums.NotificationTargetType;
import com.mx.dev.blog.spring_001_blog.notifiation.utils.enums.NotificationType;
import com.mx.dev.blog.spring_001_blog.user.entities.UserEntity;
import com.mx.dev.blog.spring_001_blog.user.repositories.UserRepository;
import com.mx.dev.blog.spring_001_blog.user.services.UserService;
import com.mx.dev.blog.spring_001_blog.utils.dtos.category.BlogsByCategoryInfoDTO;
import com.mx.dev.blog.spring_001_blog.utils.dtos.category.CategoryFullInfoDTO;
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

	private final BlogRepository blogRepository;

	private final UserRepository userRepository;

	private final CategoryRepository categoryRepository;

	private final UserService userService;

	private final CategoryService categoryService;

	private final MediaService mediaService;

	private final CloudinaryService cloudinaryService;

	private final StorageServices storageServices;

	private final NotificationService notificationService;

	public BlogServiceImpl(BlogRepository blogRepository, UserRepository userRepository,
			CategoryRepository categoryRepository, UserService userService, CategoryService categoryService,
			MediaService mediaService, CloudinaryService cloudinaryService, StorageServices storageServices,
			NotificationService notificationService) {
		this.blogRepository = blogRepository;
		this.userRepository = userRepository;
		this.categoryRepository = categoryRepository;
		this.userService = userService;
		this.categoryService = categoryService;
		this.mediaService = mediaService;
		this.cloudinaryService = cloudinaryService;
		this.storageServices = storageServices;
		this.notificationService = notificationService;
	}

	// liked in a blog
	@Override
	@Transactional
	public void blogLiked(Long userId, Long blogId) throws ServiceException {

		if (blogRepository.existsByUserIdAndBlogId(userId, blogId)) {
			throw new ServiceException("Blog error, please come back in a few minutes.",
					ResponseStatus.BAD_REQUEST.getHttpStatusCode(), "/api/blog", MethodEnum.GET);
		}

		System.out.println("**********LIKE BLOG*********");

		Optional<BlogEntity> blogEntity = blogRepository.findById(blogId);

		Optional<UserEntity> userEntity = userRepository.findById(userId);

		blogRepository.insertBlogLike(userId, blogId, LocalDateTime.now());

		if (userId != blogEntity.get().getUserId()) {
			// build notification
			NotificationEntity notification = new NotificationEntity();
			notification.setContent(userEntity.get().getUsername() + " liked your post " + blogEntity.get().getTitle());
			notification.setDelivered(false);
			notification.setCreatedAt(LocalDateTime.now());
			notification.setNotificationType(NotificationType.LIKE);
			notification.setRead(false);
			notification.setUserFromId(userId);
			notification.setUserToId(blogEntity.get().getUserId());
			notification.setUpdatedAt(LocalDateTime.now());

			// set type of notification in base of the type (blog, comment, follow etc)
			notification.setTargetId(blogEntity.get().getBlogId());
			notification.setTargetType(NotificationTargetType.BLOG);
			notification.setTargetExtra(null);

			notificationService.createNotificationStorage(notification);
		}

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

		// 1. check user exists
		UserSimpleResponseDTO user = userService.getOneUserSimpleInfo(blogCreateRequestDTO.getUserId());

		// 2. check categories
		List<CategoryEntity> categoryEntities = categoryService.getListCategories(blogCreateRequestDTO.getCategories());

		// 3. generate slug
		String slug = normalizeSlug(blogCreateRequestDTO.getTitle() + "-" + LocalDateTime.now());

		// ==== IMAGE (OPTIONAL) ====
		String imageUrl = null;
		TypeStorage type = null;
		ImageResponseCloudinaryDTO imageResponse = null;

		if (blogCreateRequestDTO.getBlogImage() != null && !blogCreateRequestDTO.getBlogImage().isEmpty()) {

			imageResponse = cloudinaryService.upload(blogCreateRequestDTO.getBlogImage(), "blog_profile_spring");

			type = storageServices.validateExtension(blogCreateRequestDTO.getBlogImage());
			imageUrl = imageResponse.getImageURL();
		}

		// 4. Create entity
		BlogEntity blogEntity = BlogMappers.toCreateABlog(blogCreateRequestDTO, slug, user.getUserId(), imageUrl);

		// 5. Save and flush
		blogRepository.saveAndFlush(blogEntity);

		// 6. Assign categories
		blogEntity.setCategories(categoryEntities);

		// 7. Save final entity
		BlogEntity blogSaved = blogRepository.save(blogEntity);

		// 8. Save media only if image exists
		if (imageUrl != null) {
			mediaService.saveMedia(CloudStorageMappers.fromObjectsToMediaEntity("BLOG", blogSaved.getBlogId(),
					imageResponse, "BLOG_IMAGE", type.name()));
		}

		return blogSaved;
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

		Page<Object[]> result = blogRepository.findBlogCardsReadLaterByUser(userId, pageable);

		List<BlogInfoCardDTO> content = result.getContent().stream().map(BlogMappers::mapRow).toList();

		return new PageImpl<>(content, pageable, result.getTotalElements());
	}

	@Override
	public Page<BlogInfoCardDTO> dashboardGetBlogsByUserPaginated(Long userId, Pageable pageable)
			throws ServiceException {

		Page<Object[]> result = blogRepository.findBlogCardsByUserId(userId, pageable);

		List<BlogInfoCardDTO> content = result.getContent().stream().map(BlogMappers::mapRow).toList();

		return new PageImpl<>(content, pageable, result.getTotalElements());
	}

	@Override
	public Page<BlogsByCategoryInfoDTO> dashboardGetCategoriesFollowedByUserPaginated(Long userId, Pageable pageable)
			throws ServiceException {

		// 1. get categories that user follow
		Page<CategoryFullInfoDTO> categoryFullInfoPage = categoryRepository.findCategoriesByFollowedUser(userId,
				pageable);

		// 2. mapping info
		Page<BlogsByCategoryInfoDTO> blogsByCategoryInfoPage = categoryFullInfoPage.map(categoryFullInfoDTO -> {
			BlogsByCategoryInfoDTO blogsByCategoryInfoDTO = new BlogsByCategoryInfoDTO();

			// 3. get users than follow this category
			List<UserSimpleResponseDTO> userSimpleResponseDTO = categoryRepository
					.findTopUsersByCategory(categoryFullInfoDTO.getName(), pageable);

			// 4. get ids for follows
			List<Long> usersFollowers = categoryRepository
					.findUserFollowersIdsByCategory(categoryFullInfoDTO.getName());

			// 5. build info
			blogsByCategoryInfoDTO.setCategoryFullInfoDTO(categoryFullInfoDTO);
			blogsByCategoryInfoDTO.setFollewersCategory(userSimpleResponseDTO);
			blogsByCategoryInfoDTO.setUsersFollowersIds(usersFollowers);

			return blogsByCategoryInfoDTO;
		});
		return blogsByCategoryInfoPage;
	}

	@Override
	public Page<UserInfoCardDTO> dashboardGetFollowedsByUserPaginated(Long userId, Pageable pageable)
			throws ServiceException {

		Page<UserInfoCardDTO> basePage = userRepository.findUsersFollowedByUser(userId, pageable);

		List<UserInfoCardDTO> enrichedUsers = basePage.getContent().stream().map(user -> {
			List<Long> followerIds = userRepository.getFollowersIds(user.getUserId());
			user.setUsersFollowers(followerIds);
			return user;
		}).toList();

		return new PageImpl<>(enrichedUsers, pageable, basePage.getTotalElements());
	}

	@Override
	public Page<UserInfoCardDTO> dashboardGetFollowersByUserPaginated(Long userId, Pageable pageable)
			throws ServiceException {

		Page<UserInfoCardDTO> basePage = userRepository.findUsersWhoFollowUser(userId, pageable);

		List<UserInfoCardDTO> enrichedUsers = basePage.getContent().stream().map(user -> {
			List<Long> followerIds = userRepository.getFollowersIds(user.getUserId());
			user.setUsersFollowers(followerIds);
			return user;
		}).toList();

		return new PageImpl<>(enrichedUsers, pageable, basePage.getTotalElements());
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

		// 3. if user is same, then we delete blog soft
		blogEntity.setDeletedAt(LocalDateTime.now());
		blogEntity.setDeleted(true);

		blogRepository.save(blogEntity);

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
	public Page<BlogInfoCardDTO> searchBlogs(String query, int page, int size) {

		Pageable pageable = PageRequest.of(page, size);

		Page<Object[]> result = blogRepository.searchBlogCards(query, pageable);

		List<BlogInfoCardDTO> content = result.getContent().stream().map(BlogMappers::mapRow).toList();

		return new PageImpl<>(content, pageable, result.getTotalElements());
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

	private String normalizeSlug(String input) {
		return input.toLowerCase().replaceAll("[^a-z0-9\\s-]", "").replaceAll("\\s+", "-").replaceAll("-+", "-");
	}

}
