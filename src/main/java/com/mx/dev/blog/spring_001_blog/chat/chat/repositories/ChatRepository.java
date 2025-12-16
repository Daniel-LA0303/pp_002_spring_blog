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
			WHERE (c.sender.userId = :userId1 AND c.recipient.userId = :userId2)
			   OR (c.sender.userId = :userId2 AND c.recipient.userId = :userId1)
			""")
	Optional<ChatEntity> findChatBetweenUsers(@Param("userId1") Long userId1, @Param("userId2") Long userId2);

	// find chat by users order
	@Query("""
			SELECT c FROM ChatEntity c
			WHERE c.sender.userId = :minUserId AND c.recipient.userId = :maxUserId
			""")
	Optional<ChatEntity> findChatBySortedUsers(@Param("minUserId") Long minUserId, @Param("maxUserId") Long maxUserId);

	// Find all chats for a given user (chats where the user participates)
	@Query("""
			SELECT c FROM ChatEntity c
			WHERE c.sender.userId = :userId
			   OR c.recipient.userId = :userId ORDER BY createdDate DESC
			""")
	List<ChatEntity> findChatsByUserId(@Param("userId") Long userId);

}
