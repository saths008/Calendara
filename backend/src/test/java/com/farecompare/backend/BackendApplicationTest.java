package com.farecompare.backend;

import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.*;

class BackendApplicationTest {

    @Test
    void healthCheck() {
        BackendApplication backendApplication = new BackendApplication();
        ResponseEntity<String> response = backendApplication.healthCheck();
        assertEquals("Health Check Successful", response.getBody());
        assertEquals(HttpStatusCode.valueOf(200), response.getStatusCode());
    }
}