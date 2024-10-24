package org.marine.simpulationwebprogram.handler;

import org.springframework.stereotype.Component;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

@Component
public class CSVWebSocketHandler extends TextWebSocketHandler {

    @Override
    public void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
        String receivedData = message.getPayload();

        // Log received CSV row on server
        System.out.println("Received CSV row: " + receivedData);

        // If the message is "End of CSV", notify the server
        if ("End of CSV".equals(receivedData)) {
            session.sendMessage(new TextMessage("All rows received successfully"));
        } else {
            // Broadcast the received row back to the client dashboard
            session.sendMessage(new TextMessage("Processed: " + receivedData));
        }
    }
}
