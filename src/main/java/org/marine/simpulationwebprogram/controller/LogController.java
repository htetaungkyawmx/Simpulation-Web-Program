package org.marine.simpulationwebprogram.controller;

import org.marine.simpulationwebprogram.model.LogEntry;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RestController
public class LogController {
    private final List<LogEntry> logEntries = new ArrayList<>();

    // Log event function
    public void logEvent(String message, String type) {
        String timestamp = new SimpleDateFormat("MM/dd/yyyy, hh:mm:ss a").format(new Date());
        logEntries.add(new LogEntry(timestamp, message)); // Remove the 'type' argument
    }

    // Endpoint to get logs
    @GetMapping("/logs")
    public List<LogEntry> getLogs() {
        return logEntries;
    }

    // Example endpoint to log an event
    @PostMapping("/log")
    public String createLog(@RequestParam String message, @RequestParam String type) {
        logEvent(message, type); // Log the event
        return "Log entry created: " + message;
    }
}
