package com.mx.dev.blog.spring_001_blog.chat.config.ws;

import static org.springframework.http.MediaType.APPLICATION_JSON;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.converter.DefaultContentTypeResolver;
import org.springframework.messaging.converter.MappingJackson2MessageConverter;
import org.springframework.messaging.converter.MessageConverter;
import org.springframework.messaging.handler.invocation.HandlerMethodArgumentResolver;
import org.springframework.messaging.simp.config.ChannelRegistration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.messaging.simp.stomp.StompCommand;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.messaging.support.ChannelInterceptor;
import org.springframework.security.messaging.context.AuthenticationPrincipalArgumentResolver;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

import com.fasterxml.jackson.databind.ObjectMapper;

@Configuration
@EnableWebSocketMessageBroker
@Order(Ordered.HIGHEST_PRECEDENCE)
public class WebSocketConfig implements WebSocketMessageBrokerConfigurer {

	private static final AtomicInteger activeConnections = new AtomicInteger(0);

	@Override
	public void addArgumentResolvers(List<HandlerMethodArgumentResolver> argumentResolvers) {

		// add resolver for authentication principal to access current user in
		// controller methods
		argumentResolvers.add(new AuthenticationPrincipalArgumentResolver());
	}

	@Override
	public void configureClientInboundChannel(ChannelRegistration registration) {
		registration.interceptors(new ChannelInterceptor() {
			@Override
			public Message<?> preSend(Message<?> message, MessageChannel channel) {
				StompHeaderAccessor accessor = StompHeaderAccessor.wrap(message);

				if (StompCommand.CONNECT.equals(accessor.getCommand())) {
					int current = activeConnections.incrementAndGet();
					System.out.println("User connected. Active connections: " + current);
				}

				if (StompCommand.DISCONNECT.equals(accessor.getCommand())) {
					int current = activeConnections.decrementAndGet();
					System.out.println("User disconnected. Active connections: " + current);
				}

				return message;
			}
		});
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see org.springframework.web.socket.config.annotation.
	 * WebSocketMessageBrokerConfigurer#configureMessageBroker(org.springframework.
	 * messaging.simp.config.MessageBrokerRegistry)
	 */
	@Override
	public void configureMessageBroker(MessageBrokerRegistry registry) {
		// enable simple message broker for user-specific messaging
		registry.enableSimpleBroker("/user");

		// set prefix for application destinations
		registry.setApplicationDestinationPrefixes("/app");

		// set prefix for user destinations
		registry.setUserDestinationPrefix("/user");
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see org.springframework.web.socket.config.annotation.
	 * WebSocketMessageBrokerConfigurer#configureMessageConverters(java.util.List)
	 */
	@Override
	public boolean configureMessageConverters(List<MessageConverter> messageConverters) {
		// configure default content type resolver with json as default mime type
		DefaultContentTypeResolver resolver = new DefaultContentTypeResolver();
		resolver.setDefaultMimeType(APPLICATION_JSON);

		// create jackson message converter for json processing
		MappingJackson2MessageConverter converter = new MappingJackson2MessageConverter();
		converter.setObjectMapper(new ObjectMapper());
		converter.setContentTypeResolver(resolver);

		// add custom converter to the message converters list
		messageConverters.add(converter);

		// return false to indicate no default converters should be added
		return false;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see org.springframework.web.socket.config.annotation.
	 * WebSocketMessageBrokerConfigurer#registerStompEndpoints(org.springframework.
	 * web.socket.config.annotation.StompEndpointRegistry)
	 */
	@Override
	public void registerStompEndpoints(StompEndpointRegistry registry) {
		// register websocket endpoint with sockjs fallback options
		// and configure allowed origin for cors
		registry.addEndpoint("/ws").setAllowedOrigins("http://localhost:5173", "http://localhost:4200/").withSockJS();
	}

}
