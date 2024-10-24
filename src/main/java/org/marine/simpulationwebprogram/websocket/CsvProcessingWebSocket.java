package org.marine.simpulationwebprogram.websocket;

import jakarta.websocket.*;
import jakarta.websocket.server.ServerEndpoint;

import java.io.IOException;
import java.util.List;

@ServerEndpoint("/process-csv")
public class CsvProcessingWebSocket {

    private Session session;

    @OnOpen
    public void onOpen(Session session) {
        this.session = session;
    }

    @OnMessage
    public void onMessage(String message) {
        // Process the CSV file and send logs
        List<String> lines = readCsvFile(message); // Replace with your CSV reading logic
        for (int i = 0; i < lines.size(); i++) {
            String logMessage = "Line " + (i + 1) + ", count " + (i + 1) + " processed successfully";
            sendMessage(logMessage);
        }
    }

    private void sendMessage(String message) {
        try {
            session.getBasicRemote().sendText(message);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @OnClose
    public void onClose(Session session) {
        // Handle closing the session if necessary
    }

    @OnError
    public void onError(Session session, Throwable throwable) {
        // Handle errors
    }

    private List<String> readCsvFile(String filePath) {
        // Implement your CSV file reading logic here
        // Return a list of lines read from the CSV file
        return List.of("Sample line 1", "Sample line 2"); // Replace with actual reading logic
    }
}
