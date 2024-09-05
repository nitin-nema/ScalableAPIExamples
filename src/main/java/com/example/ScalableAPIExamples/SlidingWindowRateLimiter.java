package com.example.ScalableAPIExamples;

public class SlidingWindowRateLimiter {

    private final int capacity;
    private final long windowSize;
    private final Deque<Long> timestamps;

    public SlidingWindowRateLimiter(int capacity, long windowSize) {
        this.capacity = capacity;  // Maximum  number of the request in time woindow
        this.windowSize = windowSize; // duration of the time window in milliseconds
        this.timestamps = new ArrayDeque<>(); // Deque  to store the tiemstamp of the request
    }

    public synchronized boolean allowRequest() {
        long now = System.currentTimeMillis();
        while (!timestamps.isEmpty() && (now - timestamps.peek()) >= windowSize) {
            timestamps.poll();
        }
        if (timestamps.size() < capacity) {
            timestamps.add(now);
            return true;
        }
        return false;
    }
}
