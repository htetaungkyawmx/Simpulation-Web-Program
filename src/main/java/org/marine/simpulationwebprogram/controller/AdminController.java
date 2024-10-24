package org.marine.simpulationwebprogram.controller;

import org.marine.simpulationwebprogram.service.CsvLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AdminController {

    private final CsvLogService csvLogService;

    @Autowired
    public AdminController(CsvLogService csvLogService) {
        this.csvLogService = csvLogService;
    }

    // Render the dashboard with logs
    @GetMapping("/admin/dashboard")
    public String showDashboard(Model model) {
        // Add logs to the model
        model.addAttribute("logs", csvLogService.getLogs());
        return "admin-dashboard"; // Renders admin-dashboard.html
    }
}
