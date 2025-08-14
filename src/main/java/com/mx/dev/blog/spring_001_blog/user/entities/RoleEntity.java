package com.mx.dev.blog.spring_001_blog.user.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "role_tbl")
public class RoleEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long roleId;

	@Column(name = "role_name")
	private String roleName;

	/**
	 * 
	 */
	public RoleEntity() {
	}

	/**
	 * @param roleId
	 * @param roleName
	 */
	public RoleEntity(Long roleId, String roleName) {
		this.roleId = roleId;
		this.roleName = roleName;
	}

	/**
	 * return the value of the property roleId
	 *
	 * @return the roleId
	 */
	public Long getRoleId() {
		return roleId;
	}

	/**
	 * return the value of the property roleName
	 *
	 * @return the roleName
	 */
	public String getRoleName() {
		return roleName;
	}

	/**
	 * set the value of the property roleId
	 *
	 * @param roleId the roleId to set
	 */
	public void setRoleId(Long roleId) {
		this.roleId = roleId;
	}

	/**
	 * set the value of the property roleName
	 *
	 * @param roleName the roleName to set
	 */
	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

}
