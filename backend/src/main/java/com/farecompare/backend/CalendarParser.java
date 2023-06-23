package com.farecompare.backend;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.FileNotFoundException;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/*@Component
@ComponentScan*/
public class CalendarParser {
    private String calendarData;
    private List<String> listOfCalendarData;
    private List<String> cleanedListOfCalendarData;
    private static final String EVENT_BEGIN = "BEGIN:VEVENT";
    private static final String EVENT_END = "END:VEVENT";

    public CalendarParser(String calendarData) {
        this.calendarData = calendarData;
        listOfCalendarData = getEvents(calendarData);
        cleanedListOfCalendarData = new ArrayList<>();
        for (String event : listOfCalendarData) {
            cleanedListOfCalendarData.add(cleanEvent(event));
        }
    }

    /**
     * Finds the indices of the first EVENT_BEGIN and EVENT_END in a given String
     * 
     * @param fileContents the String to search
     * @return a List of Integers containing the indices of the first and last
     *         VEvent
     */
    public List<Integer> findRangeOfIndices(String fileContents) {
        List<Integer> indices = new ArrayList<>();
        int beginIndex = fileContents.indexOf(EVENT_BEGIN);
        int endIndex = fileContents.indexOf(EVENT_END) + EVENT_END.length();
        if (beginIndex != -1 && endIndex != -1) {
            indices.add(beginIndex);
            indices.add(endIndex);
            return indices;
        } else {
            return null;
        }
    }

    public String getCalendarData() {
        return calendarData;
    }

    /**
     * Returns a List of Strings containing all VEvents in the calendar
     * 
     * @return a List of Strings containing all VEvents in the calendar
     */
    public List<String> getEvents(String calendarData) {
        String fileContents = calendarData;
        List<String> events = new ArrayList<>();
        while (fileContents.contains(EVENT_BEGIN) && fileContents.contains(EVENT_END)) {
            List<Integer> indices = this.findRangeOfIndices(fileContents);
            int beginIndex = indices.get(0);
            int endIndex = indices.get(1);
            String event = fileContents.substring(beginIndex, endIndex);
            events.add(event);
            fileContents = fileContents.substring(endIndex);
        }
        return events;
    }

    public List<String> getListOfCalendarData() {
        return this.listOfCalendarData;
    }

    public int getNumberOfEvents() {
        return listOfCalendarData.size();
    }

    public String sayHello() {
        return "Hello, this is the Calendar Parser Class";
    }

    /**
     *
     * @param calendarData input calendar data string
     * @return string containing the location of the event
     */
    public String getLocation(String calendarData) {
        // System.out.println("In getLocation of CalendarParser, input param: " +
        // calendarData);
        String regexUppercaseLocation = "LOCATION:[A-Za-z0-9\\s]+";
        Pattern patternUpperCase = Pattern.compile(regexUppercaseLocation);
        Matcher matcherUpperCase = patternUpperCase.matcher(calendarData);

        String regexLowercaseLocation = "Location: [A-Za-z0-9\\s]+";
        Pattern patternLowerCase = Pattern.compile(regexLowercaseLocation);
        Matcher matcherLowerCase = patternLowerCase.matcher(calendarData);

        if (matcherUpperCase.find()) {
            String match = matcherUpperCase.group();
            return match;
        } else if (matcherLowerCase.find()) {
            String match = matcherLowerCase.group();
            return match;
        } else {
            return "No Location found";
        }
    }

    /**
     *
     * @param calendarData some calendar Data
     * @return the DTSTART tag of the calendarData followed by an ISO timestamp
     */
    public String getDStart(String calendarData) {
        String regex = "DTSTART:\\d{8}T\\d{6}Z";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(calendarData);

        if (matcher.find()) {
            String match = matcher.group();
            return match;
        } else {
            return "No DStart";
        }
    }

    /**
     *
     * @param calendarData some calendar Data
     * @return the DTEND tag of the calendarData followed by an ISO timestamp
     */
    public String getDTEnd(String calendarData) {
        String regex = "DTEND:\\d{8}T\\d{6}Z";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(calendarData);

        if (matcher.find()) {
            String match = matcher.group();
            return match;
        } else {
            return "No DTEND";
        }
    }

