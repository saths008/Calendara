package com.farecompare.backend.service.serviceImpl;

import java.util.*;

import com.farecompare.backend.CalendarParser;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.farecompare.backend.service.FileUploadService;

@Service
public class FileUploadServiceImpl implements FileUploadService {

    @Override
    public ResponseEntity<Map<String, Object>> uploadToLocal(MultipartFile file, String startDate, String endDate) {
        Map<String, Object> payload = new HashMap<>();
        try {
            System.out.println("Original filename: " + file.getOriginalFilename());
            String fileName = file.getOriginalFilename();
            int fileNamelength = fileName.length();
            String fileExtension = file.getOriginalFilename().substring(fileNamelength - 4, fileNamelength);

            if (!fileExtension.equals(".ics")) {
                throw new IllegalArgumentException("File extension must be .ics");
            }

            byte[] data = file.getBytes();
            String calendarData = new String(data);
            calendarData = calendarData.replace("\n", "\\n");
            CalendarParser calendarParser = new CalendarParser(calendarData, startDate, endDate);

            Collection<String> collection = calendarParser.generateFormLabels().get(1);
            Set<String> formLabels = new HashSet<>(collection);
            payload.put("message", "Hello World");
            payload.put("eventDetails", formLabels);
            payload.put("fileData", calendarData);
            payload.put("startDate", startDate);
            payload.put("endDate", endDate);

            return ResponseEntity.ok(payload);
        } catch (IllegalArgumentException e) {
            payload.put("error", e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(payload);
        }

        catch (Exception e) {
            e.printStackTrace();
            payload.put("error", "Something went wrong!");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(payload);
        }
    }
}
