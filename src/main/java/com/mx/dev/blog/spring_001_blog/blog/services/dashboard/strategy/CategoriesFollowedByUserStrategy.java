package com.mx.dev.blog.spring_001_blog.blog.services.dashboard.strategy;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.mx.dev.blog.spring_001_blog.blog.services.blog.BlogService;
import com.mx.dev.blog.spring_001_blog.utils.exceptions.ServiceException;

@Service
public class CategoriesFollowedByUserStrategy implements DashboardQueryStrategy {

	private final BlogService blogService;

	public CategoriesFollowedByUserStrategy(BlogService blogService) {
		this.blogService = blogService;
	}

	@Override
	public Page<?> execute(Long userId, Pageable pageable) throws ServiceException {
		return blogService.dashboardGetCategoriesFollowedByUserPaginated(userId, pageable);
	}

}