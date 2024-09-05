package com.example.EventSourcing;

// OrderReadService.java
@Service
public class OrderReadService {
    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private InventoryService inventoryService;

    public void updateOrderReadModel(OrderCreatedEvent event) {
        // Update read model
        Order order = new Order(event.getOrderId(), event.getItems());
        orderRepository.save(order);

        // Ensure eventual consistency
        inventoryService.updateInventory(order);
    }
}
