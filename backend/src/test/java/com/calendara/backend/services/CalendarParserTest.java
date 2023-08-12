package com.calendara.backend.services;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

import static org.assertj.core.api.Assertions.assertThat;
@ExtendWith(MockitoExtension.class)
public class CalendarParserTest {

    private CalendarParser underTest;

    public CalendarParserTest(){
        File file = new File("src/test/resources/test.ics");
        StringBuilder content = new StringBuilder();

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                content.append(line).append("\n");
            }
        }
        catch(Exception e) {
            System.out.println("Error: " + e);
        }
        String calendarData = content.toString();
        underTest = new CalendarParser(calendarData, "2022-12-16T00:00:00.000Z","2023-01-20T00:00:00.000Z");
    }

    @Test
    public void testCalendarParserHello() {
        assertThat(underTest.sayHello()).isEqualTo("Hello, this is the Calendar Parser Class");
    }

    @Test
    public void testNumberOfEvents(){
        assertThat(underTest.getNumberOfEvents()).isEqualTo(2);
    }
}
