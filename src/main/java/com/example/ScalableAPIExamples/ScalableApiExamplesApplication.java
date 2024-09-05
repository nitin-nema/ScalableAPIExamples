package com.example.ScalableAPIExamples;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;



@SpringBootApplication
public class ScalableAPIExamples {
	public static void main(String[] args) {
		SpringApplication.run(ScalableAPIExamples.class, args);
	}
}

@RestController
@RequestMapping("/api")
public class StatusController {

	@GetMapping("/status")
	public ResponseEntity<String> getStatus() throws UnknownHostException {
		//Reterive the server/instance
		String instanceId = InetAddress.getLocalHost().getHostName();
		return ResponseEntity.ok("Running on instance: " + instanceId);
	}
}

//localhost/api/status