package com.mx.dev.blog.spring_001_blog.chat.message.cntrollers;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.mx.dev.blog.spring_001_blog.chat.message.services.MessageService;
import com.mx.dev.blog.spring_001_blog.chat.message.utils.dto.MessageRequestDTO;
import com.mx.dev.blog.spring_001_blog.chat.message.utils.dto.MessageResponseDTO;
import com.mx.dev.blog.spring_001_blog.utils.exceptions.ServiceException;

@RestController
@RequestMapping("/api/v1/messages")
public class MessageController {

	private final MessageService messageService;

	public MessageController(MessageService messageService) {
		this.messageService = messageService;
	}

	@GetMapping("/chat/{chat-id}")
	public ResponseEntity<List<MessageResponseDTO>> getAllMessages(@PathVariable("chat-id") String chatId) {

		return ResponseEntity.ok(messageService.findChatMessages(chatId));
	}

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public void saveMessage(@RequestBody MessageRequestDTO message) {
		messageService.saveMessage(message);
	}

	@PatchMapping
	@ResponseStatus(HttpStatus.ACCEPTED)
	public void setMessageToSeen(@RequestParam("chat-id") String chatId, Authentication authentication)
			throws ServiceException {
		messageService.setMessagesToSeen(chatId, authentication);
	}

	@PostMapping(value = "/upload-media", consumes = "multipart/form-data")
	@ResponseStatus(HttpStatus.CREATED)
	public void uploadMedia(@RequestParam("chat-id") String chatId, @RequestPart("file") MultipartFile file,
			Authentication authentication) throws ServiceException {
		messageService.uploadMediaMessage(chatId, file, authentication);
	}
}
