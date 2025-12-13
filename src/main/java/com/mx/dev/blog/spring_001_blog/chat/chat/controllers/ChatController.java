package com.mx.dev.blog.spring_001_blog.chat.chat.controllers;

import java.lang.management.ManagementFactory;
import java.lang.management.MemoryMXBean;
import java.lang.management.MemoryUsage;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.mx.dev.blog.spring_001_blog.chat.chat.services.ChatService;
import com.mx.dev.blog.spring_001_blog.chat.chat.utils.dto.ChatResponseDTO;
import com.mx.dev.blog.spring_001_blog.utils.exceptions.ServiceException;

@RestController
@RequestMapping("/api/v1/chats")
public class ChatController {

	private final ChatService chatService;

	// -----------------------
	// Constructor
	// -----------------------
	public ChatController(ChatService chatService) {
		this.chatService = chatService;
	}

	// create a chat
	@PostMapping
	public ResponseEntity<ChatResponseDTO> createChat(@RequestParam(name = "receiver-id") Long receiverId,
			Authentication authentication) throws ServiceException {

		Long senderId = chatService.getAuthenticatedUserId(authentication);
		ChatResponseDTO chat = chatService.createChat(senderId, receiverId);

		return ResponseEntity.ok(chat);
	}

	@GetMapping("/{chatId}")
	public ResponseEntity<ChatResponseDTO> getChatById(@PathVariable String chatId, Authentication authentication)
			throws ServiceException {

		Long currentUserId = chatService.getAuthenticatedUserId(authentication);
		ChatResponseDTO chat = chatService.getChatById(chatId, currentUserId);
		return ResponseEntity.ok(chat);
	}

	// get chats by user
	@GetMapping
	public ResponseEntity<List<ChatResponseDTO>> getChatsByReceiver(Authentication authentication)
			throws ServiceException {
		return ResponseEntity.ok(chatService.getChatsByReceiverId(authentication));
	}

	@GetMapping("/api/v1/system/ram")
	public ResponseEntity<Map<String, Object>> getRamUsage() {
		MemoryMXBean memoryBean = ManagementFactory.getMemoryMXBean();
		MemoryUsage heap = memoryBean.getHeapMemoryUsage();

		long usedBytes = heap.getUsed();
		long maxBytes = heap.getMax();
		double usedMB = usedBytes / (1024.0 * 1024.0);
		double maxMB = maxBytes / (1024.0 * 1024.0);
		double usedPercentage = (usedMB / maxMB) * 100;

		Map<String, Object> response = new HashMap<>();
		response.put("ramUsedMB", String.format("%.2f MB", usedMB));
		response.put("ramMaxMB", String.format("%.2f MB", maxMB));
		response.put("ramUsedPercentage", String.format("%.2f %%", usedPercentage));

		return ResponseEntity.ok(response);
	}
}
