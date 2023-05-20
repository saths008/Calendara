package com.farecompare.backend;
import java.util.Map;
import java.util.HashMap;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@SpringBootApplication
public class BackendApplication {
	
	@GetMapping("/")
	public Map<String, String> home() {
		Map<String, String> payload = new HashMap<>();
		payload.put("message", "welcome to the home page");
		return payload;
	}
	public static void main(String[] args) {
		SpringApplication.run(BackendApplication.class, args);
	}


}
