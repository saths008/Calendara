package com.farecompare.backend.controller;

import com.farecompare.backend.CalendarParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
@RestController
@RequestMapping("/api/v1")
public class FormController {

    @CrossOrigin(origins = "http://localhost:3000")
    @PostMapping("/submitForm")
    public HashMap<String, Object> submitForm(@RequestBody Map<String, String> requestBody) {
        HashMap<String, Object> response = new HashMap<>();
        try {
            CalendarParser calendarParser= new CalendarParser(requestBody.get("fileData"));
            requestBody.remove("fileData");
            response.put("fare", calculateFare(calendarParser, requestBody));
        }
        catch(Exception e){
            System.out.println("Error: " + e);
        }
        return response;
    }


    public int calculateFare(CalendarParser calendarParser, Map<String, String> requestBody){
        HashMap<String, Integer> freqOfUniqueEvents = calendarParser.getFreqOfUniqueEvents();
        Set<String> uniqueEvents = requestBody.keySet();
        int totalFare = 0;
        for (String event: uniqueEvents) {
            int frequency = freqOfUniqueEvents.get(event);
            int fare = Integer.parseInt(requestBody.get(event));
            totalFare += frequency * fare;
        }
        return totalFare;
    }
}