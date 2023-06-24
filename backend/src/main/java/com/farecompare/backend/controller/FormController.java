package com.farecompare.backend.controller;

import com.farecompare.backend.CalendarParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

@RestController
@RequestMapping("/api/v1")
public class FormController {

    @CrossOrigin(origins = "*")
    @PostMapping("/submitForm")
    public HashMap<String, Object> submitForm(@RequestBody Map<String, String> requestBody) {
        HashMap<String, Object> response = new HashMap<>();
        try {
            CalendarParser calendarParser = new CalendarParser(requestBody.get("fileData"));
            requestBody.remove("fileData");
            System.out.println();
            System.out.println("requestBody: " + requestBody);
            response.put("fare", calculateFare(calendarParser, requestBody));
        } catch (Exception e) {
            System.out.println("Error: " + e);
        }
        return response;
    }

    public BigDecimal calculateFare(CalendarParser calendarParser, Map<String, String> requestBody) {
        HashMap<String, Integer> freqOfFormLabels = calendarParser.getFrequencyOfFormLabels();
        Set<String> uniqueEvents = requestBody.keySet();
        BigDecimal totalFare = BigDecimal.ZERO;
        System.out.println("ln36");
        for (String event : uniqueEvents) {
            System.out.println("ln38");
            int frequency = freqOfFormLabels.get(event);
            System.out.println("event: " + event + " frequency: " + frequency);
            BigDecimal fare = new BigDecimal(requestBody.get(event));
            totalFare = totalFare.add(fare.multiply(BigDecimal.valueOf(frequency)));
        }
        return totalFare;
    }
}