package com.mx.dev.blog.spring_001_blog.utils.constants.regex;

public class UserRegex {

	public static final String ALFANUMERIC_150 = "^.{1,150}$";

	public static final String ALFANUMERIC_200 = "^.{1,200}$";

	public static final String EMAIL = "^[_A-z0-9-]+(\\.[_A-z0-9-]+)*@[A-z0-9-]+(\\.[A-z0-9-]+)*(\\.[A-z]{2,4})$";

	private UserRegex() {
		throw new IllegalStateException("Utility class");
	}

}
