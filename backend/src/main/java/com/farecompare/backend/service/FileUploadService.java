package com.farecompare.backend.service;

import java.util.Map;

import org.springframework.web.multipart.MultipartFile;

public interface FileUploadService {
    
    public Map<String, String>  uploadToLocal(MultipartFile file);
}
