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
	@CrossOrigin(origins = "http://localhost:3000")
	@GetMapping("/")
	public Map<String, String> home() {
		System.out.println("Hello reached");
		Map<String, String> payload = new HashMap<>();
		payload.put("message", "welcome to the home page");
		return payload;
	}



	// @CrossOrigin(origins = "http://localhost:3000")
	// @PostMapping("/analyse")
	// public ResponseEntity<String> handleFileUpload(@RequestBody MultipartFile file) {
	//   // Process the file as needed
	//   if(file == null) {
	// 	return ResponseEntity.status(HttpStatusCode.valueOf(400)).body("Invalid request");
	//   } 
	//   if (!file.isEmpty()) {
	// 	// File handling logic
	// 	// e.g., save the file, read its content, etc.
	// 	return ResponseEntity.ok("Request processed successfully");
	//   } else {
	// 	return ResponseEntity.status(HttpStatusCode.valueOf(400)).body("Invalid request");
	//   }
	// }

	@CrossOrigin(origins = "http://localhost:3000")
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
