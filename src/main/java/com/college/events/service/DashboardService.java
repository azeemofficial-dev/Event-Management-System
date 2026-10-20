package com.college.events.service;

import com.college.events.model.Event;
import com.college.events.repository.EventRepository;
import com.college.events.repository.RegistrationRepository;
import org.springframework.stereotype.Service;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * Aggregates statistics for the admin dashboard.
 *
 * @author Jiya
 */
@Service
public class DashboardService {

    private final EventRepository eventRepository;
    private final RegistrationRepository registrationRepository;

    public DashboardService(EventRepository eventRepository,
                            RegistrationRepository registrationRepository) {
        this.eventRepository = eventRepository;
        this.registrationRepository = registrationRepository;
    }

    public long getTotalEvents() {
        return eventRepository.count();
    }

    public long getTotalRegistrations() {
        return registrationRepository.count();
    }

    /**
     * Returns a map of Event name → registration count.
     */
    public Map<String, Long> getRegistrationsPerEvent() {
        List<Event> events = eventRepository.findAll();
        Map<String, Long> stats = new LinkedHashMap<>();
        for (Event event : events) {
            long count = registrationRepository.countByEvent(event);
            stats.put(event.getName(), count);
        }
        return stats;
    }
}
