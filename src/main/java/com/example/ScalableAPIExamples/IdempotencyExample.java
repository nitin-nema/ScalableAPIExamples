package com.example.ScalableAPIExamples;


// consistency in distributed system
@PutMapping("/user/{id}")
public ResponseEntity<User> updateUser(@PathVariable Long id, @RequestBody User user) {
    // Update user logic
    User updatedUser = userService.updateUser(id, user);
    return ResponseEntity.ok(updatedUser);
}
