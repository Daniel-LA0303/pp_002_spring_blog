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
	 * @param content
	 * @param totalPages
	 * @param totalElements
	 * @param number
	 * @param size
	 */
	public PageDTO(List<T> content, int totalPages, long totalElements, int number, int size) {
		this.content = content;
		this.totalPages = totalPages;
		this.totalElements = totalElements;
		this.number = number;
		this.size = size;
	}

	/**
	 * return the value of the propertie content
	 *
	 * @return the content
	 */
	public List<T> getContent() {
		return content;
	}

	/**
	 * return the value of the propertie number
	 *
	 * @return the number
	 */
	public int getNumber() {
		return number;
	}

	/**
	 * return the value of the propertie size
	 *
	 * @return the size
	 */
	public int getSize() {
		return size;
	}

	/**
	 * return the value of the propertie totalElements
	 *
	 * @return the totalElements
	 */
	public long getTotalElements() {
		return totalElements;
	}

	/**
	 * return the value of the propertie totalPages
	 *
	 * @return the totalPages
	 */
	public int getTotalPages() {
		return totalPages;
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
