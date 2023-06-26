package com.farecompare.backend;

import org.apache.coyote.Request;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import javax.swing.*;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@WebMvcTest(BackendApplication.class)
class BackendApplicationIntTest {
    @Autowired
    private MockMvc mockMvc;
    @Test
    void healthCheck() throws Exception {
        RequestBuilder request = MockMvcRequestBuilders.get("/health_check");
         MvcResult result = mockMvc.perform(request).andReturn();
         assertEquals("Health Check Successful", result.getResponse().getContentAsString());
    }
}