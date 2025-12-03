package com.mx.dev.blog.spring_001_blog.utils.validators;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;

<<<<<<< HEAD
import com.mx.dev.blog.spring_001_blog.utils.dtos.blog.BlogCreateRequestDTO;
=======
import com.mx.dev.blog.spring_001_blog.blog.utils.dto.BlogCreateRequestDTO;
import com.mx.dev.blog.spring_001_blog.utils.constants.regex.BlogRegex;
>>>>>>> LAZD-service-blog
import com.mx.dev.blog.spring_001_blog.utils.enums.MethodEnum;
import com.mx.dev.blog.spring_001_blog.utils.enums.ResponseStatus;
import com.mx.dev.blog.spring_001_blog.utils.enums.ValidationBlogEnum;
import com.mx.dev.blog.spring_001_blog.utils.exceptions.ServiceException;

public class BlogValidator implements Validator<BlogCreateRequestDTO> {

	public Map<String, String> mapValidation = new HashMap<>();

	public boolean isValidateDescription(String description) {
		return Pattern.matches(ValidationBlogEnum.ALFANUMERIC_300.getRegex(), description);
	}

	public boolean isValidateTitle(String title) {
		return Pattern.matches(ValidationBlogEnum.ALFANUMERIC_200.getRegex(), title);
	}

	@Override
	public void validate(BlogCreateRequestDTO t) throws ServiceException {
		mapValidation.clear();
		validateDescription(t.getDescription());
		validateContent(t.getContent());
		validateTitle(t.getTitle());

		if (!mapValidation.isEmpty()) {
			throw new ServiceException("Validation errors", ResponseStatus.BAD_REQUEST.getHttpStatusCode(), "/api/blog",
					MethodEnum.POST, mapValidation);
		}
	}

	public void validateContent(String content) {

		if (content.isBlank() || content.isEmpty()) {
			mapValidation.put("content", "Your content is in blank.");
		}
	}

	public void validateDescription(String description) {

		boolean isValid = isValidateDescription(description);

		if (!isValid || description.isBlank() || description.isEmpty()) {
			mapValidation.put("description", ValidationBlogEnum.ALFANUMERIC_300.getMessage());
		}
	}

	public void validateTitle(String title) {

		boolean isValid = isValidateTitle(title);

		if (!isValid || title.isBlank() || title.isEmpty()) {
			mapValidation.put("title", ValidationBlogEnum.ALFANUMERIC_200.getMessage());
		}
	}

}
