package com.farecompare.backend.service.serviceImpl;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.farecompare.backend.CalendarParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.farecompare.backend.service.FileUploadService;

@Service
public class FileUploadServiceImpl implements FileUploadService {
    
//    private String uploadFolderPath = "/home/saath/Dev/fareCompare/backend/src/test/resources/";
    @Override
    public Map<String, String> uploadToLocal(MultipartFile file){
            Map<String, String> payload = new HashMap<>();
        try{
            byte[] data = file.getBytes();
            String calendarData= new String(data);
            System.out.println("Successful Upload");
            CalendarParser calendarParser = new CalendarParser(calendarData);
            System.out.println(calendarParser.sayHello());
            List<String> allEvents = calendarParser.getEvents(calendarData);
            System.out.println("allEvents: "+ allEvents);
            System.out.println("Number of Events: " +  calendarParser.getNumberOfEvents());
            payload.put("message", calendarData);
		    return payload;
        }
        catch(IOException e){
            e.printStackTrace();
            payload.put("message", "Something went wrong!");
            return payload;
        }
    }
}
