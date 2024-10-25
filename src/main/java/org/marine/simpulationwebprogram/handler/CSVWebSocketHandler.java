package org.marine.simpulationwebprogram.handler;

import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class CSVWebSocketHandler extends TextWebSocketHandler {
    private final SimpMessagingTemplate messagingTemplate;
    private final String filePath = "received_data.csv"; // File to save received CSV rows

    public CSVWebSocketHandler(SimpMessagingTemplate messagingTemplate) {
        this.messagingTemplate = messagingTemplate;
    }

    @Override
    public void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
        String receivedData = message.getPayload();

        // Log received CSV row on the server
        System.out.println("Received CSV row: " + receivedData);

        // Save the received data to a CSV file
        saveDataToFile(receivedData);

        // If the message is "End of CSV", notify the server
        if ("End of CSV".equals(receivedData)) {
            session.sendMessage(new TextMessage("All rows received successfully"));
        } else {
            // Broadcast the received row back to the client dashboard
            messagingTemplate.convertAndSend("/topic/updates", "Processed: " + receivedData);
            session.sendMessage(new TextMessage("Processed: " + receivedData));
        }
    }

    private void saveDataToFile(String data) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath, true))) {
            writer.write(data);
            writer.newLine(); // Add a new line after each entry
        } catch (IOException e) {
            System.err.println("Error writing to file: " + e.getMessage());
        }
    }
}
