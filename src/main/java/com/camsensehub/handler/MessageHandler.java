package com.camsensehub.handler;

import com.camsensehub.entity.Camera;
import com.camsensehub.repository.CameraRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.socket.*;

import java.util.Date;

public class MessageHandler implements WebSocketHandler {

    private static final Logger LOG = LoggerFactory.getLogger(MessageHandler.class);

    private final CameraRepository cameraRepository;

    public MessageHandler(CameraRepository cameraRepository) {
        this.cameraRepository = cameraRepository;
    }

    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        LOG.info("Connection established on session: {}", session.getId());
    }

    @Override
    public void handleMessage(WebSocketSession session, WebSocketMessage<?> message) throws Exception {
        try {
            LOG.info("IN - handleMessage - Date: {}", new Date());
            ObjectMapper objectMapper = new ObjectMapper();
            String messagePayload = (String) message.getPayload();
            LOG.info("received message: {}", messagePayload);
            session.sendMessage(new TextMessage("Started processing message: " + session + " - " + messagePayload));
            Camera camera = objectMapper.readValue(messagePayload, Camera.class);
            LOG.info("saving event captured from camera: {} start", camera.getName());
            cameraRepository.save(camera);
            LOG.info("saving event captured from camera: {} end", camera.getName());
            Thread.sleep(1000);
            session.sendMessage(new TextMessage("Completed processing message: " + messagePayload));
        } catch (Exception e) {
            LOG.info("exception in handleMessage: {}", e.toString());
        } finally {
            LOG.info("OUT - handleMessage - Date: {}", new Date());
        }
    }

    @Override
    public void handleTransportError(WebSocketSession session, Throwable exception) throws Exception {
        LOG.info("Exception occured: {} on session: {}", exception.getMessage(), session.getId());

    }

    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus closeStatus) throws Exception {
        LOG.info("Connection closed on session: {} with status: {}", session.getId(), closeStatus.getCode());

    }

    @Override
    public boolean supportsPartialMessages() {
        return false;
    }
}
