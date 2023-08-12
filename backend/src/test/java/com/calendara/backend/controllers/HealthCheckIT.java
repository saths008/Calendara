package com.calendara.backend.controllers;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@AutoConfigureMockMvc
public class HealthCheckIT {

    private final MockMvc mockMvc;
    @Autowired
    public HealthCheckIT(MockMvc mockMvc) {
        this.mockMvc = mockMvc;
    }
    @Test
    public void testHealthCheck() throws Exception {
        RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/health_check");
        MvcResult result = mockMvc.perform(requestBuilder)
                .andDo(MockMvcResultHandlers.print())
                .andReturn();
        assertEquals(200, result.getResponse().getStatus());
        assertEquals("Health Check Successful", result.getResponse().getContentAsString());
    }
}
