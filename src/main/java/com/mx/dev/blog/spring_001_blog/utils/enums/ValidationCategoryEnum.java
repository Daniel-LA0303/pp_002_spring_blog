package com.mx.dev.blog.spring_001_blog.utils.enums;

import com.mx.dev.blog.spring_001_blog.utils.constants.regex.CategoryRegex;

public enum ValidationCategoryEnum {

	ALFANUMERIC_150(CategoryRegex.ALFANUMERIC_150, "Must be 150-position alphanumeric"),
	ALFANUMERIC_300(CategoryRegex.ALFANUMERIC_300, "Must be 300-position alphanumeric"),
	COLOR_HEXA(CategoryRegex.COLOR_HEXA, "Must be a color in format hexadecimal");

	private final String regex;

	private final String message;

	ValidationCategoryEnum(String regex, String message) {
		this.regex = regex;
		this.message = message;
	}

	public String getMessage() {
		return message;
	}

	public String getRegex() {
		return regex;
	}
}