    /**
     * @return string just containing the time, DTSTART should be spliced off the
     *         original string
     */
    public String spliceDTSTART(String input) {
        // replace with DTSTART.length()
        return input.substring(8, input.length());
    }

    /**
     * @return string just containing the time, DTEND should be spliced off the
     *         original string
     */
    public String spliceDTEND(String input) {
        return input.substring(6, input.length());
    }

    /**
     *
     * @param zuluTime a timestamp formatted in the ISO standard eg.
     *                 20230120T090000Z
     * @throws IllegalArgumentException if timestamp is not in ISO standard
     */
    public String getDayOfMonthFromISO(String zuluTime) throws IllegalArgumentException {

        if (zuluTime.length() == 16 && zuluTime.contains("T") && zuluTime.contains("Z")) {
            // String date = zuluTime.substring(0, 8);
            // String dateFormat = zuluTime.substring(0,3) + "-" + zuluTime.substring(4,5) +
            // "-" + zuluTime.substring(6,7);
            final int beginningOfISO = 0;
            final int startOfMonth = 0;
            final int startOfDayOfMonth = 0;
            final int endOfDayOfMonth = 0;
            int year = Integer.parseInt(zuluTime.substring(0, 4)); // 2023
            int month = Integer.parseInt(zuluTime.substring(4, 6));// 01
            int dayOfMonth = Integer.parseInt(zuluTime.substring(6, 8));// 20
            LocalDate localDate = LocalDate.of(year, month, dayOfMonth);
            DayOfWeek dayOfWeek = localDate.getDayOfWeek();
            return dayOfWeek.toString();
        } else {
            throw new IllegalArgumentException("zuluTime not in ISO format");
        }
    }

    public String getDate(String zuluTime) throws IllegalArgumentException {

        if (zuluTime.length() == 16 && zuluTime.contains("T") && zuluTime.contains("Z")) {
            int year = Integer.parseInt(zuluTime.substring(0, 4)); // 2023
            int month = Integer.parseInt(zuluTime.substring(4, 6));// 01
            int dayOfMonth = Integer.parseInt(zuluTime.substring(6, 8));// 20
            LocalDate localDate = LocalDate.of(year, month, dayOfMonth);
            return localDate.toString();
        } else {
            throw new IllegalArgumentException("zuluTime not in ISO format");
        }
    }

    /**
     *
     * @param zuluTime a timestamp formatted in the ISO standard eg.
     *                 20230120T090000Z
     * @return the actual time from the timestamp eg. 09:00
     */
    public String getTimeFromISO(String zuluTime) throws IllegalArgumentException {

        if (zuluTime.length() == 16 && zuluTime.contains("T") && zuluTime.contains("Z")) {
            String hourTime = zuluTime.substring(9, 11);
            String minuteTime = zuluTime.substring(12, 14);
            String time = hourTime + ":" + minuteTime;
            // System.out.println("The time of the event: " + time);
            return time;
        } else {
            throw new IllegalArgumentException("zuluTime not in ISO format");
        }
    }

    public String summariseEvent(String event) {
        String dStartTag = getDStart(event);
        String dTSTARTtimeStamp = spliceDTSTART(dStartTag);

        String dTendTag = getDTEnd(event);
        String dTENDtimeStamp = spliceDTEND(dTendTag);

        return

        "Day: " + getDayOfMonthFromISO(dTSTARTtimeStamp) + ", Start Time: " + getTimeFromISO(dTSTARTtimeStamp) + " & "
                + ", End Time: " + getTimeFromISO(dTENDtimeStamp) + ", Location: " + getLocation(event);
    }

    /**
     * Print out the location, time and day of the week for every event.
     * 
     * @return a HashMap where the key is an event identifier and the value
     *         represents the details of the event
     */
    public HashMap<String, String> getDetailsForAllEvents() {
        int counter = 0;
        HashMap<String, String> detailsAndEventsMap = new HashMap<>();
        for (String event : listOfCalendarData) {
            counter++;
            detailsAndEventsMap.put("Event " + counter, summariseEvent(event));
        }
        return detailsAndEventsMap;
    }

