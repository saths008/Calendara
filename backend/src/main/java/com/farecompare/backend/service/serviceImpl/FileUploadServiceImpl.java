package com.farecompare.backend.service.serviceImpl;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.farecompare.backend.service.FileUploadService;

@Service
public class FileUploadServiceImpl implements FileUploadService {
    
    private String uploadFolderPath = "/home/saath/Dev/fareCompare/backend/src/test/resources/";
    @Override
    public Map<String, String> uploadToLocal(MultipartFile file){
        
        try{
            byte[] data = file.getBytes();
            System.out.println("ln22");
            String calendarData= new String(data);
            System.out.println("calendarData: " + calendarData);
            String pathDir = uploadFolderPath + file.getOriginalFilename();
            System.out.println("pathDir: " + pathDir);
            Path path = Paths.get(pathDir);
            System.out.println("ln26");
            Files.write(path, data);
            System.out.println("ln28");
            Map<String, String> payload = new HashMap<>();
            payload.put("message", calendarData);
		    return payload;
        }
        catch(IOException e){
            e.printStackTrace();
            Map<String, String> payload = new HashMap<>();
            payload.put("message", "Something went wrong!");
            return payload;
        }
    }
}
