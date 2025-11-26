package com.mx.dev.blog.spring_001_blog.utils.validators;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;

import com.mx.dev.blog.spring_001_blog.utils.dtos.comment.CommentCreateRequestDTO;
import com.mx.dev.blog.spring_001_blog.utils.enums.MethodEnum;
import com.mx.dev.blog.spring_001_blog.utils.enums.ResponseStatus;
import com.mx.dev.blog.spring_001_blog.utils.enums.ValidationCommentEnum;
import com.mx.dev.blog.spring_001_blog.utils.exceptions.ServiceException;

public class CommentValidator implements Validator<CommentCreateRequestDTO> {

	public Map<String, String> mapValidation = new HashMap<>();

	public boolean isValidateContent(String content) {
		return Pattern.matches(ValidationCommentEnum.ALFANUMERIC_300.getRegex(), content);
	}

	@Override
	public void validate(CommentCreateRequestDTO t) throws ServiceException {
		mapValidation.clear();
		validateContent(t.getContent());

		if (!mapValidation.isEmpty()) {
			throw new ServiceException("Validation errors", ResponseStatus.BAD_REQUEST.getHttpStatusCode(),
					"/api/comment", MethodEnum.POST, mapValidation);
		}

	}

	public void validateContent(String content) {

		boolean isValid = isValidateContent(content);

		if (!isValid || content.isBlank() || content.isEmpty()) {
			mapValidation.put("content", ValidationCommentEnum.ALFANUMERIC_300.getMessage());
		}
	}

}
