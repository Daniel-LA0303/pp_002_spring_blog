package com.mx.dev.blog.spring_001_blog.utils.enums;

/**
 * method enum
 */
public enum MethodEnum {
	POST("POST"), PUT("PUT"), DELETE("DELETE"), GET("GET");

	private final String method;

	MethodEnum(String method) {
		this.method = method;
	}

}
