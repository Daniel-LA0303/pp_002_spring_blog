package com.mx.dev.blog.spring_001_blog.chat.chat.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.mx.dev.blog.spring_001_blog.chat.chat.entities.ChatEntity;

public interface ChatRepository extends JpaRepository<ChatEntity, String> {

	// Search a chat between two users (bidirectional)
	@Query("""
			SELECT c FROM ChatEntity c
			WHERE (c.sender.userId = :senderId AND c.recipient.userId = :recipientId)
			   OR (c.sender.userId = :recipientId AND c.recipient.userId = :senderId)
			""")
	Optional<ChatEntity> findChatBetweenUsers(@Param("senderId") Long senderId, @Param("recipientId") Long recipientId);

	// Find all chats for a given user (chats where the user participates)
	@Query("""
			SELECT c FROM ChatEntity c
			WHERE c.sender.userId = :userId
			   OR c.recipient.userId = :userId ORDER BY createdDate DESC
			""")
	List<ChatEntity> findChatsByUserId(@Param("userId") Long userId);

}
