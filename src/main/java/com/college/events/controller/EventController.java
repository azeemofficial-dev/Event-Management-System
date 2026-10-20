package com.college.events.controller;

import com.college.events.model.Event;
import com.college.events.model.User;
import com.college.events.service.EventService;
import com.college.events.service.RegistrationService;
import com.college.events.service.UserService;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Optional;

/**
 * Public-facing event list and event detail pages.
 *
 * @author Azeem
 */
@Controller
public class EventController {

    private final EventService eventService;
    private final RegistrationService registrationService;
    private final UserService userService;

    public EventController(EventService eventService,
                           RegistrationService registrationService,
                           UserService userService) {
        this.eventService = eventService;
        this.registrationService = registrationService;
        this.userService = userService;
    }

    @GetMapping({"/", "/events"})
    public String listEvents(@RequestParam(required = false) String search, Model model) {
        if (search != null && !search.isBlank()) {
            model.addAttribute("events", eventService.searchEvents(search));
            model.addAttribute("search", search);
        } else {
            model.addAttribute("events", eventService.getUpcomingEvents());
        }
        return "events/list";
    }

    @GetMapping("/events/{id}")
    public String eventDetail(@PathVariable Long id, Model model, Authentication authentication) {
        Optional<Event> optEvent = eventService.getEventById(id);
        if (optEvent.isEmpty()) {
            return "redirect:/events";
        }

        Event event = optEvent.get();
        model.addAttribute("event", event);

        // Check if the logged-in user is already registered
        if (authentication != null) {
            Optional<User> optUser = userService.findByEmail(authentication.getName());
            optUser.ifPresent(user ->
                model.addAttribute("isRegistered",
                    registrationService.isUserRegistered(user, event))
            );
        }

        return "events/detail";
    }
}
