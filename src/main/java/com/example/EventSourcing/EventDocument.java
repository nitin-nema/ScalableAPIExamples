package com.example.EventSourcing;

// Event Document (EventDocument.java)
@Document(collection = "events")
public class EventDocument {
    @Id
    private String id;
    private String aggregateId;
    private String eventType;
    private String payload; // Serialized event data
    private LocalDateTime timestamp;

    // Constructors, Getters, Setters
}

// Event Repository (EventRepository.java)
public interface EventRepository extends MongoRepository<EventDocument, String> {
    List<EventDocument> findByAggregateIdOrderByTimestampAsc(String aggregateId);
}

// Event Store Service (EventStoreService.java)
@Service
public class EventStoreService {
    @Autowired
    private EventRepository eventRepository;

    public void saveEvent(EventDocument event) {
        eventRepository.save(event);
    }

    public List<EventDocument> getEventsForAggregate(String aggregateId) {
        return eventRepository.findByAggregateIdOrderByTimestampAsc(aggregateId);
    }
}

