package com.example.ScalableAPIExamples;

public class TokenBucket {

    private final int capacity;
    private int tokens;
    private final long refillInterval;
    private long lastRefillTime;

    public TokenBucket(int capacity, long refillInterval) {
        this.capacity = capacity;
        this.tokens = capacity;
        this.refillInterval = refillInterval;
        this.lastRefillTime = System.nanoTime();
    }

    public synchronized boolean allowRequest() {
        refillTokens();
        if (tokens > 0) {
            tokens--;
            return true;
        }
        return false;
    }

    private void refillTokens() {
        long now = System.nanoTime(); // high precioson
        long elapsed = now - lastRefillTime;
        long tokensToAdd = elapsed / refillInterval;
        if (tokensToAdd > 0) {
            tokens = Math.min(capacity, tokens + (int) tokensToAdd);
            lastRefillTime = now;
        }
    }
}
