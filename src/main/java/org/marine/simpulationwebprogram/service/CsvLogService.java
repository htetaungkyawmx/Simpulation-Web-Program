package org.marine.simpulationwebprogram.service;

import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class CsvLogService {

    private final List<LogEntry> logs = new ArrayList<>();

    public void logCsvUpload(String message) {
        logs.add(new LogEntry(LocalDateTime.now(), message));
    }

    public List<LogEntry> getLogs() {
        return logs;
    }

    public static class LogEntry {
        private final LocalDateTime timestamp;
        private final String message;

        public LogEntry(LocalDateTime timestamp, String message) {
            this.timestamp = timestamp;
            this.message = message;
        }

        public LocalDateTime getTimestamp() {
            return timestamp;
        }

        public String getMessage() {
            return message;
        }
    }
}
