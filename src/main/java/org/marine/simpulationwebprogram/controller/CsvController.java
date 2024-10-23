package org.marine.simpulationwebprogram.controller;

import org.marine.simpulationwebprogram.service.CsvLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

@RestController
public class CsvController {

    private final CsvLogService logService;

    @Autowired
    public CsvController(CsvLogService logService) {
        this.logService = logService;
    }

    @PostMapping("/upload-csv")
    public List<String> uploadCsv(@RequestBody String csvData) {
        // Log CSV upload event
        logService.logCsvUpload("CSV uploaded with " + csvData.split("\n").length + " lines");

        // Simulate CSV processing
        String[] lines = csvData.split("\n");
        return Arrays.asList(lines);  // Return processed lines
    }
}
