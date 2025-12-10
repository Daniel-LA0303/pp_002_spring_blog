package com.mx.dev.blog.spring_001_blog.chat.config.ws;

import static org.springframework.http.MediaType.APPLICATION_JSON;

import java.util.List;

import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.messaging.converter.DefaultContentTypeResolver;
import org.springframework.messaging.converter.MappingJackson2MessageConverter;
import org.springframework.messaging.converter.MessageConverter;
import org.springframework.messaging.handler.invocation.HandlerMethodArgumentResolver;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.security.messaging.context.AuthenticationPrincipalArgumentResolver;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

import com.fasterxml.jackson.databind.ObjectMapper;

@Configuration
@EnableWebSocketMessageBroker
@Order(Ordered.HIGHEST_PRECEDENCE)
public class WebSocketConfig implements WebSocketMessageBrokerConfigurer {

	@Override
	public void addArgumentResolvers(List<HandlerMethodArgumentResolver> argumentResolvers) {

		// add resolver for authentication principal to access current user in
		// controller methods
		argumentResolvers.add(new AuthenticationPrincipalArgumentResolver());
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
		registry.addEndpoint("/ws").setAllowedOrigins("http://localhost:5173").withSockJS();
	}

}
