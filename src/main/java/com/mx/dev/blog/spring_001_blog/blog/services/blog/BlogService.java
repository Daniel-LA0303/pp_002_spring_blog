package com.mx.dev.blog.spring_001_blog.blog.services.blog;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.mx.dev.blog.spring_001_blog.blog.entities.BlogEntity;
import com.mx.dev.blog.spring_001_blog.blog.utils.dto.BlogCreateRequestDTO;
import com.mx.dev.blog.spring_001_blog.blog.utils.dto.BlogInfoCardDTO;
import com.mx.dev.blog.spring_001_blog.blog.utils.dto.BlogPageResponseDTO;
import com.mx.dev.blog.spring_001_blog.category.entities.CategoryEntity;
import com.mx.dev.blog.spring_001_blog.user.entities.UserEntity;
import com.mx.dev.blog.spring_001_blog.utils.dtos.info.HomePageResponseDTO;
import com.mx.dev.blog.spring_001_blog.utils.exceptions.ServiceException;

public interface BlogService {

	void blogLiked(Long userId, Long blogId) throws ServiceException;

	void blogRead(Long userId, Long blogId) throws ServiceException;

	void blogUnliked(Long userId, Long blogId) throws ServiceException;

	void blogUnread(Long userId, Long blogId) throws ServiceException;

	BlogEntity createBlog(BlogCreateRequestDTO blogCreateRequestDTO) throws ServiceException;

	// dash board services
	Page<BlogEntity> dashboardGetBlogsByLikedUserPaginated(Long userId, Pageable pageable) throws ServiceException;

	Page<BlogEntity> dashboardGetBlogsByReadLaterUserPaginated(Long userId, Pageable pageable) throws ServiceException;

	Page<BlogEntity> dashboardGetBlogsByUserPaginated(Long userId, Pageable pageable) throws ServiceException;

	Page<CategoryEntity> dashboardGetCategoriesFollowedByUserPaginated(Long userId, Pageable pageable)
			throws ServiceException;

	Page<UserEntity> dashboardGetFollowedsByUserPaginated(Long userId, Pageable pageable) throws ServiceException;

	Page<UserEntity> dashboardGetFollowersByUserPaginated(Long userId, Pageable pageable) throws ServiceException;

	void deleteBlog(Long blogId, Long userId) throws ServiceException;

	BlogEntity getBlogByIdOrThrow(Long blogId) throws ServiceException;

	Page<BlogInfoCardDTO> getBlogsByCategoryNamePaginated(String categoryName, int page, int size);

	Page<BlogInfoCardDTO> getBlogsByUserIdPaginated(Long userId, int page, int size);

	Page<BlogInfoCardDTO> getBlogsPaginated(int page, int size);

	Page<BlogInfoCardDTO> getBlogsPaginatedByLike(int page, int size);

	HomePageResponseDTO getHomePageInfo();

	BlogPageResponseDTO getOneBlog(Long blogId) throws ServiceException;

	BlogEntity updateBlog(BlogCreateRequestDTO blogCreateRequestDTO, Long blogId) throws ServiceException;

}
