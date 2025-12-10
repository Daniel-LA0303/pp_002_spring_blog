package com.mx.dev.blog.spring_001_blog.notifiation.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.mx.dev.blog.spring_001_blog.notifiation.entities.NotificationEntity;
import com.mx.dev.blog.spring_001_blog.notifiation.utils.dto.NotificationDTO;

public interface NotificationRepository extends JpaRepository<NotificationEntity, Long> {

	// count total notifications by user
	@Query("""
			    SELECT COUNT(n)
			    FROM NotificationEntity n
			    WHERE n.userToId = :userId
			      AND n.read = false
			""")
	Long countUnreadNotifications(Long userId);

	@Query("""
			    SELECT new com.mx.dev.blog.spring_001_blog.notifiation.utils.dto.NotificationDTO(
			        n.notificationId,
			        n.content,
			        n.userToId,
			        n.userFromId,
			        n.notificationType,
			        n.delivered,
			        n.read,
			        n.targetId,
			        n.targetType,
			        n.createdAt,
			        u.userId,
			        ui.profilePicture,
			        u.username
			    )
			    FROM NotificationEntity n
			    LEFT JOIN UserEntity u ON u.userId = n.userFromId
			    LEFT JOIN UserInfoEntity ui ON ui.userId = u.userId
			    WHERE n.userToId = :userToId
			      AND n.delivered = false
			    ORDER BY n.createdAt DESC
			""")
	Page<NotificationDTO> findAllNotificationsWithUserInfo(@Param("userToId") Long userToId, Pageable pageable);

	Optional<NotificationEntity> findByNotificationId(Long notificationId);

	@Query("SELECT n from NotificationEntity n WHERE n.userToId = :userToId ORDER BY n.createdAt DESC")
	List<NotificationEntity> findByUserToId(@Param("userToId") Long userToId);

	@Query("SELECT n from NotificationEntity n WHERE n.userToId = :userToId AND n.delivered = false ORDER BY n.createdAt DESC")
	List<NotificationEntity> findByUserToIdAndDeliveredFalse(@Param("userToId") Long userToId);

	@Query("SELECT n FROM NotificationEntity n WHERE n.userFromId = :userFromId AND n.targetId = :targetId")
	Optional<NotificationEntity> findNotificationByUserFromIdAndTargetId(@Param("userFromId") Long userFromId,
			@Param("targetId") Long targetId);

	@Query("""
			    SELECT new com.mx.dev.blog.spring_001_blog.notifiation.utils.dto.NotificationDTO(
			        n.notificationId,
			        n.content,
			        n.userToId,
			        n.userFromId,
			        n.notificationType,
			        n.delivered,
			        n.read,
			        n.targetId,
			        n.targetType,
			        n.createdAt,
			        u.userId,
			        ui.profilePicture,
			        u.username
			    )
			    FROM NotificationEntity n
			    LEFT JOIN UserEntity u ON u.userId = n.userFromId
			    LEFT JOIN UserInfoEntity ui ON ui.userId = u.userId
			    WHERE n.userToId = :userToId
			      AND n.delivered = false
			    ORDER BY n.createdAt DESC
			""")
	List<NotificationDTO> findNotificationsWithUserInfo(@Param("userToId") Long userToId, Pageable pageable);

	@Query("""
			    SELECT n FROM NotificationEntity n
			    WHERE n.userToId = :userId
			      AND (n.read = false OR n.read IS NULL)
			    ORDER BY n.createdAt DESC
			""")
	List<NotificationEntity> findUnread(@Param("userId") Long userId);

}