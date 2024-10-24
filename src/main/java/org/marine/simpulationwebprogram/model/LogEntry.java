package org.marine.simpulationwebprogram.model;

public class LogEntry {
    private String timestamp;
    private String message;

    // Constructor
    public LogEntry(String timestamp, String message) {
        this.timestamp = timestamp;
        this.message = message;
    }

    // Getters and setters
    public String getTimestamp() {
        return timestamp;
    }

    public String getMessage() {
        return message;
    }
}
