package com.example.ScalableAPIExamples;
//Graceful degradtion

@RestControllerAdvice
public class RateLimitExceptionHandler {

    @ExceptionHandler(HttpStatus.TOO_MANY_REQUESTS.class)
    public ResponseEntity<String> handleRateLimitExceeded() {
        return new ResponseEntity<>("Rate limit exceeded. Please try again later.", HttpStatus.TOO_MANY_REQUESTS);
    }
}
