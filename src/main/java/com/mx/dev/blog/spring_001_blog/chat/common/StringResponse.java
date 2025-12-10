package com.mx.dev.blog.spring_001_blog.chat.common;

public class StringResponse {

	private String response;

	public StringResponse() {
	}

	public StringResponse(String response) {
		this.response = response;
	}

	/**
	 * return value of the property response
	 *
	 * @return the response
	 */
	public String getResponse() {
		return response;
	}

	/**
	 * set value of the property response
	 *
	 * @param response the response to set
	 */
	public void setResponse(String response) {
		this.response = response;
	}

}