package com.farecompare.backend;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class CalendarParser {
    private final String pathToCalendar;
    private static final String EVENT_BEGIN = "BEGIN:VEVENT";
    private static final String EVENT_END = "END:VEVENT";

    public CalendarParser(String pathToCalendar) {
        this.pathToCalendar = pathToCalendar;
    }

    public String getPathToCalendar() {
        return pathToCalendar;
    }

    public String readFile() {
        try{
            File file = new File(this.pathToCalendar);
            Scanner sc = new Scanner(file);
            String fileContents = "";
            while(sc.hasNextLine()) {
                fileContents += sc.nextLine();
            }
            sc.close();
            // System.out.println(fileContents);
            return fileContents;
        }
        catch(FileNotFoundException e) {
            System.out.println(e);
            return null;
        }
    }
    /**
     * Finds the indices of the first EVENT_BEGIN and EVENT_END in a given String
     * @param fileContents the String to search
     * @return a List of Integers containing the indices of the first and last VEvent
     */
    public List<Integer> findRangeOfIndices(String fileContents) {
        List<Integer> indices = new ArrayList<>();
        int beginIndex = fileContents.indexOf(EVENT_BEGIN);
        int endIndex = fileContents.indexOf(EVENT_END) + EVENT_END.length();
        if(beginIndex != -1 && endIndex != -1) {
            indices.add(beginIndex);
            indices.add(endIndex);
            return indices;
        }
        else{
           return null;
        }
    }
/**
 * Returns a List of Strings containing all VEvents in the calendar
 * @return a List of Strings containing all VEvents in the calendar
 */
    public List<String> getEvents() {
        String fileContents = this.readFile();
        List<String> events = new ArrayList<>();
        while(fileContents.contains(EVENT_BEGIN) && fileContents.contains(EVENT_END)) {
            List<Integer> indices = this.findRangeOfIndices(fileContents);
            int beginIndex = indices.get(0);
            int endIndex = indices.get(1);
            String event = fileContents.substring(beginIndex, endIndex);
            events.add(event);
            fileContents = fileContents.substring(endIndex);
        }
        return events;
       
    }
}
