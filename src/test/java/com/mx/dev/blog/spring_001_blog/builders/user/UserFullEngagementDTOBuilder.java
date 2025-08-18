package com.mx.dev.blog.spring_001_blog.builders.user;

import com.mx.dev.blog.spring_001_blog.utils.dtos.user.UserFullEngagementDTO;

public class UserFullEngagementDTOBuilder {

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
	 * Creates a builder with all dummy data for testing purposes.
	 *
	 * @return a pre-filled instance of UserFullEngagementDTOBuilder
	 */
	public static UserFullEngagementDTOBuilder withAllDummy() {
		return new UserFullEngagementDTOBuilder().setBlogCount(3L).setLikesCount(10L).setReadBlogsCount(10L)
				.setCommentCount(2L).setFollowingUserCount(4L).setFollowersUserCount(4L).setFollowingCategoryCount(3L);
	}

	/**
	 * Builds a UserUpdateInfoResponseDTO instance.
	 *
	 * @return a new instance of UserUpdateInfoResponseDTO
	 */
	public UserFullEngagementDTO build() {
		UserFullEngagementDTO dto = new UserFullEngagementDTO();
		dto.setBlogCount(this.blogCount);
		dto.setLikesCount(this.likesCount);
		dto.setReadBlogsCount(this.readBlogsCount);
		dto.setCommentCount(this.commentCount);
		dto.setFollowingUserCount(this.followingUserCount);
		dto.setFollowersUserCount(this.followersUserCount);
		dto.setFollowingCategoryCount(this.followingCategoryCount);
		return dto;
	}

	/**
	 * set the value of the proppertie blogCount
	 *
	 * @param blogCount the blogCount to set
	 */
	public UserFullEngagementDTOBuilder setBlogCount(Long blogCount) {
		this.blogCount = blogCount;
		return this;
	}

	/**
	 * set the value of the proppertie commentCount
	 *
	 * @param commentCount the commentCount to set
	 */
	public UserFullEngagementDTOBuilder setCommentCount(Long commentCount) {
		this.commentCount = commentCount;
		return this;
	}

	/**
	 * set the value of the proppertie followersUserCount
	 *
	 * @param followersUserCount the followersUserCount to set
	 */
	public UserFullEngagementDTOBuilder setFollowersUserCount(Long followersUserCount) {
		this.followersUserCount = followersUserCount;
		return this;
	}

	/**
	 * set the value of the proppertie followingCategoryCount
	 *
	 * @param followingCategoryCount the followingCategoryCount to set
	 */
	public UserFullEngagementDTOBuilder setFollowingCategoryCount(Long followingCategoryCount) {
		this.followingCategoryCount = followingCategoryCount;
		return this;
	}

	/**
	 * set the value of the proppertie followingUserCount
	 *
	 * @param followingUserCount the followingUserCount to set
	 */
	public UserFullEngagementDTOBuilder setFollowingUserCount(Long followingUserCount) {
		this.followingUserCount = followingUserCount;
		return this;
	}

	/**
	 * set the value of the proppertie likesCount
	 *
	 * @param likesCount the likesCount to set
	 */
	public UserFullEngagementDTOBuilder setLikesCount(Long likesCount) {
		this.likesCount = likesCount;
		return this;
	}

	/**
	 * set the value of the proppertie readBlogsCount
	 *
	 * @param readBlogsCount the readBlogsCount to set
	 */
	public UserFullEngagementDTOBuilder setReadBlogsCount(Long readBlogsCount) {
		this.readBlogsCount = readBlogsCount;
		return this;
	}

}
