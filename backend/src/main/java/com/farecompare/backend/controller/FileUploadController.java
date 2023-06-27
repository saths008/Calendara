package com.farecompare.backend.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.farecompare.backend.service.FileUploadService;

@RestController
@RequestMapping("api/v1")
public class FileUploadController {

    @Autowired
    private FileUploadService fileUploadService;

    @CrossOrigin(origins = { "https://calendara.vercel.app/", "http://localhost:3000" })
    @PostMapping("/upload/local")
    public ResponseEntity<Map<String, Object>> uploadLocal(@RequestParam("file") MultipartFile file,
            @RequestParam("startDate") String startDate, @RequestParam("endDate") String endDate) {
        return fileUploadService.uploadToLocal(file, startDate, endDate);
    }
}
