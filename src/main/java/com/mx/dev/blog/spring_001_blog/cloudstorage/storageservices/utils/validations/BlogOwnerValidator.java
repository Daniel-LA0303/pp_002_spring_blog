package com.mx.dev.blog.spring_001_blog.cloudstorage.storageservices.utils.validations;

import org.springframework.stereotype.Component;

import com.mx.dev.blog.spring_001_blog.blog.services.blog.BlogService;
import com.mx.dev.blog.spring_001_blog.utils.exceptions.ServiceException;

@Component
public class BlogOwnerValidator implements OwnerValidator {

	private final BlogService blogService;

	public BlogOwnerValidator(BlogService blogService) {
		this.blogService = blogService;
	}

	@Override
	public String getOwnerType() {
		return "BLOG";
	}

	@Override
	public void validate(Long ownerId) throws ServiceException {
		blogService.getBlogByIdOrThrow(ownerId);
	}
}