    /**
     * Print out the location, time and day of the week for every event.
     * 
     * @return a HashMap where the key is an event identifier and the value
     *         represents the details of the event
     */
    public HashMap<String, String> lessAccurateGetDetailsForAllEvents() {
        int counter = 0;
        HashMap<String, String> detailsAndEventsMap = new HashMap<>();
        for (String event : listOfCalendarData) {
            counter++;
            String dStartTag = getDStart(event);
            String timeStamp = spliceDTSTART(dStartTag);
            detailsAndEventsMap.put("Event " + counter, getDayOfMonthFromISO(timeStamp));
            // System.out.println("Event " + counter + ":" + getDayOfMonthFromISO(timeStamp)
            // + " " + getTimeFromISO(timeStamp) + getLocation(event));
            // System.out.println();

        }
        return detailsAndEventsMap;
    }

    /**
     * @param eventDetails HashMap where the key is an event identifier and the
     *                     value represents the details of the event
     * @return a Set of event details
     */
    public Set<String> getUniqueEventDetails(HashMap<String, String> eventDetails) {
        Set<String> uniqueEventDetails = new HashSet<>();
        for (String key : eventDetails.keySet()) {
            uniqueEventDetails.add(eventDetails.get(key));
        }
        return uniqueEventDetails;
    }

    /**
     * @param allEventDetails    a Collection of details of all events in the
     *                           calendar
     * @param uniqueEventDetails a Set of unique event details in the calendar
     * @return HashMap where the key is the event detail and the value is the
     *         frequency of that key in the calendar.
     */
    public HashMap<String, Integer> getFreqOfEventDetails(Set<String> uniqueEventDetails,
            Collection<String> allEventDetails) {
        HashMap<String, Integer> frequencyOfEventDetails = new HashMap<>();

        for (String uniqueEventDetail : uniqueEventDetails) {
            frequencyOfEventDetails.put(uniqueEventDetail, 0);
        }

        for (String eventDetail : allEventDetails) {
            for (String uniqueEventDetail : frequencyOfEventDetails.keySet()) {
                if (eventDetail.equals(uniqueEventDetail)) {
                    frequencyOfEventDetails.put(eventDetail, frequencyOfEventDetails.get(eventDetail) + 1);
                }
            }
        }
        System.out.println(frequencyOfEventDetails.keySet());
        System.out.println(frequencyOfEventDetails);
        return frequencyOfEventDetails;
    }

    /**
     *
     * @return A hashmap containing the frequency of each unique event in the
     *         calendar
     */
    public HashMap<String, Integer> getFreqOfUniqueEvents() {
        HashMap<String, String> allEventDetails = getDetailsForAllEvents();
        HashMap<String, Integer> freqOfEventDetails = getFreqOfEventDetails(getUniqueEventDetails(allEventDetails),
                allEventDetails.values());
        return freqOfEventDetails;
    }

    /**
     *
     * @return A hashmap where the key is a Day and the value is the number of
     *         events on a specified day
     */
    public HashMap<String, Integer> getTotalFreqOfEventsOnDaysOfTheWeek() {
        HashMap<String, String> lessAccurateGetDetailsForAllEvents = lessAccurateGetDetailsForAllEvents();
        HashMap<String, Integer> uniqueEventDetails = getFreqOfEventDetails(
                getUniqueEventDetails(lessAccurateGetDetailsForAllEvents), lessAccurateGetDetailsForAllEvents.values());
        return uniqueEventDetails;
    }

    /**
     *
     * @return a set containing all dates that events occur on
     */
    public Set<String> getAllDates() {
        Set<String> allDates = new HashSet<>();
        for (String event : this.listOfCalendarData) {
            String dStartTag = getDStart(event);
            String timeStamp = spliceDTSTART(dStartTag);
            allDates.add(getDate(timeStamp));
        }
        return allDates;
    }

    /**
     *
     * @return a hashmap where the key is a date and value is an arrayList
     *         containing the indices of the events with respect to the
     *         listOfCalendarData
     */
    public HashMap<String, ArrayList<Integer>> mapEachEventToADate() {
        Set<String> allDates = getAllDates();
        HashMap<String, ArrayList<Integer>> dateToEventMap = new HashMap<>();
        for (String date : allDates) {
            ArrayList<Integer> indicesOfEvents = new ArrayList<>();
            for (int i = 0; i < this.listOfCalendarData.size(); i++) {
                String event = this.listOfCalendarData.get(i);
                String dStartTag = getDStart(event);
                String timeStamp = spliceDTSTART(dStartTag);
                if (getDate(timeStamp).equals(date)) {
                    indicesOfEvents.add(i);
                }
            }
            dateToEventMap.put(date, indicesOfEvents);
        }
        return dateToEventMap;
    }

