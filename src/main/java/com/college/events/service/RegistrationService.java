package com.college.events.service;

import com.college.events.model.Event;
import com.college.events.model.Registration;
import com.college.events.model.User;
import com.college.events.repository.EventRepository;
import com.college.events.repository.RegistrationRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

/**
 * @author Bhumika
 */
@Service
public class RegistrationService {

    private final RegistrationRepository registrationRepository;
    private final EventRepository eventRepository;

    public RegistrationService(RegistrationRepository registrationRepository,
                               EventRepository eventRepository) {
        this.registrationRepository = registrationRepository;
        this.eventRepository = eventRepository;
    }

    /**
     * Register a user for an event.
     * Checks capacity and prevents duplicate sign-ups.
     */
    @Transactional
    public Registration registerForEvent(User user, Event event) {
        // Duplicate check
        if (registrationRepository.existsByUserAndEvent(user, event)) {
            throw new RuntimeException("You are already registered for this event.");
        }
        // Capacity check
        if (event.isFull()) {
            throw new RuntimeException("This event is full. Registration closed.");
        }

        Registration registration = new Registration(user, event);
        Registration saved = registrationRepository.save(registration);

        // Increment registered count
        event.setRegisteredCount(event.getRegisteredCount() + 1);
        eventRepository.save(event);

        return saved;
    }

    /**
     * Cancel a user's registration for an event.
     */
    @Transactional
    public void cancelRegistration(User user, Event event) {
        Registration registration = registrationRepository.findByUserAndEvent(user, event)
                .orElseThrow(() -> new RuntimeException("Registration not found."));

        registrationRepository.delete(registration);

        // Decrement registered count
        event.setRegisteredCount(Math.max(0, event.getRegisteredCount() - 1));
        eventRepository.save(event);
    }

    public List<Registration> getUserRegistrations(User user) {
        return registrationRepository.findByUser(user);
    }

    public List<Registration> getEventRegistrations(Event event) {
        return registrationRepository.findByEvent(event);
    }

    public boolean isUserRegistered(User user, Event event) {
        return registrationRepository.existsByUserAndEvent(user, event);
    }

    public long countRegistrations() {
        return registrationRepository.count();
    }

    public long countRegistrationsByEvent(Event event) {
        return registrationRepository.countByEvent(event);
    }
}
