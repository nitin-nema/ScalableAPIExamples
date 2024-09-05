package com.example.ScalableAPIExamples;

public class PagingFilteringExample {
}
@RestController
@RequestMapping("/users")
public class UserPaginationController {

    @Autowired
    private UserRepository userRepository;

    @GetMapping
    public ResponseEntity<Page<User>> getUsers(Pageable pageable) {  // ?page=0&size=10
        Page<User> users = userRepository.findAll(pageable);
        return ResponseEntity.ok(users);
    }
}
