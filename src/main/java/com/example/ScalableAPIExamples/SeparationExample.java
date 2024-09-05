package com.example.ScalableAPIExamples;

// User Service
@RestController
@RequestMapping("/users")
public class UserController {

    @PostMapping
    public ResponseEntity<User> createUser(@RequestBody User user) {
        // Logic to create a user
        return ResponseEntity.status(HttpStatus.CREATED).body(user);
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> getUser(@PathVariable Long id) {
        // Logic to retrieve a user
        return ResponseEntity.ok(new User(id, "John", "Doe"));
    }
}

// Order Service
@RestController
@RequestMapping("/orders")
public class OrderController {

    @PostMapping
    public ResponseEntity<Order> createOrder(@RequestBody Order order) {
        // Logic to create an order
        return ResponseEntity.status(HttpStatus.CREATED).body(order);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Order> getOrder(@PathVariable Long id) {
        // Logic to retrieve an order
        return ResponseEntity.ok(new Order(id, "Order Details"));
    }
}
