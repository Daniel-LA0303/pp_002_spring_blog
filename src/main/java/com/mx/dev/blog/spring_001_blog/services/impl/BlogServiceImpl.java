package com.mx.dev.blog.spring_001_blog.services.impl;

import java.time.LocalDateTime;
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

import com.mx.dev.blog.spring_001_blog.entities.blog.BlogEntity;
import com.mx.dev.blog.spring_001_blog.entities.ctaegory.CategoryEntity;
import com.mx.dev.blog.spring_001_blog.entities.user.UserEntity;
import com.mx.dev.blog.spring_001_blog.repositories.BlogRepository;
import com.mx.dev.blog.spring_001_blog.repositories.UserRepository;
import com.mx.dev.blog.spring_001_blog.services.BlogService;
import com.mx.dev.blog.spring_001_blog.services.CategoryService;
import com.mx.dev.blog.spring_001_blog.services.UserService;
import com.mx.dev.blog.spring_001_blog.utils.dtos.blog.BlogCreateRequestDTO;
import com.mx.dev.blog.spring_001_blog.utils.dtos.blog.BlogEngagementDTO;
import com.mx.dev.blog.spring_001_blog.utils.dtos.blog.BlogInfoCardDTO;
import com.mx.dev.blog.spring_001_blog.utils.dtos.blog.BlogPageResponseDTO;
import com.mx.dev.blog.spring_001_blog.utils.dtos.blog.BlogResponseDTO;
import com.mx.dev.blog.spring_001_blog.utils.dtos.category.CategorySmallInfoDTO;
import com.mx.dev.blog.spring_001_blog.utils.dtos.user.UserInfoCardDTO;
import com.mx.dev.blog.spring_001_blog.utils.dtos.user.UserSimpleResponseDTO;
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
	public Page<BlogInfoCardDTO> getBlogsPaginated(int page, int size) {
		Pageable pageable = PageRequest.of(page, size);

		// Obtener la página de entidades desde el repositorio
		Page<BlogEntity> blogEntities = blogRepository.findAll(pageable);

		// Extraer los userIds únicos de los blogs
		List<Long> userIds = blogEntities.stream().map(BlogEntity::getUserId).distinct().collect(Collectors.toList());

		// Obtener los nombres de usuario por userId
		Map<Long, String> usernames = userRepository.findByIds(userIds).stream()
				.collect(Collectors.toMap(UserEntity::getUserId, UserEntity::getUsername));

		// Mapear a BlogInfoCardDTO
		return BlogMappers.toPageBlogInfoCardDTO(blogEntities, usernames);
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

		// 3. we need some info from user
		UserInfoCardDTO userInfoCardDTO = userRepository.getUserInfoCard(blogEntity.getUserId());

		// 4. prepare info
		List<CategorySmallInfoDTO> categories = CategoryMappers.toListCategorySmallInfo(blogEntity.getCategories());

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

		return blogResponsePageDTO;

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
