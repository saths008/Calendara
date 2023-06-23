package com.farecompare.backend.controller;

import com.farecompare.backend.CalendarParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/v1")
public class FormController {

    @CrossOrigin(origins = "http://localhost:3000")
    @PostMapping("/submitForm")
    public void submitForm(@RequestBody Map<String, String> requestBody) {
        try {
            CalendarParser calendarParser= new CalendarParser(requestBody.get("fileData"));
//            System.out.println(calendarParser.getEvents(calendarParser.getCalendarData()));
//            System.out.println(requestBody.get("fileData"));
        }
        catch(Exception e){
            System.out.println("Error: " + e);
        }
        System.out.println("Received request body: " + requestBody);
        // Perform any necessary operations with the received data
    }

//    @CrossOrigin(origins = "http://localhost:3000")
//    @PostMapping("/submitForm")
//    public void submitForm(@RequestBody String requestBody) {
//        // Process the request body here
//        System.out.println("Received request body: " + requestBody);
//        // Perform any necessary operations with the received data
//    }

}