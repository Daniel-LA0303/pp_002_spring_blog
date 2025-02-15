package com.mx.dev.blog.spring_001_blog.utils.validators;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;

import com.mx.dev.blog.spring_001_blog.utils.constants.regex.AuthRegex;
import com.mx.dev.blog.spring_001_blog.utils.dtos.user.LoginDTO;
import com.mx.dev.blog.spring_001_blog.utils.enums.MethodEnum;
import com.mx.dev.blog.spring_001_blog.utils.enums.ResponseStatus;
import com.mx.dev.blog.spring_001_blog.utils.exceptions.ServiceException;

public class AuthLoginValidator implements Validator<LoginDTO> {

	public Map<String, String> mapValidation = new HashMap<>();

	public boolean isValidEmail(String email) {
		return Pattern.matches(AuthRegex.EMAIL, email);
	}

	public boolean isValidPassword(String password) {
		return Pattern.matches(AuthRegex.ALFANUMERIC_200, password);
	}

	@Override
	public void validate(LoginDTO t) throws ServiceException {
		mapValidation.clear();

		validateEmail(t.getEmail());
		validatePassword(t.getPassword());

		if (!mapValidation.isEmpty()) {
			throw new ServiceException("Validation errors", ResponseStatus.BAD_REQUEST.getHttpStatusCode(), "/api/auth",
					MethodEnum.POST, mapValidation);
		}
	}

	public void validateEmail(String email) {

		boolean isValid = isValidEmail(email);

		if (!isValid || email.isBlank() || email.isEmpty()) {
			mapValidation.put("email", "Email can not be empty or email invalid");
		}
	}

	public void validatePassword(String password) {

		boolean isValid = isValidPassword(password);

		if (!isValid || password.isBlank() || password.isEmpty()) {
			mapValidation.put("password", "Password can not be empty or password invalid");
		}
	}

}
