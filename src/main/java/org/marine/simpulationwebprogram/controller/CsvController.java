package org.marine.simpulationwebprogram.controller;

import org.marine.simpulationwebprogram.service.CsvLogService;
import org.marine.simpulationwebprogram.service.LogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/csv") // Grouping related endpoints under /csv
public class CsvController {

    private final CsvLogService csvLogService; // Renamed for clarity
    private final LogService logService;

    @Autowired
    public CsvController(CsvLogService csvLogService, LogService logService) {
        this.csvLogService = csvLogService; // Assign the correct service to the field
        this.logService = logService; // Assign the correct service to the field
    }

    @PostMapping("/upload-string")
    public ResponseEntity<List<String>> uploadCsvString(@RequestBody String csvData) {
        List<String> processedLines = new ArrayList<>();
        String[] lines = csvData.split("\n");

        for (String line : lines) {
            processedLines.add(line);
            logService.log("Processed line: " + line);
        }

        logService.log("CSV uploaded with " + lines.length + " lines");
        return ResponseEntity.ok(processedLines); // Return processed lines
    }

    @PostMapping("/upload-file")
    public ResponseEntity<String> uploadCsvFile(@RequestParam("file") MultipartFile file) {
        try {
            // Validate file type
            if (!file.getContentType().equals("text/csv")) {
                return ResponseEntity.badRequest().body("File is not a CSV.");
            }

            String filePath = saveFile(file); // Save the file
            logService.log("Uploaded file: " + filePath);
            return ResponseEntity.ok("File uploaded successfully: " + filePath);
        } catch (IOException e) {
            logService.log("Error uploading file: " + e.getMessage());
            return ResponseEntity.status(500).body("File upload failed.");
        }
    }

    private String saveFile(MultipartFile file) throws IOException {
        // Define a directory to save the uploaded files
        Path directory = Paths.get("uploads"); // Change to your desired path
        if (!Files.exists(directory)) {
            Files.createDirectories(directory); // Create the directory if it doesn't exist
        }

        // Save the file
        File savedFile = new File(directory.toFile(), file.getOriginalFilename());
        try (FileOutputStream fos = new FileOutputStream(savedFile)) {
            fos.write(file.getBytes());
        }

        return savedFile.getAbsolutePath(); // Return the file path
    }
}
