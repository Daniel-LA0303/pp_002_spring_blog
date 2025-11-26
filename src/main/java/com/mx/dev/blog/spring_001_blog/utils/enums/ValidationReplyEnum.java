package com.mx.dev.blog.spring_001_blog.utils.enums;

import com.mx.dev.blog.spring_001_blog.utils.constants.regex.CategoryRegex;

public enum ValidationReplyEnum {

	ALFANUMERIC_300(CategoryRegex.ALFANUMERIC_300, "Must be 300-position alphanumeric");

	private final String regex;

	private final String message;

	ValidationReplyEnum(String regex, String message) {
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
