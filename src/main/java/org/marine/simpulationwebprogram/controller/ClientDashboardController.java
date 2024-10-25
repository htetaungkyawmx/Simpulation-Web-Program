package org.marine.simpulationwebprogram.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ClientDashboardController {

    @GetMapping("/")
    public String home() {
        return "client-dashboard";
    }
}
