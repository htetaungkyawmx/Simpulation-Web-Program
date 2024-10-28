package org.marine.simpulationwebprogram.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ServerDashboardController {

    @GetMapping("/server")
    public String home() {
        return "server-dashboard";
    }
}
