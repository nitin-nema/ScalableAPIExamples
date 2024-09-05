package com.example.ScalableAPIExamples;


import org.springframework.cache.annotation.CacheEvict;

@Service
public class UserService {

    @Cacheable("users")
    public User getUserById(Long id) {
        // Simulate slow service
        simulateSlowService();
        return new User(id, "John", "Doe");
    }

    private void simulateSlowService() {
        try {
            Thread.sleep(3000L);
        } catch (InterruptedException e) {
            throw new IllegalStateException(e);
        }
    }
}

//@CacheEvict  //to remove the old stale data
//@CachePut   // update the data
// peronsaliation