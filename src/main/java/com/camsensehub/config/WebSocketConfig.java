package com.camsensehub.config;

import com.camsensehub.handler.MessageHandler;
import com.camsensehub.repository.CameraRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.config.annotation.*;


@Configuration
@EnableWebSocket
public class WebSocketConfig implements WebSocketConfigurer {

    private final CameraRepository cameraRepository;

    public WebSocketConfig(CameraRepository cameraRepository) {
        this.cameraRepository = cameraRepository;
    }

    @Override
    public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
        registry.addHandler(messageHandler(), "/register")
                .setAllowedOrigins("*"); //.withSockJS

    }

    @Bean
    WebSocketHandler messageHandler() {
        return new MessageHandler(cameraRepository);
    }
}
