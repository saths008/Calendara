package com.farecompare.backend.controller;

import com.farecompare.backend.CalendarParser;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

@RestController
@RequestMapping("/api/v1")
public class FormController {

    @CrossOrigin(origins = { "https://fare-compare.vercel.app", "http://localhost:3000" })
    @PostMapping("/submitForm")
    public ResponseEntity<Map<String, Object>> submitForm(@RequestBody Map<String, String> requestBody) {
        HashMap<String, Object> response = new HashMap<>();
        try {
            String startDate = requestBody.get("startDate");
            String endDate = requestBody.get("endDate");
            if (startDate == null || endDate == null) {
                response.put("error", "startDate and endDate are required");
                return ResponseEntity.badRequest().body(response);
            }
            CalendarParser calendarParser = new CalendarParser(requestBody.get("fileData"), startDate, endDate);
            requestBody.remove("fileData");
            requestBody.remove("startDate");
            requestBody.remove("endDate");
            System.out.println();
            System.out.println("requestBody: " + requestBody);
            System.out.println("Problem is calculateFare()");
            response.put("fare", calculateFare(calendarParser, requestBody));
        } catch (Exception e) {
            System.out.println("Error: " + e);
            response.put("error", e.getMessage());
            return ResponseEntity.badRequest().body(response);
        }
        return ResponseEntity.ok(response);
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