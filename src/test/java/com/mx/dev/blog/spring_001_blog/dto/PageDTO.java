package com.mx.dev.blog.spring_001_blog.dto;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class PageDTO<T> {

	private List<T> content;

	private int totalPages;

	private long totalElements;

	private int number;

	private int size;

	/**
	 * 
	 */
	public PageDTO() {
	}

	/**
	 * set the value of the proppertie content
	 *
	 * @param content the content to set
	 */
	public void setContent(List<T> content) {
		this.content = content;
	}

	/**
	 * set the value of the proppertie number
	 *
	 * @param number the number to set
	 */
	public void setNumber(int number) {
		this.number = number;
	}

	/**
	 * set the value of the proppertie size
	 *
	 * @param size the size to set
	 */
	public void setSize(int size) {
		this.size = size;
	}

	/**
	 * set the value of the proppertie totalElements
	 *
	 * @param totalElements the totalElements to set
	 */
	public void setTotalElements(long totalElements) {
		this.totalElements = totalElements;
	}

	/**
	 * set the value of the proppertie totalPages
	 *
	 * @param totalPages the totalPages to set
	 */
	public void setTotalPages(int totalPages) {
		this.totalPages = totalPages;
	}

}
