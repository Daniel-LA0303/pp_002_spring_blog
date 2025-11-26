package com.mx.dev.blog.spring_001_blog.utils.dtos.user;

public class UserFullEngagementDTO {

	/**
	 * blog count
	 */
	private Long blogCount;

	/**
	 * likes count
	 */
	private Long likesCount;

	/**
	 * read blogs count
	 */
	private Long readBlogsCount;

	/**
	 * comment count
	 */
	private Long commentCount;

	/**
	 * following users
	 */
	private Long followingUserCount;

	/**
	 * followers count
	 */
	private Long followersUserCount;

	/**
	 * following category
	 */
	private Long followingCategoryCount;

	/**
	 * 
	 */
	public UserFullEngagementDTO() {
	}

	/**
	 * @param blogCount
	 * @param likesCount
	 * @param readBlogsCount
	 * @param commentCount
	 * @param followingUserCount
	 * @param followersUserCount
	 * @param followingCategoryCount
	 */
	public UserFullEngagementDTO(Long blogCount, Long likesCount, Long readBlogsCount, Long commentCount,
			Long followingUserCount, Long followersUserCount, Long followingCategoryCount) {
		this.blogCount = blogCount;
		this.likesCount = likesCount;
		this.readBlogsCount = readBlogsCount;
		this.commentCount = commentCount;
		this.followingUserCount = followingUserCount;
		this.followersUserCount = followersUserCount;
		this.followingCategoryCount = followingCategoryCount;
	}

	/**
	 * return the value of the property blogCount
	 *
	 * @return the blogCount
	 */
	public Long getBlogCount() {
		return blogCount;
	}

	/**
	 * return the value of the property commentCount
	 *
	 * @return the commentCount
	 */
	public Long getCommentCount() {
		return commentCount;
	}

	/**
	 * return the value of the property followersUserCount
	 *
	 * @return the followersUserCount
	 */
	public Long getFollowersUserCount() {
		return followersUserCount;
	}

	/**
	 * return the value of the property followingCategoryCount
	 *
	 * @return the followingCategoryCount
	 */
	public Long getFollowingCategoryCount() {
		return followingCategoryCount;
	}

	/**
	 * return the value of the property followingUserCount
	 *
	 * @return the followingUserCount
	 */
	public Long getFollowingUserCount() {
		return followingUserCount;
	}

	/**
	 * return the value of the property likesCount
	 *
	 * @return the likesCount
	 */
	public Long getLikesCount() {
		return likesCount;
	}

	/**
	 * return the value of the property readBlogsCount
	 *
	 * @return the readBlogsCount
	 */
	public Long getReadBlogsCount() {
		return readBlogsCount;
	}

	/**
	 * set the value of the property blogCount
	 *
	 * @param blogCount the blogCount to set
	 */
	public void setBlogCount(Long blogCount) {
		this.blogCount = blogCount;
	}

	/**
	 * set the value of the property commentCount
	 *
	 * @param commentCount the commentCount to set
	 */
	public void setCommentCount(Long commentCount) {
		this.commentCount = commentCount;
	}

	/**
	 * set the value of the property followersUserCount
	 *
	 * @param followersUserCount the followersUserCount to set
	 */
	public void setFollowersUserCount(Long followersUserCount) {
		this.followersUserCount = followersUserCount;
	}

	/**
	 * set the value of the property followingCategoryCount
	 *
	 * @param followingCategoryCount the followingCategoryCount to set
	 */
	public void setFollowingCategoryCount(Long followingCategoryCount) {
		this.followingCategoryCount = followingCategoryCount;
	}

	/**
	 * set the value of the property followingUserCount
	 *
	 * @param followingUserCount the followingUserCount to set
	 */
	public void setFollowingUserCount(Long followingUserCount) {
		this.followingUserCount = followingUserCount;
	}

	/**
	 * set the value of the property likesCount
	 *
	 * @param likesCount the likesCount to set
	 */
	public void setLikesCount(Long likesCount) {
		this.likesCount = likesCount;
	}

	/**
	 * set the value of the property readBlogsCount
	 *
	 * @param readBlogsCount the readBlogsCount to set
	 */
	public void setReadBlogsCount(Long readBlogsCount) {
		this.readBlogsCount = readBlogsCount;
	}

}
