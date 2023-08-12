package com.calendara.backend.services.serviceImpl;

import java.util.*;

import com.calendara.backend.services.FileUploadService;
import com.calendara.backend.services.CalendarParser;
import lombok.extern.java.Log;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
@Log
public class FileUploadServiceImpl implements FileUploadService {

    @Override
    public ResponseEntity<Map<String, Object>> uploadToLocal(MultipartFile file, String startDate, String endDate) {
        Map<String, Object> payload = new HashMap<>();
        try {
            log.info("Original filename: "  + file.getOriginalFilename());
            String fileName = file.getOriginalFilename();
            int fileNamelength = fileName.length();
            //check if file is of type .ics
            String fileExtension = file.getOriginalFilename().substring(fileNamelength - 4, fileNamelength);

            if (!fileExtension.equals(".ics")) {
                log.warning("File was not of type .ics");
                payload.put("error", "File must be of type .ics");
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(payload);
            }
            log.info("Start date: " + startDate);
            log.info("End date: " + endDate);
            byte[] data = file.getBytes();
            String calendarData = new String(data);
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
            log.warning("Error: " + e.getMessage());
            payload.put("error", e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(payload);
        }

        catch (Exception e) {
            log.warning("Error: " + e.getMessage());
            payload.put("error", "Something went wrong!");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(payload);
        }
    }
}
