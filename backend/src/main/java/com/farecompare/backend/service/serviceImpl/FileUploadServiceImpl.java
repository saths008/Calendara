package com.farecompare.backend.service.serviceImpl;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

import com.farecompare.backend.CalendarParser;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import com.farecompare.backend.service.FileUploadService;

@Service
public class FileUploadServiceImpl implements FileUploadService {

    // private String uploadFolderPath =
    // "/home/saath/Dev/fareCompare/backend/src/test/resources/";
    @Override
    public ResponseEntity<Map<String, Object>> uploadToLocal(MultipartFile file) {
        Map<String, Object> payload = new HashMap<>();
        try {
            System.out.println("Original filename: " + file.getOriginalFilename());
            String fileName = file.getOriginalFilename();
            int fileNamelength = fileName.length();
            String fileExtension = file.getOriginalFilename().substring(fileNamelength - 4, fileNamelength);

            if (!fileExtension.equals(".ics")) {
                throw new IllegalArgumentException("File extension must be .ics");
            }

            System.out.println("fileExtension: " + fileExtension);
            byte[] data = file.getBytes();
            // System.out.println("calendarData: " + calendarData );
            String calendarData = new String(data);
            // System.out.println("Successful Upload");
            // System.out.println("calendarData: " + calendarData );
            calendarData = calendarData.replace("\n", "\\n");
            CalendarParser calendarParser = new CalendarParser(calendarData);
            // System.out.println(calendarParser.getCalendarData());
            // System.out.println(calendarParser.sayHello());
            List<String> listOfCalendarData = calendarParser.getListOfCalendarData();

            Set<String> uniqueEventDetails = calendarParser
                    .getUniqueEventDetails(calendarParser.getDetailsForAllEvents());
            HashMap<String, String> lessAccurateGetDetailsForAllEvents = calendarParser
                    .lessAccurateGetDetailsForAllEvents();
            HashMap<String, String> allEventDetails = calendarParser.getDetailsForAllEvents();

            Collection<String> collection = calendarParser.generateFormLabels().get(1);
            Set<String> formLabels = new HashSet<>(collection);
            payload.put("message", "Hello World");
            payload.put("eventDetails", formLabels);
            payload.put("fileData", calendarData);

            // ObjectMapper objectMapper = new ObjectMapper();
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
