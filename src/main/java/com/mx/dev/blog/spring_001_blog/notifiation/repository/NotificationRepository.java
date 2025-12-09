package com.mx.dev.blog.spring_001_blog.notifiation.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.mx.dev.blog.spring_001_blog.notifiation.entities.NotificationEntity;
import com.mx.dev.blog.spring_001_blog.notifiation.utils.dto.NotificationDTO;

public interface NotificationRepository extends JpaRepository<NotificationEntity, Long> {

	Optional<NotificationEntity> findByNotificationId(Long notificationId);

	@Query("SELECT n from NotificationEntity n WHERE n.userToId = :userToId ORDER BY n.createdAt DESC")
	List<NotificationEntity> findByUserToId(@Param("userToId") Long userToId);

	@Query("SELECT n from NotificationEntity n WHERE n.userToId = :userToId AND n.delivered = false ORDER BY n.createdAt DESC")
	List<NotificationEntity> findByUserToIdAndDeliveredFalse(@Param("userToId") Long userToId);

	@Query("""
			    SELECT new com.mx.dev.blog.spring_001_blog.notifiation.utils.dto.NotificationDTO(
			        n.notificationId,
			        n.content,
			        n.userToId,
			        n.userFromId,
			        n.notificationType,
			        n.delivered,
			        n.read,
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
	List<NotificationDTO> findNotificationsWithUserInfo(@Param("userToId") Long userToId);

}