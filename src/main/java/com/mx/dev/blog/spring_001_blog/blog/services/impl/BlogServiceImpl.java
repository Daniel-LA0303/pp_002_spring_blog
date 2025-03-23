package com.mx.dev.blog.spring_001_blog.blog.services.impl;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mx.dev.blog.spring_001_blog.blog.entities.BlogEntity;
import com.mx.dev.blog.spring_001_blog.blog.repositories.BlogRepository;
import com.mx.dev.blog.spring_001_blog.blog.services.BlogService;
import com.mx.dev.blog.spring_001_blog.category.entities.CategoryEntity;
import com.mx.dev.blog.spring_001_blog.category.repositories.CategoryRepository;
import com.mx.dev.blog.spring_001_blog.category.services.CategoryService;
import com.mx.dev.blog.spring_001_blog.user.entities.UserEntity;
import com.mx.dev.blog.spring_001_blog.user.repositories.UserRepository;
import com.mx.dev.blog.spring_001_blog.user.services.UserService;
import com.mx.dev.blog.spring_001_blog.utils.dtos.blog.BlogCreateRequestDTO;
import com.mx.dev.blog.spring_001_blog.utils.dtos.blog.BlogEngagementDTO;
import com.mx.dev.blog.spring_001_blog.utils.dtos.blog.BlogInfoCardDTO;
import com.mx.dev.blog.spring_001_blog.utils.dtos.blog.BlogPageResponseDTO;
import com.mx.dev.blog.spring_001_blog.utils.dtos.blog.BlogResponseDTO;
import com.mx.dev.blog.spring_001_blog.utils.dtos.category.CategorySmallInfoDTO;
import com.mx.dev.blog.spring_001_blog.utils.dtos.category.CategoryTopInfoDTO;
import com.mx.dev.blog.spring_001_blog.utils.dtos.info.HomePageResponseDTO;
import com.mx.dev.blog.spring_001_blog.utils.dtos.user.UserInfoCardDTO;
import com.mx.dev.blog.spring_001_blog.utils.dtos.user.UserSimpleResponseDTO;
import com.mx.dev.blog.spring_001_blog.utils.dtos.user.UserTopDTO;
import com.mx.dev.blog.spring_001_blog.utils.enums.BlogStatusEnum;
import com.mx.dev.blog.spring_001_blog.utils.enums.MethodEnum;
import com.mx.dev.blog.spring_001_blog.utils.enums.ResponseStatus;
import com.mx.dev.blog.spring_001_blog.utils.exceptions.ServiceException;
import com.mx.dev.blog.spring_001_blog.utils.mappers.BlogMappers;
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

	@Override
	@Transactional
	public void blogLiked(Long userId, Long blogId) throws ServiceException {
		try {
			if (blogRepository.existsByUserIdAndBlogId(userId, blogId)) {
				throw new ServiceException("Blog error in generate slug, please come back in a few minutes.",
						ResponseStatus.BAD_REQUEST.getHttpStatusCode(), "/api/blog", MethodEnum.GET);
			}

			blogRepository.insertBlogLike(userId, blogId, LocalDateTime.now());

		} catch (Exception e) {
			throw new ServiceException("Blog not found", ResponseStatus.NOT_FOUND.getHttpStatusCode(), "/api/blog",
					MethodEnum.GET);
		}
	}

	@Override
	@Transactional
	public void blogRead(Long userId, Long blogId) throws ServiceException {
		try {
			if (blogRepository.existsByUserIdAndBlogIdRead(userId, blogId)) {
				throw new ServiceException("El blog ya ha sido leído por el usuario.",
						ResponseStatus.BAD_REQUEST.getHttpStatusCode(), "/api/blog/read", MethodEnum.POST);
			}

			blogRepository.insertBlogRead(userId, blogId, LocalDateTime.now());
			System.out.println("***hola****");

		} catch (Exception e) {
			throw new ServiceException("Error al registrar la lectura del blog",
					ResponseStatus.INTERNAL_SERVER_ERROR.getHttpStatusCode(), "/api/blog/read", MethodEnum.POST);
		}

	}

	@Override
	public void blogUnliked(Long userId, Long blogId) throws ServiceException {

		if (!blogRepository.existsByUserIdAndBlogId(userId, blogId)) {
			throw new ServiceException("Blog error in generate slug, please come back in a few minutes.",
					ResponseStatus.BAD_REQUEST.getHttpStatusCode(), "/api/blog", MethodEnum.GET);
		}

		blogRepository.deleteByUserIdAndBlogId(userId, blogId);
	}

	@Override
	public void blogUnread(Long userId, Long blogId) throws ServiceException {

		if (!blogRepository.existsByUserIdAndBlogIdRead(userId, blogId)) {
			throw new ServiceException("El blog no ha sido leído por el usuario.",
					ResponseStatus.BAD_REQUEST.getHttpStatusCode(), "/api/blog/read", MethodEnum.DELETE);
		}

		// Elimina la entrada de lectura del blog para el usuario
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
		BlogEntity blogEntity = new BlogEntity();
		blogEntity.setContent(blogCreateRequestDTO.getContent());
		blogEntity.setCreatedAt(LocalDateTime.now());
		blogEntity.setDescription(blogCreateRequestDTO.getDescription());
		blogEntity.setSlug(slug);
		blogEntity.setStatus(BlogStatusEnum.PUBLISHED);
		blogEntity.setTitle(blogCreateRequestDTO.getTitle());
		blogEntity.setUpdatedAt(LocalDateTime.now());
		blogEntity.setUserId(user.getUserId());

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
	 * get all blog service
	 */
	@Override
	public List<BlogResponseDTO> getAllBlogs() {

		return BlogMappers.toListBlogResponseDTO(blogRepository.findAll());
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

	@Override
	public Page<BlogInfoCardDTO> getBlogsByCategoryNamePaginated(String categoryName, int page, int size) {
		Pageable pageable = PageRequest.of(page, size);

		// Consulta de blogs por nombre de categoría
		Page<BlogEntity> blogEntities = blogRepository.findBlogsByCategoryName(categoryName, pageable);

		// Obtener IDs de los blogs
		List<Long> blogIds = blogEntities.getContent().stream().map(BlogEntity::getBlogId).distinct()
				.collect(Collectors.toList());

		// Obtener datos de compromiso (engagement)
		List<BlogEngagementDTO> engagementList = blogRepository.getBlogEngagementDataForBlogs(blogIds);

		// Crear un mapa para acceso rápido a los datos de compromiso
		Map<Long, BlogEngagementDTO> engagementData = engagementList.stream().filter(dto -> dto.getBlogId() != null)
				.collect(Collectors.toMap(BlogEngagementDTO::getBlogId, dto -> dto, (existing, duplicate) -> {
					existing.setCommentsNumber(existing.getCommentsNumber() + duplicate.getCommentsNumber());
					existing.setLikesNumber(existing.getLikesNumber() + duplicate.getLikesNumber());
					existing.setSavedNumber(existing.getSavedNumber() + duplicate.getSavedNumber());
					return existing;
				}));

		// Obtener IDs de usuarios únicos
		List<Long> userIds = blogEntities.getContent().stream().map(BlogEntity::getUserId).distinct()
				.collect(Collectors.toList());

		// Consultar los nombres de usuario
		Map<Long, String> usernames = userRepository.findByIds(userIds).stream()
				.collect(Collectors.toMap(UserEntity::getUserId, UserEntity::getUsername));

		// Obtener los usuarios que han dado like a los blogs
		List<Object[]> likesResults = blogRepository.findUserIdsLikeByBlogIds(blogIds);
		Map<Long, List<Long>> blogLikesMap = likesResults.stream().collect(Collectors.groupingBy(row -> (Long) row[0],
				Collectors.mapping(row -> (Long) row[1], Collectors.toList())));

		// Obtener los usuarios que han guardado los blogs
		List<Object[]> savedResults = blogRepository.findUserIdsReadByBlogIds(blogIds);
		Map<Long, List<Long>> blogSavesMap = savedResults.stream().collect(Collectors.groupingBy(row -> (Long) row[0],
				Collectors.mapping(row -> (Long) row[1], Collectors.toList())));

		// Mapear los resultados a DTOs
		Page<BlogInfoCardDTO> blogInfoCards = blogEntities.map(blogEntity -> {
			BlogEngagementDTO engagementDTO = engagementData.get(blogEntity.getBlogId());
			if (engagementDTO == null) {
				engagementDTO = new BlogEngagementDTO(blogEntity.getBlogId(), 0L, 0L, 0L);
			}

			BlogInfoCardDTO dto = BlogMappers.toBlogInfoCardDTO(blogEntity, usernames.get(blogEntity.getUserId()));
			dto.setBlogEngagementDTO(engagementDTO);

			// Set the users who liked and saved the blog
			dto.setUsersLiked(blogLikesMap.getOrDefault(blogEntity.getBlogId(), new ArrayList<>()));
			dto.setUsersReaded(blogSavesMap.getOrDefault(blogEntity.getBlogId(), new ArrayList<>()));

			return dto;
		});

		return blogInfoCards;
	}

	@Override
	public Page<BlogInfoCardDTO> getBlogsByUserIdPaginated(Long userId, int page, int size) {
		Pageable pageable = PageRequest.of(page, size);

		Page<BlogEntity> blogEntities = blogRepository.findBlogsByUserId(userId, pageable);

		List<Long> blogIds = blogEntities.getContent().stream().map(BlogEntity::getBlogId).distinct()
				.collect(Collectors.toList());

		List<BlogEngagementDTO> engagementList = blogRepository.getBlogEngagementDataForBlogs(blogIds);

		Map<Long, BlogEngagementDTO> engagementData = engagementList.stream().filter(dto -> dto.getBlogId() != null)
				.collect(Collectors.toMap(BlogEngagementDTO::getBlogId, dto -> dto, (existing, duplicate) -> {
					existing.setCommentsNumber(existing.getCommentsNumber() + duplicate.getCommentsNumber());
					existing.setLikesNumber(existing.getLikesNumber() + duplicate.getLikesNumber());
					existing.setSavedNumber(existing.getSavedNumber() + duplicate.getSavedNumber());
					return existing;
				}));

		List<Object[]> likesResults = blogRepository.findUserIdsLikeByBlogIds(blogIds);
		Map<Long, List<Long>> blogLikesMap = likesResults.stream().collect(Collectors.groupingBy(row -> (Long) row[0], // blogId
				Collectors.mapping(row -> (Long) row[1], Collectors.toList())));

		List<Object[]> readResults = blogRepository.findUserIdsReadByBlogIds(blogIds);
		Map<Long, List<Long>> blogReadsMap = readResults.stream().collect(Collectors.groupingBy(row -> (Long) row[0], // blogId
				Collectors.mapping(row -> (Long) row[1], Collectors.toList())));

		List<Long> userIds = blogEntities.getContent().stream().map(BlogEntity::getUserId).distinct()
				.collect(Collectors.toList());

		Map<Long, String> usernames = userRepository.findByIds(userIds).stream()
				.collect(Collectors.toMap(UserEntity::getUserId, UserEntity::getUsername));

		Page<BlogInfoCardDTO> blogInfoCards = blogEntities.map(blogEntity -> {
			BlogEngagementDTO engagementDTO = engagementData.get(blogEntity.getBlogId());
			if (engagementDTO == null) {
				engagementDTO = new BlogEngagementDTO(blogEntity.getBlogId(), 0L, 0L, 0L);
			}

			BlogInfoCardDTO dto = BlogMappers.toBlogInfoCardDTO(blogEntity, usernames.get(blogEntity.getUserId()));
			dto.setBlogEngagementDTO(engagementDTO);

			dto.setUsersLiked(blogLikesMap.getOrDefault(blogEntity.getBlogId(), new ArrayList<>()));
			dto.setUsersReaded(blogReadsMap.getOrDefault(blogEntity.getBlogId(), new ArrayList<>()));

			return dto;
		});

		return blogInfoCards;
	}

	/*
	 * @Override public Page<BlogInfoCardDTO> getBlogsPaginatedByRead(Long userId,
	 * int page, int size) { Pageable pageable = PageRequest.of(page, size);
	 * 
	 * // Obtener los blogs que el usuario ha leído. Page<BlogEntity> blogEntities =
	 * blogRepository.findBlogsReadByUser(userId, pageable);
	 * 
	 * // Extraer los IDs únicos de los blogs. List<Long> blogIds =
	 * blogEntities.getContent().stream().map(BlogEntity::getBlogId).distinct()
	 * .collect(Collectors.toList());
	 * 
	 * // Obtener los datos de engagement para estos blogs. List<BlogEngagementDTO>
	 * engagementList = blogRepository.getBlogEngagementDataForBlogs(blogIds);
	 * 
	 * // Mapear los datos de engagement a un Map para un acceso rápido. Map<Long,
	 * BlogEngagementDTO> engagementData = engagementList.stream().filter(dto ->
	 * dto.getBlogId() != null)
	 * .collect(Collectors.toMap(BlogEngagementDTO::getBlogId, dto -> dto,
	 * (existing, duplicate) -> {
	 * existing.setCommentsNumber(existing.getCommentsNumber() +
	 * duplicate.getCommentsNumber());
	 * existing.setLikesNumber(existing.getLikesNumber() +
	 * duplicate.getLikesNumber());
	 * existing.setSavedNumber(existing.getSavedNumber() +
	 * duplicate.getSavedNumber()); return existing; }));
	 * 
	 * // Extraer los IDs únicos de los usuarios que escribieron los blogs.
	 * List<Long> userIds =
	 * blogEntities.getContent().stream().map(BlogEntity::getUserId).distinct()
	 * .collect(Collectors.toList());
	 * 
	 * // Obtener los nombres de usuario para estos IDs. Map<Long, String> usernames
	 * = userRepository.findByIds(userIds).stream()
	 * .collect(Collectors.toMap(UserEntity::getUserId, UserEntity::getUsername));
	 * 
	 * // Convertir las entidades de blogs en DTOs para la salida final.
	 * Page<BlogInfoCardDTO> blogInfoCards = blogEntities.map(blogEntity -> {
	 * 
	 * // Obtener el engagement del blog. BlogEngagementDTO engagementDTO =
	 * engagementData.get(blogEntity.getBlogId());
	 * 
	 * // Si no hay datos de engagement, inicializar un DTO vacío. if (engagementDTO
	 * == null) { engagementDTO = new BlogEngagementDTO(blogEntity.getBlogId(), 0L,
	 * 0L, 0L); }
	 * 
	 * // Convertir el blog en un DTO y añadir el engagement. BlogInfoCardDTO dto =
	 * BlogMappers.toBlogInfoCardDTO(blogEntity,
	 * usernames.get(blogEntity.getUserId()));
	 * dto.setBlogEngagementDTO(engagementDTO);
	 * 
	 * return dto; });
	 * 
	 * return blogInfoCards; }
	 */

	@Override
	public Page<BlogInfoCardDTO> getBlogsPaginated(int page, int size) {
		Pageable pageable = PageRequest.of(page, size);

		Page<BlogEntity> blogEntities = blogRepository.findAll(pageable);

		List<Long> blogIds = blogEntities.getContent().stream().map(BlogEntity::getBlogId).distinct()
				.collect(Collectors.toList());

		System.out.println("**** Blog IDs: " + blogIds);

		List<BlogEngagementDTO> engagementList = blogRepository.getBlogEngagementDataForBlogs(blogIds);

		System.out.println("**** Engagement Data: " + engagementList);
		engagementList.forEach(value -> {
			System.out.println("BlogId engagement: " + value.getBlogId() + ", comments: " + value.getCommentsNumber()
					+ ", likes: " + value.getLikesNumber() + ", saved: " + value.getSavedNumber());
		});

		Map<Long, BlogEngagementDTO> engagementData = engagementList.stream().filter(dto -> dto.getBlogId() != null)
				.collect(Collectors.toMap(BlogEngagementDTO::getBlogId, dto -> dto, (existing, duplicate) -> {
					existing.setCommentsNumber(existing.getCommentsNumber() + duplicate.getCommentsNumber());
					existing.setLikesNumber(existing.getLikesNumber() + duplicate.getLikesNumber());
					existing.setSavedNumber(existing.getSavedNumber() + duplicate.getSavedNumber());
					return existing;
				}));

		System.out.println("**** Engagement Map: " + engagementData);

		List<Object[]> likesResults = blogRepository.findUserIdsLikeByBlogIds(blogIds);
		Map<Long, List<Long>> blogLikesMap = likesResults.stream().collect(Collectors.groupingBy(row -> (Long) row[0], // blogId
				Collectors.mapping(row -> (Long) row[1], Collectors.toList())));

		List<Object[]> readResults = blogRepository.findUserIdsReadByBlogIds(blogIds);
		Map<Long, List<Long>> blogReadsMap = readResults.stream().collect(Collectors.groupingBy(row -> (Long) row[0], // blogId
				Collectors.mapping(row -> (Long) row[1], Collectors.toList())));

		List<Long> userIds = blogEntities.getContent().stream().map(BlogEntity::getUserId).distinct()
				.collect(Collectors.toList());

		Map<Long, String> usernames = userRepository.findByIds(userIds).stream()
				.collect(Collectors.toMap(UserEntity::getUserId, UserEntity::getUsername));

		// Construcción final del DTO
		Page<BlogInfoCardDTO> blogInfoCards = blogEntities.map(blogEntity -> {
			BlogEngagementDTO engagementDTO = engagementData.get(blogEntity.getBlogId());
			BlogInfoCardDTO dto = BlogMappers.toBlogInfoCardDTO(blogEntity, usernames.get(blogEntity.getUserId()));

			dto.setBlogEngagementDTO(engagementDTO != null ? engagementDTO : new BlogEngagementDTO());

			// Asignar likes y reads al DTO
			dto.setUsersLiked(blogLikesMap.getOrDefault(blogEntity.getBlogId(), new ArrayList<>()));
			dto.setUsersReaded(blogReadsMap.getOrDefault(blogEntity.getBlogId(), new ArrayList<>()));

			return dto;
		});

		return blogInfoCards;
	}

	@Override
	public Page<BlogInfoCardDTO> getBlogsPaginatedByLike(int page, int size) {
		Pageable pageable = PageRequest.of(page, size);

		Page<BlogEntity> blogEntities = blogRepository.findBlogsLikedByUser(1L, pageable);

		List<Long> blogIds = blogEntities.getContent().stream().map(BlogEntity::getBlogId).distinct()
				.collect(Collectors.toList());

		List<BlogEngagementDTO> engagementList = blogRepository.getBlogEngagementDataForBlogs(blogIds);

		Map<Long, BlogEngagementDTO> engagementData = engagementList.stream().filter(dto -> dto.getBlogId() != null)
				.collect(Collectors.toMap(BlogEngagementDTO::getBlogId, dto -> dto, (existing, duplicate) -> {
					existing.setCommentsNumber(existing.getCommentsNumber() + duplicate.getCommentsNumber());
					existing.setLikesNumber(existing.getLikesNumber() + duplicate.getLikesNumber());
					existing.setSavedNumber(existing.getSavedNumber() + duplicate.getSavedNumber());
					return existing;
				}));

		List<Long> userIds = blogEntities.getContent().stream().map(BlogEntity::getUserId).distinct()
				.collect(Collectors.toList());

		Map<Long, String> usernames = userRepository.findByIds(userIds).stream()
				.collect(Collectors.toMap(UserEntity::getUserId, UserEntity::getUsername));

		Page<BlogInfoCardDTO> blogInfoCards = blogEntities.map(blogEntity -> {

			BlogEngagementDTO engagementDTO = engagementData.get(blogEntity.getBlogId());

			if (engagementDTO == null) {
				engagementDTO = new BlogEngagementDTO(blogEntity.getBlogId(), 0L, 0L, 0L);
			}

			BlogInfoCardDTO dto = BlogMappers.toBlogInfoCardDTO(blogEntity, usernames.get(blogEntity.getUserId()));
			dto.setBlogEngagementDTO(engagementDTO);

			return dto;
		});

		return blogInfoCards;
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
	 * get one blog service
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

		BlogPageResponseDTO blogResponsePageDTO = new BlogPageResponseDTO();
		blogResponsePageDTO.setBlogId(blogEntity.getBlogId());
		blogResponsePageDTO.setTitle(blogEntity.getTitle());
		blogResponsePageDTO.setDescription(blogEntity.getDescription());
		blogResponsePageDTO.setContent(blogEntity.getContent());
		blogResponsePageDTO.setStatus(blogEntity.getStatus());
		blogResponsePageDTO.setSlug(blogEntity.getSlug());
		blogResponsePageDTO.setCreatedAt(blogEntity.getCreatedAt());
		blogResponsePageDTO.setCategories(categories);
		blogResponsePageDTO.setUserInfoCardDTO(userInfoCardDTO);
		blogResponsePageDTO.setBlogEngagementDTO(blogEngagementDTO);
		blogResponsePageDTO.setUsersLiked(usersLiked);
		blogResponsePageDTO.setUsersReaded(usersReaded);

		return blogResponsePageDTO;
	}

	// search blogs
	@Override
	public Page<BlogInfoCardDTO> searchBlogs(String query, int page, int size) {
		Pageable pageable = PageRequest.of(page, size);

		// Paso 1: Obtener los blogs (filtrados o no)
		Page<BlogEntity> blogEntities;
		if (query != null && !query.isEmpty()) {
			// Buscar blogs que coincidan con el término de búsqueda
			blogEntities = blogRepository.findByTitleContainingIgnoreCase(query, pageable);
		} else {
			// Obtener todos los blogs
			blogEntities = blogRepository.findAll(pageable);
		}

		// Paso 2: Mapear los resultados a BlogInfoCardDTO
		return mapToBlogInfoCardDTO(blogEntities);
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

	// Método privado para reutilizar la lógica de mapeo
	private Page<BlogInfoCardDTO> mapToBlogInfoCardDTO(Page<BlogEntity> blogEntities) {
		List<Long> blogIds = blogEntities.getContent().stream().map(BlogEntity::getBlogId).distinct()
				.collect(Collectors.toList());

		// Obtener datos de engagement (likes, comentarios, guardados)
		List<BlogEngagementDTO> engagementList = blogRepository.getBlogEngagementDataForBlogs(blogIds);
		Map<Long, BlogEngagementDTO> engagementData = engagementList.stream().filter(dto -> dto.getBlogId() != null)
				.collect(Collectors.toMap(BlogEngagementDTO::getBlogId, dto -> dto, (existing, duplicate) -> {
					existing.setCommentsNumber(existing.getCommentsNumber() + duplicate.getCommentsNumber());
					existing.setLikesNumber(existing.getLikesNumber() + duplicate.getLikesNumber());
					existing.setSavedNumber(existing.getSavedNumber() + duplicate.getSavedNumber());
					return existing;
				}));

		// Obtener usuarios que dieron like a los blogs
		List<Object[]> likesResults = blogRepository.findUserIdsLikeByBlogIds(blogIds);
		Map<Long, List<Long>> blogLikesMap = likesResults.stream().collect(Collectors.groupingBy(row -> (Long) row[0], // blogId
				Collectors.mapping(row -> (Long) row[1], Collectors.toList())));

		// Obtener usuarios que leyeron los blogs
		List<Object[]> readResults = blogRepository.findUserIdsReadByBlogIds(blogIds);
		Map<Long, List<Long>> blogReadsMap = readResults.stream().collect(Collectors.groupingBy(row -> (Long) row[0], // blogId
				Collectors.mapping(row -> (Long) row[1], Collectors.toList())));

		// Obtener nombres de usuario
		List<Long> userIds = blogEntities.getContent().stream().map(BlogEntity::getUserId).distinct()
				.collect(Collectors.toList());
		Map<Long, String> usernames = userRepository.findByIds(userIds).stream()
				.collect(Collectors.toMap(UserEntity::getUserId, UserEntity::getUsername));

		// Mapear a BlogInfoCardDTO
		return blogEntities.map(blogEntity -> {
			BlogEngagementDTO engagementDTO = engagementData.get(blogEntity.getBlogId());
			BlogInfoCardDTO dto = BlogMappers.toBlogInfoCardDTO(blogEntity, usernames.get(blogEntity.getUserId()));

			dto.setBlogEngagementDTO(engagementDTO != null ? engagementDTO : new BlogEngagementDTO());

			// Asignar likes y reads al DTO
			dto.setUsersLiked(blogLikesMap.getOrDefault(blogEntity.getBlogId(), new ArrayList<>()));
			dto.setUsersReaded(blogReadsMap.getOrDefault(blogEntity.getBlogId(), new ArrayList<>()));

			return dto;
		});
	}

}
