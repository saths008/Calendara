package com.farecompare.backend.service;

import org.springframework.web.multipart.MultipartFile;

public interface FileUploadService {
    
    public void uploadToLocal(MultipartFile file);
}
