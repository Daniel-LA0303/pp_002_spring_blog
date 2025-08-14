package com.mx.dev.blog.spring_001_blog.utils.validators;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;

import com.mx.dev.blog.spring_001_blog.utils.constants.regex.UserRegex;
import com.mx.dev.blog.spring_001_blog.utils.dtos.user.UserCreateRequestDTO;
import com.mx.dev.blog.spring_001_blog.utils.enums.MethodEnum;
import com.mx.dev.blog.spring_001_blog.utils.enums.ResponseStatus;
import com.mx.dev.blog.spring_001_blog.utils.enums.ValidationUserEnum;
import com.mx.dev.blog.spring_001_blog.utils.exceptions.ServiceException;

public class UserValidator implements Validator<UserCreateRequestDTO> {

	public Map<String, String> mapValidation = new HashMap<>();

	public boolean isValidateEmail(String email) {
		return Pattern.matches(UserRegex.EMAIL, email);
	}

	public boolean isValidateUsername(String username) {
		return Pattern.matches(UserRegex.ALFANUMERIC_150, username);
	}

	public boolean isValidPassword(String password) {
		return Pattern.matches(UserRegex.ALFANUMERIC_200, password);
	}

	@Override
	public void validate(UserCreateRequestDTO t) throws ServiceException {
		mapValidation.clear();
		validateEmail(t.getEmail());
		validateUsername(t.getUsername());
		validatePassword(t.getPassword());

		if (!mapValidation.isEmpty()) {
			throw new ServiceException("Validation errors", ResponseStatus.BAD_REQUEST.getHttpStatusCode(), "/api/user",
					MethodEnum.POST, mapValidation);
		}
	}

	public void validateEmail(String email) {

		boolean isValid = isValidateEmail(email);

		if (!isValid || email.isBlank() || email.isEmpty()) {
			mapValidation.put("email", ValidationUserEnum.EMAIL.getMessage());
		}
	}

	public void validatePassword(String password) {

		boolean isValid = isValidPassword(password);

		if (!isValid || password.isBlank() || password.isEmpty()) {
			mapValidation.put("password", "Please password can not be empty");
		}
	}

	public void validateUsername(String username) {

		boolean isValid = isValidateUsername(username);

		if (!isValid || username.isBlank() || username.isEmpty()) {
			mapValidation.put("username", "Please username can not be empty");
		}
	}

}
