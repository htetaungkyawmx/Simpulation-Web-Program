package org.marine.simpulationwebprogram.service;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class LogService {

    private final List<String> logs = new ArrayList<>();

    public void log(String message) {
        logs.add(message);
    }

    public List<String> getLogs() {
        return new ArrayList<>(logs);
    }
}
