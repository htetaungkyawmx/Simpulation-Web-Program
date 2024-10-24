package org.marine.simpulationwebprogram.controller;

import org.marine.simpulationwebprogram.service.CsvLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@RestController
public class CsvController {

    private final CsvLogService logService;

    @Autowired
    public CsvController(CsvLogService logService) {
        this.logService = logService;
    }

    /*@PostMapping("/upload-csv")
    public List<String> uploadCsv(@RequestBody String csvData) {
        // Log CSV upload event
        logService.logCsvUpload("CSV uploaded with " + csvData.split("\n").length + " lines");

        // Simulate CSV processing
        String[] lines = csvData.split("\n");
        return Arrays.asList(lines);  // Return processed lines
    }*/

    @PostMapping("/upload-csv")
    public List<String> uploadCsv(@RequestBody String csvData) {
        // Validate and process CSV data
        List<String> processedLines = new ArrayList<>();
        String[] lines = csvData.split("\n");

        for (String line : lines) {
            // Here you could add logic to parse the CSV line
            processedLines.add(line); // Process and add to results
            logService.logCsvUpload("Processed line: " + line); // Log processing
        }

        // Log CSV upload event
        logService.logCsvUpload("CSV uploaded with " + lines.length + " lines");

        return processedLines;  // Return processed lines
    }

}
