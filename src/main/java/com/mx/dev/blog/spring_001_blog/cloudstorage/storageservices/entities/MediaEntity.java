package com.mx.dev.blog.spring_001_blog.cloudstorage.storageservices.entities;

import java.time.LocalDateTime;
import java.util.Map;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;

import com.vladmihalcea.hibernate.type.json.JsonBinaryType;

@TypeDef(name = "jsonb", typeClass = JsonBinaryType.class)
@Entity
@Table(name = "media_tbl")
public class MediaEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long mediaId;

	// -- USER, BLOG, PRODUCT, etc.
	@Column(name = "owner_type")
	private String ownerType;

	@Column(name = "owner_id")
	private Long ownerId;

	/// -- cloudinary, aws_s3, local
	@Column(name = "provider")
	private String provider;

	@Column(name = "url")
	private String urlMedia;

	// -- avatar, blog_cover, blog_image...
	@Column(name = "category")
	private String category;

	// -- image/jpeg, video/mp4
	@Column(name = "type_file")
	private String typeFile;

	@Column(name = "size_file")
	private Double sizeFile;

	@Type(type = "jsonb")
	@Column(columnDefinition = "jsonb", name = "metadata")
	private Map<String, Object> metadata;

	@Column(name = "deleted")
	private Boolean deleted;

	@Column(name = "deleted_by")
	private Long deletedBy;

	@Column(name = "created_at")
	private LocalDateTime createdAt;

	/**
	 * 
	 */
	public MediaEntity() {
	}

	/**
	 * @param mediaId
	 * @param ownerType
	 * @param ownerId
	 * @param provider
	 * @param urlMedia
	 * @param category
	 * @param typeFile
	 * @param sizeFile
	 * @param metadata
	 * @param deleted
	 * @param deletedBy
	 * @param createdAt
	 */
	public MediaEntity(Long mediaId, String ownerType, Long ownerId, String provider, String urlMedia, String category,
			String typeFile, Double sizeFile, Map<String, Object> metadata, Boolean deleted, Long deletedBy,
			LocalDateTime createdAt) {
		this.mediaId = mediaId;
		this.ownerType = ownerType;
		this.ownerId = ownerId;
		this.provider = provider;
		this.urlMedia = urlMedia;
		this.category = category;
		this.typeFile = typeFile;
		this.sizeFile = sizeFile;
		this.metadata = metadata;
		this.deleted = deleted;
		this.deletedBy = deletedBy;
		this.createdAt = createdAt;
	}

	/**
	 * return value of the property category
	 *
	 * @return the category
	 */
	public String getCategory() {
		return category;
	}

	/**
	 * return value of the property createdAt
	 *
	 * @return the createdAt
	 */
	public LocalDateTime getCreatedAt() {
		return createdAt;
	}

	/**
	 * return value of the property deleted
	 *
	 * @return the deleted
	 */
	public Boolean getDeleted() {
		return deleted;
	}

	/**
	 * return value of the property deletedBy
	 *
	 * @return the deletedBy
	 */
	public Long getDeletedBy() {
		return deletedBy;
	}

	/**
	 * return value of the property mediaId
	 *
	 * @return the mediaId
	 */
	public Long getMediaId() {
		return mediaId;
	}

	/**
	 * return value of the property metadata
	 *
	 * @return the metadata
	 */
	public Map<String, Object> getMetadata() {
		return metadata;
	}

	/**
	 * return value of the property ownerId
	 *
	 * @return the ownerId
	 */
	public Long getOwnerId() {
		return ownerId;
	}

	/**
	 * return value of the property ownerType
	 *
	 * @return the ownerType
	 */
	public String getOwnerType() {
		return ownerType;
	}

	/**
	 * return value of the property provider
	 *
	 * @return the provider
	 */
	public String getProvider() {
		return provider;
	}

	/**
	 * return value of the property sizeFile
	 *
	 * @return the sizeFile
	 */
	public Double getSizeFile() {
		return sizeFile;
	}

	/**
	 * return value of the property typeFile
	 *
	 * @return the typeFile
	 */
	public String getTypeFile() {
		return typeFile;
	}

	/**
	 * return value of the property urlMedia
	 *
	 * @return the urlMedia
	 */
	public String getUrlMedia() {
		return urlMedia;
	}

	/**
	 * set value of the property category
	 *
	 * @param category the category to set
	 */
	public void setCategory(String category) {
		this.category = category;
	}

	/**
	 * set value of the property createdAt
	 *
	 * @param createdAt the createdAt to set
	 */
	public void setCreatedAt(LocalDateTime createdAt) {
		this.createdAt = createdAt;
	}

	/**
	 * set value of the property deleted
	 *
	 * @param deleted the deleted to set
	 */
	public void setDeleted(Boolean deleted) {
		this.deleted = deleted;
	}

	/**
	 * set value of the property deletedBy
	 *
	 * @param deletedBy the deletedBy to set
	 */
	public void setDeletedBy(Long deletedBy) {
		this.deletedBy = deletedBy;
	}

	/**
	 * set value of the property mediaId
	 *
	 * @param mediaId the mediaId to set
	 */
	public void setMediaId(Long mediaId) {
		this.mediaId = mediaId;
	}

	/**
	 * set value of the property metadata
	 *
	 * @param metadata the metadata to set
	 */
	public void setMetadata(Map<String, Object> metadata) {
		this.metadata = metadata;
	}

	/**
	 * set value of the property ownerId
	 *
	 * @param ownerId the ownerId to set
	 */
	public void setOwnerId(Long ownerId) {
		this.ownerId = ownerId;
	}

	/**
	 * set value of the property ownerType
	 *
	 * @param ownerType the ownerType to set
	 */
	public void setOwnerType(String ownerType) {
		this.ownerType = ownerType;
	}

	/**
	 * set value of the property provider
	 *
	 * @param provider the provider to set
	 */
	public void setProvider(String provider) {
		this.provider = provider;
	}

	/**
	 * set value of the property sizeFile
	 *
	 * @param sizeFile the sizeFile to set
	 */
	public void setSizeFile(Double sizeFile) {
		this.sizeFile = sizeFile;
	}

	/**
	 * set value of the property typeFile
	 *
	 * @param typeFile the typeFile to set
	 */
	public void setTypeFile(String typeFile) {
		this.typeFile = typeFile;
	}

	/**
	 * set value of the property urlMedia
	 *
	 * @param urlMedia the urlMedia to set
	 */
	public void setUrlMedia(String urlMedia) {
		this.urlMedia = urlMedia;
	}

}
