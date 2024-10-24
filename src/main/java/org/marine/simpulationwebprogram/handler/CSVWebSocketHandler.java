package org.marine.simpulationwebprogram.handler;

import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

public class CSVWebSocketHandler extends TextWebSocketHandler {

    @Override
    public void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
        // Broadcast the received CSV message to the WebSocket client
        String payload = message.getPayload();
        System.out.println("Received WebSocket message: " + payload);

        // Send the received message back to the client
        session.sendMessage(new TextMessage(payload));
    }
}
