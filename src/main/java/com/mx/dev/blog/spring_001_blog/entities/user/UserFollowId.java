package com.mx.dev.blog.spring_001_blog.entities.user;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class UserFollowId implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Column(name = "follower_id")
	private Long followerId;

	@Column(name = "followed_id")
	private Long followedId;

	/**
	 * 
	 */
	public UserFollowId() {
	}

	/**
	 * @param followerId
	 * @param followedId
	 */
	public UserFollowId(Long followerId, Long followedId) {
		this.followerId = followerId;
		this.followedId = followedId;
	}

	/**
	 * return the value of the property followedId
	 *
	 * @return the followedId
	 */
	public Long getFollowedId() {
		return followedId;
	}

	/**
	 * return the value of the property followerId
	 *
	 * @return the followerId
	 */
	public Long getFollowerId() {
		return followerId;
	}

	/**
	 * set the value of the property followedId
	 *
	 * @param followedId the followedId to set
	 */
	public void setFollowedId(Long followedId) {
		this.followedId = followedId;
	}

	/**
	 * set the value of the property followerId
	 *
	 * @param followerId the followerId to set
	 */
	public void setFollowerId(Long followerId) {
		this.followerId = followerId;
	}

}
