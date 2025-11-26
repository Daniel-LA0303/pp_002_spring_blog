package com.mx.dev.blog.spring_001_blog.utils.dtos.blog;

public class BlogEngagementDTO {

	/**
	 * blogId
	 */
	private Long blogId;

	/**
	 * number likes
	 */
	private Long likesNumber;

	/**
	 * number of comments
	 */
	private Long commentsNumber;

	/**
	 * number of saved
	 */
	private Long savedNumber;

	/**
	 * 
	 */
	public BlogEngagementDTO() {
	}

	/**
	 * @param likesNumber
	 * @param commentsNumber
	 * @param savedNumber
	 */
	public BlogEngagementDTO(Long blogId, Long likesNumber, Long commentsNumber, Long savedNumber) {
		this.blogId = blogId;
		this.likesNumber = likesNumber;
		this.commentsNumber = commentsNumber;
		this.savedNumber = savedNumber;
	}

	/**
	 * return the value of the property blogId
	 *
	 * @return the blogId
	 */
	public Long getBlogId() {
		return blogId;
	}

	/**
	 * return the value of the property commentsNumber
	 *
	 * @return the commentsNumber
	 */
	public Long getCommentsNumber() {
		return commentsNumber;
	}

	/**
	 * return the value of the property likesNumber
	 *
	 * @return the likesNumber
	 */
	public Long getLikesNumber() {
		return likesNumber;
	}

	/**
	 * return the value of the property savedNumber
	 *
	 * @return the savedNumber
	 */
	public Long getSavedNumber() {
		return savedNumber;
	}

	/**
	 * set the value of the property blogId
	 *
	 * @param blogId the blogId to set
	 */
	public void setBlogId(Long blogId) {
		this.blogId = blogId;
	}

	/**
	 * set the value of the property commentsNumber
	 *
	 * @param commentsNumber the commentsNumber to set
	 */
	public void setCommentsNumber(Long commentsNumber) {
		this.commentsNumber = commentsNumber;
	}

	/**
	 * set the value of the property likesNumber
	 *
	 * @param likesNumber the likesNumber to set
	 */
	public void setLikesNumber(Long likesNumber) {
		this.likesNumber = likesNumber;
	}

	/**
	 * set the value of the property savedNumber
	 *
	 * @param savedNumber the savedNumber to set
	 */
	public void setSavedNumber(Long savedNumber) {
		this.savedNumber = savedNumber;
	}

}
