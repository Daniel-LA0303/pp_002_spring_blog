package com.mx.dev.blog.spring_001_blog.chat.message.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.mx.dev.blog.spring_001_blog.chat.message.entities.MessageEntity;
import com.mx.dev.blog.spring_001_blog.chat.message.utils.enums.MessageState;

public interface MessageRepository extends JpaRepository<MessageEntity, Long> {

	// Find messages by chat ID
	@Query("""
			SELECT m
			FROM MessageEntity m
			WHERE m.chat.id = :chatId
			ORDER BY m.createdDate
			""")
	List<MessageEntity> findMessagesByChatId(@Param("chatId") String chatId);

	// Set messages to SEEN in a chat
	@Modifying
	@Query("""
			UPDATE MessageEntity m
			SET m.state = :newState
			WHERE m.chat.id = :chatId
			""")
	void setMessagesToSeenByChatId(@Param("chatId") String chatId, @Param("newState") MessageState newState);
}
