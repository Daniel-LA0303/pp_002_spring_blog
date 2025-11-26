package com.mx.dev.blog.spring_001_blog.cloudstorage.storageservices.utils.validations;

import org.springframework.stereotype.Component;

import com.mx.dev.blog.spring_001_blog.user.services.UserService;
import com.mx.dev.blog.spring_001_blog.utils.exceptions.ServiceException;

@Component
public class UserOwnerValidator implements OwnerValidator {

	private final UserService userService;

	public UserOwnerValidator(UserService userService) {
		this.userService = userService;
	}

	@Override
	public String getOwnerType() {
		return "USER";
	}

	@Override
	public void validate(Long ownerId) throws ServiceException {
		userService.getOneUserOrThrow(ownerId);
	}
}
