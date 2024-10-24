package org.marine.simpulationwebprogram.controller;

import org.marine.simpulationwebprogram.service.LogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
public class CsvServerController {

    @Autowired
    private LogService logService;

    @PostMapping("/upload-file")
    public ResponseEntity<String> uploadCsv(@RequestParam("file") MultipartFile file) {
        // Process the CSV file and return the status
        String result = processFile(file);
        return ResponseEntity.ok(result);
    }

    @MessageMapping("/process-csv")
    @SendTo("/topic/logs")
    public String handleCsvData(String csvRow) {
        // Process each row of CSV and log it
        logService.log("Processing row: " + csvRow);
        return "Processed: " + csvRow;
    }

    private String processFile(MultipartFile file) {
        // Logic to process the CSV file and convert to XML
        return "File processed successfully";
    }
}
