package com.farecompare.backend.service.serviceImpl;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

import com.farecompare.backend.CalendarParser;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.farecompare.backend.service.FileUploadService;

@Service
public class FileUploadServiceImpl implements FileUploadService {

    // private String uploadFolderPath =
    // "/home/saath/Dev/fareCompare/backend/src/test/resources/";
    @Override
    public Map<String, Object> uploadToLocal(MultipartFile file) {
        Map<String, Object> payload = new HashMap<>();
        try {
            byte[] data = file.getBytes();
            // System.out.println("calendarData: " + calendarData );
            String calendarData = new String(data);
            // System.out.println("Successful Upload");
            // System.out.println("calendarData: " + calendarData );
            calendarData = calendarData.replace("\n", "\\n");
            CalendarParser calendarParser = new CalendarParser(calendarData);
            System.out.println(calendarParser.getCalendarData());
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
            return payload;
        } catch (Exception e) {
            e.printStackTrace();
            payload.put("message", "Something went wrong!");
            return payload;
        }
    }
}
