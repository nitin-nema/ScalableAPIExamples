package com.example.ScalableAPIExamples;

@GetMapping("/users")
public ResponseEntity<User> getAllUsers() {
    Item item= userService.findAll();
    return ResponseEntity.ok();
}

@GetMapping("/users")
public ResponseEntity<User> getAllUsers(@PathVariable(id) Long id) {
    Item item= userService.findById(id);
    return ResponseEntity.ok(item);
}

@DeleteMapping("/user/{id}")
public ResponseEntity<User> deleteUser(@PathVariable("id) Long id) {
        userService.delete(id);
        return ResponseEntity.noContent().build();
        }

        Non-Idempontent
        @PostMapping("/user")

        public ResponseEntity<Item> saveUser(@RequestBody User user) {

    Item item= userService.create(user);

    return ResponseEntity.status(HttpStatus.CREATED).body(item);

}

_____
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatatureAlgorithm;

@PostMapping("/login")
public ResponseEntity<String> login(@RequestParam String username){
    String token = Jwts.builder()
            .setSubject(username)
            .signWith(SignatatureAlgorithm.HS256,"your--secret-key")
            .compact();
    return ResponseEntity.ok(token);
}

// input validation
@PostMapping("/add-user")
public ResponseEntity<String> saveUser(@RequestParam @Size(min=2, max=30) @NotNull String name,
                                       @RequestParam @Min(18) int age {
    return ResponseEntity.ok("user created successfully");
}