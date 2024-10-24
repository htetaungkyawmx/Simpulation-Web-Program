package org.marine.simpulationwebprogram.controller;

import org.marine.simpulationwebprogram.service.LogService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
public class CsvUploadController {

    private final LogService logService;

    public CsvUploadController(LogService logService) {
        this.logService = logService;
    }

    @PostMapping("/upload-csv-file")
    public ResponseEntity<String> uploadCsv(@RequestParam("file") MultipartFile file) {
        String filePath = saveFile(file); // Implement this method
        logService.log("Uploaded file: " + filePath);
        return ResponseEntity.ok("File uploaded successfully");
    }

    private String saveFile(MultipartFile file) {
        // Implement your file saving logic here
        return "/path/to/saved/file.csv"; // Replace with actual path
    }
}
