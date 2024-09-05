package com.example.EventSourcing;

// Event Archiving Service (EventArchivingService.java)
@Service
public class EventArchivingService {
    @Autowired
    private EventRepository eventRepository;

    @Autowired
    private ArchivedEventRepository archivedEventRepository;

    @Scheduled(cron = "0 0 0 * * ?")
    public void archiveOldEvents() {
        LocalDateTime cutoffDate = LocalDateTime.now().minusMonths(6);
        List<EventDocument> oldEvents = eventRepository.findByTimestampBefore(cutoffDate);

        archivedEventRepository.saveAll(oldEvents);
        eventRepository.deleteAll(oldEvents);
    }
}
