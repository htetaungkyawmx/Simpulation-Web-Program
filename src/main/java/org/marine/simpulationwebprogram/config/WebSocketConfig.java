package org.marine.simpulationwebprogram.config;

import org.marine.simpulationwebprogram.handler.CSVWebSocketHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

@Configuration
@EnableWebSocketMessageBroker
public class WebSocketConfig implements WebSocketMessageBrokerConfigurer {

    @Override
    public void configureMessageBroker(MessageBrokerRegistry config) {
        // Configure the message broker to use a simple in-memory broker
        config.enableSimpleBroker("/topic", "/queue"); // Define message destinations
        config.setApplicationDestinationPrefixes("/app"); // Define application prefix
    }

    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        // Register STOMP endpoints for WebSocket communication
        registry.addEndpoint("/process-csv").withSockJS();
        registry.addEndpoint("/server-dashboard").withSockJS(); // Additional endpoint if needed
    }

    // No need to explicitly define the SimpMessagingTemplate bean here

    // Create the CSVWebSocketHandler bean using the messagingTemplate bean
    @Bean
    public CSVWebSocketHandler csvWebSocketHandler(SimpMessagingTemplate messagingTemplate) {
        return new CSVWebSocketHandler(messagingTemplate);
    }
}

/*
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

@Configuration
@EnableWebSocketMessageBroker
public class WebSocketConfig implements WebSocketMessageBrokerConfigurer {

    @Override
    public void configureMessageBroker(MessageBrokerRegistry config) {
        config.enableSimpleBroker("/topic");
        config.setApplicationDestinationPrefixes("/app");
    }

    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        registry.addEndpoint("/process-csv").withSockJS();
        registry.addEndpoint("/server-dashboard").withSockJS(); // WebSocket endpoint
    }
}
*/
