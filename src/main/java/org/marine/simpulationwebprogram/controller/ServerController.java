package org.marine.simpulationwebprogram.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ServerController {
    @GetMapping("/")
    public String server() {
        return "server";
    }
}
