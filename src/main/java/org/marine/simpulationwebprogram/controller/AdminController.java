package org.marine.simpulationwebprogram.controller;

import org.marine.simpulationwebprogram.model.LogEntry;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.stereotype.Controller;

import java.util.ArrayList;
import java.util.List;

@Controller
public class AdminController {

    // Simulated log entries for demonstration
    private final List<LogEntry> logEntries = new ArrayList<>(List.of(
            new LogEntry("2024-10-01 12:00:00", "User logged in successfully."),
            new LogEntry("2024-10-02 12:30:00", "Data retrieved from the database."),
            new LogEntry("2024-10-03 13:00:00", "Error: Unable to connect to the server."),
            new LogEntry("2024-10-04 14:00:00", "New user created: admin@example.com"),
            new LogEntry("2024-10-05 15:00:00", "Settings updated successfully.")
    ));

    // Dashboard endpoint
    @GetMapping("/admin/dashboard")
    public String dashboard() {
        return "dashboard"; // This corresponds to dashboard.html
    }

    // Logs endpoint
    @GetMapping("/admin/logs")
    @ResponseBody
    public List<LogEntry> getLogs() {
        return logEntries; // Return the list of log entries
    }
}
