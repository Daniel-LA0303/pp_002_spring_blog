package com.mx.dev.blog.spring_001_blog.chat.common;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public class BaseAuditingEntity {

	@CreatedDate
	@Column(name = "created_date", updatable = false, nullable = false)
	private LocalDateTime createdDate;

	@LastModifiedDate
	@Column(name = "last_modified_date", insertable = false)
	private LocalDateTime lastModifiedDate;

	public BaseAuditingEntity() {
	}

	public BaseAuditingEntity(LocalDateTime createdDate, LocalDateTime lastModifiedDate) {
		this.createdDate = createdDate;
		this.lastModifiedDate = lastModifiedDate;
	}

	public LocalDateTime getCreatedDate() {
		return createdDate;
	}

	public LocalDateTime getLastModifiedDate() {
		return lastModifiedDate;
	}

	public void setCreatedDate(LocalDateTime createdDate) {
		this.createdDate = createdDate;
	}

	public void setLastModifiedDate(LocalDateTime lastModifiedDate) {
		this.lastModifiedDate = lastModifiedDate;
	}
}