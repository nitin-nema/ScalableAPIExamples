package com.example.ScalableAPIExamples;

public class LeakyBucket {

    private final int capacity;
    private int water; // current level of water (number of requests)
    private final long leakRate; // time in nanoseconds it takes for one request to leak out
    private long lastLeakTime;

    public LeakyBucket(int capacity, long leakRate) {
        this.capacity = capacity;  // maximum nuimber of request or the water level the bucket can hold
        this.water = 0;  // current level of request in the bucket
        this.leakRate = leakRate; //rate at whcih the request(water) leak out of the bucket
        this.lastLeakTime = System.nanoTime();
    }

    public synchronized boolean allowRequest() {
        leak();
        if (water < capacity) {
            water++;
            return true;
        }
        return false;
    }

    private void leak() {
        long now = System.nanoTime();
        long elapsed = now - lastLeakTime;
        long leaks = elapsed / leakRate;
        if (leaks > 0) {
            water = Math.max(0, water - (int) leaks);
            lastLeakTime = now;
        }
    }
}