    /**
     * @param date a date in the format
     * @return List containing the events that occur on a specified date
     */
    public List<String> getFirstAndLastEventOnEachDate(String date) {
        HashMap<String, ArrayList<Integer>> mapFromADateToAllEvents = mapEachEventToADate();
        List<String> events = new ArrayList<>();

        if (mapFromADateToAllEvents.containsKey(date)) {
            ArrayList<Integer> eventIndices = mapFromADateToAllEvents.get(date);

            if (eventIndices.size() > 1) {
                events.add(summariseEvent(this.listOfCalendarData.get(eventIndices.get(0))));
                events.add(summariseEvent(this.listOfCalendarData.get(eventIndices.size() - 1)));
            } else {
                events.add(summariseEvent(this.listOfCalendarData.get(eventIndices.get(0))));
            }
        }
        return events;
    }

    /**
     * Go through each date and find the first and last event on each date.
     * If it is the first event, then add HOME -> {event}
     * If it is the last event, then add {event} -> HOME
     * 
     * @return a list where the first element is a list of all possible form labels
     *         and the second is the unique form labels
     */
    public List<Collection<String>> generateFormLabels() {
        Set<String> allDates = getAllDates();
        List<String> listOfFormLabels = new ArrayList<>();
        for (String date : allDates) {
            List<String> firstAndLastEventOnEachDate = getFirstAndLastEventOnEachDate(date);
            if (firstAndLastEventOnEachDate.size() == 1) {
                listOfFormLabels.add("HOME -> " + firstAndLastEventOnEachDate.get(0));
                listOfFormLabels.add(firstAndLastEventOnEachDate.get(0) + " -> HOME");
            } else {
                listOfFormLabels.add("HOME -> " + firstAndLastEventOnEachDate.get(0));
                listOfFormLabels.add(firstAndLastEventOnEachDate.get(0) + " -> " + firstAndLastEventOnEachDate.get(1));
                listOfFormLabels.add(firstAndLastEventOnEachDate.get(1) + " -> HOME");
            }
        }
        return Arrays.asList(listOfFormLabels, new HashSet<>(listOfFormLabels));
    }

    public HashMap<String, Integer> getFrequencyOfFormLabels() {
        List<Collection<String>> collection = this.generateFormLabels();

        List<String> formLabels = new ArrayList<>(collection.get(0));
        // Set<String> uniqueFormLabels = new HashSet<>(collection.get(1));
        System.out.println("ln398: " + collection);

        HashMap<String, Integer> freqOfUniqueLabels = new HashMap<>();

        for (String label : formLabels) {
            if (!freqOfUniqueLabels.containsKey(label)) {
                freqOfUniqueLabels.put(label, 1);
            } else {
                freqOfUniqueLabels.put(label, freqOfUniqueLabels.get(label) + 1);
            }
        }
        return freqOfUniqueLabels;
    }

    /**
     * Clean the event by removing any event specific details such as date, UID
     * 
     * @param event
     * @return
     */
    public String cleanEvent(String event) {
        String DTEND_REGEX = "DTEND:[^\\r\\n]+";
        String DTSTAMP_REGEX = "DTSTAMP:[^\\r\\n]+";
        String DTSTART_REGEX = "DTSTART:[^\\r\\n]+";
        String LASTMODIFIED_REGEX = "LAST-MODIFIED:[^\\r\\n]+";
        String UID_REGEX = "UID:[^\\r\\n]+";
        String DATE_REGEX = "Date:[^\\r\\n]+";
        String strippedString = event.replaceAll(DTEND_REGEX, "");
        strippedString = strippedString.replaceAll(DTSTAMP_REGEX, "");
        strippedString = strippedString.replaceAll(DTSTART_REGEX, "");
        strippedString = strippedString.replaceAll(LASTMODIFIED_REGEX, "");
        strippedString = strippedString.replaceAll(UID_REGEX, "");
        strippedString = strippedString.replaceAll(DATE_REGEX, "");
        System.out.println(strippedString);
        return strippedString;
    }
}
