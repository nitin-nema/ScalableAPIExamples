package com.example.EventSourcing;

// Command Handler (OrderCommandHandler.java)
@Service
public class OrderCommandHandler {
    @Autowired
    private EventStoreService eventStoreService;

    public void handleCreateOrder(CreateOrderCommand command) {
        // Business logic to create order
        OrderCreatedEvent event = new OrderCreatedEvent(command.getOrderId(), command.getItems());
        eventStoreService.saveEvent(event);
    }
}
