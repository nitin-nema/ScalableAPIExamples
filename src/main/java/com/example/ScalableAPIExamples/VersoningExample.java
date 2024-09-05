package com.example.ScalableAPIExamples;

// Version 1 Controller
@RestController
@RequestMapping("/api/v1/users")
public class UserV1Controller {

    @GetMapping("/{id}")
    public ResponseEntity<UserV1> getUserV1(@PathVariable Long id) {
        // Return user details according to version 1 format
        return ResponseEntity.ok(new UserV1("John", "Doe"));
    }
}

// Version 2 Controller
@RestController
@RequestMapping("/api/v2/users")
public class UserV2Controller {

    @GetMapping("/{id}")
    public ResponseEntity<UserV2> getUserV2(@PathVariable Long id) {
        // Return user details according to version 2 format
        return ResponseEntity.ok(new UserV2("John", "Doe", 30));
    }
}
