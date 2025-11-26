package com.mx.dev.blog.spring_001_blog.utils.constants.regex;

public class CategoryRegex {

	public static final String ALFANUMERIC_150 = "^.{1,150}$";

	public static final String ALFANUMERIC_300 = "^.{1,300}$";

	public static final String COLOR_HEXA = "^#(?:[0-9a-fA-F]{3}){1,2}$";

	private CategoryRegex() {
		throw new IllegalStateException("Utility class");
	}

}
