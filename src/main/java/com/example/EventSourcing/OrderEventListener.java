package com.example.EventSourcing;

// Event Listener (OrderEventListener.java)
@Component
public class OrderEventListener {
    @Autowired
    private OrderReadService orderReadService;

    @EventListener
    public void handleOrderCreated(OrderCreatedEvent event) {
        // Update read model
        orderReadService.updateOrderReadModel(event);
    }
}

// Read Model Update Service (OrderReadService.java)
@Service
public class OrderReadService {
    @Autowired
    private OrderRepository orderRepository;

    public void updateOrderReadModel(OrderCreatedEvent event) {
        // Update or create the order in the read model
        Order order = new Order(event.getOrderId(), event.getItems());
        orderRepository.save(order);
    }
}
