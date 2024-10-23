package org.marine.simpulationwebprogram.handler;

import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import java.io.BufferedReader;
import java.io.StringReader;
import java.util.concurrent.TimeUnit;

public class CSVWebSocketHandler extends TextWebSocketHandler {

    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
        String csvContent = message.getPayload();
        BufferedReader reader = new BufferedReader(new StringReader(csvContent));
        String line;

        while ((line = reader.readLine()) != null) {
            // Send each line to client with a 5-second delay
            session.sendMessage(new TextMessage(line));
            TimeUnit.SECONDS.sleep(5); // 5-second delay
        }

        // After processing all lines, send end message
        session.sendMessage(new TextMessage("End of CSV"));
    }
}
