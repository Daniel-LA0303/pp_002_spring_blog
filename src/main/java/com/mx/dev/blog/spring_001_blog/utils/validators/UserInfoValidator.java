package com.mx.dev.blog.spring_001_blog.utils.validators;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;

import com.mx.dev.blog.spring_001_blog.utils.constants.regex.UserRegex;
import com.mx.dev.blog.spring_001_blog.utils.dtos.user.UserUpdateInfoRequestDTO;
import com.mx.dev.blog.spring_001_blog.utils.enums.MethodEnum;
import com.mx.dev.blog.spring_001_blog.utils.enums.ResponseStatus;
import com.mx.dev.blog.spring_001_blog.utils.exceptions.ServiceException;

public class UserInfoValidator implements Validator<UserUpdateInfoRequestDTO> {

	public Map<String, String> mapValidation = new HashMap<>();

	public boolean isValidateDirection(String direction) {
		return Pattern.matches(UserRegex.ALFANUMERIC_200, direction);
	}

	@Override
	public void validate(UserUpdateInfoRequestDTO t) throws ServiceException {
		mapValidation.clear();

		validateBio(t.getBio());
		// validateDirection(t.getDirection());
		// validatePhone(t.getPhone());

		if (!mapValidation.isEmpty()) {
			throw new ServiceException("Validation errors", ResponseStatus.BAD_REQUEST.getHttpStatusCode(), "/api/user",
					MethodEnum.POST, mapValidation);
		}
	}

	public void validateBio(String bio) {

		if (bio.isBlank() || bio.isEmpty()) {
			mapValidation.put("bio", "Bio can not be empty");
		}
	}

	public void validateDirection(String direction) {

		boolean isValid = isValidateDirection(direction);

		if (!isValid || direction.isBlank() || direction.isEmpty()) {
			mapValidation.put("direction", "direction can not be empty");
		}
	}

	public void validatePhone(String phone) {

		if (phone.isBlank() || phone.isEmpty()) {
			mapValidation.put("phone", "Phone can not be empty");
		}
	}

}
