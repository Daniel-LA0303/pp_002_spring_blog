package com.mx.dev.blog.spring_001_blog.utils.enums;

import com.mx.dev.blog.spring_001_blog.utils.constants.regex.UserRegex;

public enum ValidationUserEnum {

	EMAIL(UserRegex.EMAIL, "Must be a valid email"),
	ALFANUMERIC_150(UserRegex.ALFANUMERIC_150, "Must be a 150 positions");

	private final String regex;

	private final String message;

	ValidationUserEnum(String regex, String message) {
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
