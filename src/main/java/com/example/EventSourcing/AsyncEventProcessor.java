package com.example.EventSourcing;

// Async Event Processor (AsyncEventProcessor.java)
@Service
public class AsyncEventProcessor {
    @Autowired
    private OrderReadService orderReadService;

    @Async
    public void processEvent(OrderCreatedEvent event) {
        try {
            orderReadService.updateOrderReadModel(event);
        } catch (Exception e) {
            // Log error and retry if necessary
        }
    }
}
