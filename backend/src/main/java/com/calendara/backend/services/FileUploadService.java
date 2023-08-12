package com.calendara.backend.services;

import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

public interface FileUploadService {

    public ResponseEntity<Map<String, Object>> uploadToLocal(MultipartFile file, String startDate, String endDate);
}
