package org.marine.simpulationwebprogram.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ServerDashboardController {

    @GetMapping("/server-dashboard")
    public String showServerDashboard() {
        return "server-dashboard"; // This points to server-dashboard.html
    }
}
