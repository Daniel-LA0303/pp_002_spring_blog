package com.mx.dev.blog.spring_001_blog.utils.enums;

import com.mx.dev.blog.spring_001_blog.utils.constants.regex.BlogRegex;

public enum ValidationBlogEnum {

	ALFANUMERIC_200(BlogRegex.ALFANUMERIC_200, "Must be 200-position alphanumeric"),
	ALFANUMERIC_300(BlogRegex.ALFANUMERIC_300, "Must be 300-position alphanumeric");

	private final String regex;
	private final String message;

	ValidationBlogEnum(String regex, String message) {
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
