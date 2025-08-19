package com.mx.dev.blog.spring_001_blog.utils.constants.regex;

public class CommentRegex {

	public static final String ALFANUMERIC_300 = "^.{1,300}$";

	private CommentRegex() {
		throw new IllegalStateException("Utility class");
	}

}
