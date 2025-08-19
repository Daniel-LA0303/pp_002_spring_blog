package com.mx.dev.blog.spring_001_blog.utils.constants.regex;

public class BlogRegex {

	public static final String ALFANUMERIC_200 = "^.{1,200}$";

	public static final String ALFANUMERIC_300 = "^.{1,300}$";

	private BlogRegex() {
		throw new IllegalStateException("Utility class");
	}

}
