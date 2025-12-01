package com.mx.dev.blog.spring_001_blog.blog.services.dashboard.strategy;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.mx.dev.blog.spring_001_blog.utils.exceptions.ServiceException;

public interface DashboardQueryStrategy {
	Page<?> execute(Long userId, Pageable pageable) throws ServiceException;
}
