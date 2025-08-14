package com.mx.dev.blog.spring_001_blog.services;

import java.util.List;

import com.mx.dev.blog.spring_001_blog.entities.blog.BlogEntity;
import com.mx.dev.blog.spring_001_blog.utils.dtos.blog.BlogCreateRequestDTO;
import com.mx.dev.blog.spring_001_blog.utils.dtos.blog.BlogResponseDTO;
import com.mx.dev.blog.spring_001_blog.utils.dtos.blog.BlogResponsePageDTO;
import com.mx.dev.blog.spring_001_blog.utils.exceptions.ServiceException;

public interface BlogService {

	BlogResponseDTO createBlog(BlogCreateRequestDTO blogCreateRequestDTO) throws ServiceException;

	void deleteBlog(Long blogId, Long userId) throws ServiceException;

	List<BlogResponseDTO> getAllBlogs();

	BlogEntity getBlogByIdOrThrow(Long blogId) throws ServiceException;

	BlogResponsePageDTO getOneBlog(Long blogId) throws ServiceException;

	BlogResponseDTO updateBlog(BlogCreateRequestDTO blogCreateRequestDTO, Long blogId) throws ServiceException;

}
