package com.mx.dev.blog.spring_001_blog.services;

import java.util.List;

import org.springframework.data.domain.Page;

import com.mx.dev.blog.spring_001_blog.entities.blog.BlogEntity;
import com.mx.dev.blog.spring_001_blog.utils.dtos.blog.BlogCreateRequestDTO;
import com.mx.dev.blog.spring_001_blog.utils.dtos.blog.BlogInfoCardDTO;
import com.mx.dev.blog.spring_001_blog.utils.dtos.blog.BlogPageResponseDTO;
import com.mx.dev.blog.spring_001_blog.utils.dtos.blog.BlogResponseDTO;
import com.mx.dev.blog.spring_001_blog.utils.dtos.info.HomePageResponseDTO;
import com.mx.dev.blog.spring_001_blog.utils.exceptions.ServiceException;

public interface BlogService {

	BlogEntity createBlog(BlogCreateRequestDTO blogCreateRequestDTO) throws ServiceException;

	void deleteBlog(Long blogId, Long userId) throws ServiceException;

	List<BlogResponseDTO> getAllBlogs();

	BlogEntity getBlogByIdOrThrow(Long blogId) throws ServiceException;

	Page<BlogInfoCardDTO> getBlogsByCategoryNamePaginated(String categoryName, int page, int size);

	Page<BlogInfoCardDTO> getBlogsByUserIdPaginated(Long userId, int page, int size);

	Page<BlogInfoCardDTO> getBlogsPaginated(int page, int size);

	Page<BlogInfoCardDTO> getBlogsPaginatedByLike(int page, int size);

	// Page<BlogInfoCardDTO> getBlogsPaginatedByRead(Long userId, int page, int
	// size);

	HomePageResponseDTO getHomePageInfo();

	BlogPageResponseDTO getOneBlog(Long blogId) throws ServiceException;

	BlogEntity updateBlog(BlogCreateRequestDTO blogCreateRequestDTO, Long blogId) throws ServiceException;

}
