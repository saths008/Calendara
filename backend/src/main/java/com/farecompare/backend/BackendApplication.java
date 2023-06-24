package com.farecompare.backend;

import java.util.Map;
import java.util.HashMap;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@SpringBootApplication
public class BackendApplication {
	@CrossOrigin(origins = "*")
	@GetMapping("/health_check")
	public ResponseEntity<String> home() {
		return ResponseEntity.ok("Health Check Successful");
	}

	@CrossOrigin(origins = "https://fare-compare.vercel.app")
	@PostMapping("/analyse")
	public String handleFileUpload(@RequestBody MultipartFile file) {
		// Logic to handle the file
		// You can access the file data using the 'file' parameter
		System.out.println("In controller");
		if (!file.isEmpty()) {
			System.out.println("File not empty");
			try {
				// Process the file, e.g., save it to a database or perform some operations

				// Return a success message
				return "File uploaded successfully!";
			} catch (Exception e) {
				// Handle any exceptions that occur during file processing

				// Return an error message
				return "File upload failed: " + e.getMessage();
			}
		} else {
			// Return an error message if the file is empty
			System.out.println("File is empty");
			return "File upload failed: The uploaded file is empty.";
		}
	}

	public static void main(String[] args) {
		SpringApplication.run(BackendApplication.class, args);
	}

}
