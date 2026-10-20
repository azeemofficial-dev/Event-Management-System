package com.college.events.controller;

import com.college.events.model.Event;
import com.college.events.model.Registration;
import com.college.events.service.DashboardService;
import com.college.events.service.EventService;
import com.college.events.service.RegistrationService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Admin dashboard with stats and attendee lists.
 *
 * @author Jiya
 */
@Controller
@RequestMapping("/admin")
public class AdminDashboardController {

    private final DashboardService dashboardService;
    private final EventService eventService;
    private final RegistrationService registrationService;

    public AdminDashboardController(DashboardService dashboardService,
                                    EventService eventService,
                                    RegistrationService registrationService) {
        this.dashboardService = dashboardService;
        this.eventService = eventService;
        this.registrationService = registrationService;
    }

    @GetMapping("/dashboard")
    public String dashboard(Model model) {
        model.addAttribute("totalEvents", dashboardService.getTotalEvents());
        model.addAttribute("totalRegistrations", dashboardService.getTotalRegistrations());
        model.addAttribute("registrationsPerEvent", dashboardService.getRegistrationsPerEvent());
        model.addAttribute("events", eventService.getAllEvents());
        return "admin/dashboard";
    }

    @GetMapping("/events/{eventId}/attendees")
    public String attendeeList(@PathVariable Long eventId,
                               @RequestParam(required = false) String search,
                               Model model) {
        Optional<Event> optEvent = eventService.getEventById(eventId);
        if (optEvent.isEmpty()) {
            return "redirect:/admin/dashboard";
        }

        Event event = optEvent.get();
        List<Registration> registrations = registrationService.getEventRegistrations(event);

        // Optional search/filter by attendee name or email
        if (search != null && !search.isBlank()) {
            String keyword = search.toLowerCase();
            registrations = registrations.stream()
                    .filter(r -> r.getUser().getFullName().toLowerCase().contains(keyword)
                              || r.getUser().getEmail().toLowerCase().contains(keyword))
                    .collect(Collectors.toList());
            model.addAttribute("search", search);
        }

        model.addAttribute("event", event);
        model.addAttribute("registrations", registrations);
        return "admin/attendees";
    }

    @PostMapping("/events/{eventId}/attendees/{regId}/delete")
    public String removeAttendee(@PathVariable Long eventId,
                                 @PathVariable Long regId,
                                 RedirectAttributes redirectAttributes) {
        registrationService.cancelRegistrationById(regId);
        redirectAttributes.addFlashAttribute("success", "Attendee removed successfully.");
        return "redirect:/admin/events/" + eventId + "/attendees";
    }
}
