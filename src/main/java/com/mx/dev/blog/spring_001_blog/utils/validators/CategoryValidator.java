package com.mx.dev.blog.spring_001_blog.utils.validators;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;

import com.mx.dev.blog.spring_001_blog.utils.dtos.category.CategoryRequestDTO;
import com.mx.dev.blog.spring_001_blog.utils.enums.MethodEnum;
import com.mx.dev.blog.spring_001_blog.utils.enums.ResponseStatus;
import com.mx.dev.blog.spring_001_blog.utils.enums.ValidationCategoryEnum;
import com.mx.dev.blog.spring_001_blog.utils.exceptions.ServiceException;

public class CategoryValidator implements Validator<CategoryRequestDTO> {

	public Map<String, String> mapValidation = new HashMap<>();

	public boolean isValidateColor(String color) {
		return Pattern.matches(ValidationCategoryEnum.COLOR_HEXA.getRegex(), color);
	}

	public boolean isValidateDescription(String description) {
		return Pattern.matches(ValidationCategoryEnum.ALFANUMERIC_300.getRegex(), description);
	}

	public boolean isValidateName(String name) {
		return Pattern.matches(ValidationCategoryEnum.ALFANUMERIC_150.getRegex(), name);
	}

	@Override
	public void validate(CategoryRequestDTO t) throws ServiceException {
		mapValidation.clear();
		validateDescription(t.getDescription());
		validateName(t.getName());
		validateColor(t.getColor());

		if (!mapValidation.isEmpty()) {
			throw new ServiceException("Validation errors", ResponseStatus.BAD_REQUEST.getHttpStatusCode(),
					"/api/category", MethodEnum.POST, mapValidation);
		}

	}

	public void validateColor(String color) {

		boolean isValid = isValidateColor(color);

		if (!isValid || color.isBlank() || color.isEmpty()) {
			mapValidation.put("color", ValidationCategoryEnum.COLOR_HEXA.getMessage());
		}
	}

	public void validateDescription(String description) {

		boolean isValid = isValidateDescription(description);

		if (!isValid || description.isBlank() || description.isEmpty()) {
			mapValidation.put("description", ValidationCategoryEnum.ALFANUMERIC_300.getMessage());
		}
	}

	public void validateName(String name) {

		boolean isValid = isValidateName(name);

		if (!isValid || name.isBlank() || name.isEmpty()) {
			mapValidation.put("name", ValidationCategoryEnum.ALFANUMERIC_150.getMessage());
		}
	}

}
