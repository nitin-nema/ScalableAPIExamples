package com.example.ScalableAPIExamples;

@Component
public class AdaptiveRateLimiter {

    private int maxRequests = 5;

    @Scheduled(fixedRate = 60000)
    public void adjustRateLimit() {
        long currentLoad = getCurrentSystemLoad();
        if (currentLoad > THRESHOLD) {
            maxRequests = Math.max(1, maxRequests - 1);
        } else {
            maxRequests++;
        }
    }

    private long getCurrentSystemLoad() {
        // Simulate system load calculation
        return new Random().nextInt(100);
    }
}
