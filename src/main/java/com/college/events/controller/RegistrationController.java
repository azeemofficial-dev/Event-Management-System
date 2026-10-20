package com.college.events.controller;

import com.college.events.model.Event;
import com.college.events.model.Registration;
import com.college.events.model.User;
import com.college.events.service.EventService;
import com.college.events.service.RegistrationService;
import com.college.events.service.UserService;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Optional;

/**
 * Handles event registration, cancellation, and "My Registrations" page.
 *
 * @author Bhumika
 */
@Controller
@RequestMapping("/registrations")
public class RegistrationController {

    private final RegistrationService registrationService;
    private final EventService eventService;
    private final UserService userService;

    public RegistrationController(RegistrationService registrationService,
                                  EventService eventService,
                                  UserService userService) {
        this.registrationService = registrationService;
        this.eventService = eventService;
        this.userService = userService;
    }

    /**
     * Register the logged-in user for an event.
     */
    @PostMapping("/register/{eventId}")
    public String registerForEvent(@PathVariable Long eventId,
                                   Authentication authentication,
                                   RedirectAttributes redirectAttributes) {
        User user = getLoggedInUser(authentication);
        Optional<Event> optEvent = eventService.getEventById(eventId);

        if (optEvent.isEmpty()) {
            redirectAttributes.addFlashAttribute("error", "Event not found.");
            return "redirect:/events";
        }

        try {
            registrationService.registerForEvent(user, optEvent.get());
            redirectAttributes.addFlashAttribute("success", "Successfully registered for the event!");
        } catch (RuntimeException e) {
            redirectAttributes.addFlashAttribute("error", e.getMessage());
        }

        return "redirect:/events/" + eventId;
    }

    /**
     * Cancel the logged-in user's registration for an event.
     */
    @PostMapping("/cancel/{eventId}")
    public String cancelRegistration(@PathVariable Long eventId,
                                     Authentication authentication,
                                     RedirectAttributes redirectAttributes) {
        User user = getLoggedInUser(authentication);
        Optional<Event> optEvent = eventService.getEventById(eventId);

        if (optEvent.isEmpty()) {
            redirectAttributes.addFlashAttribute("error", "Event not found.");
            return "redirect:/events";
        }

        try {
            registrationService.cancelRegistration(user, optEvent.get());
            redirectAttributes.addFlashAttribute("success", "Registration cancelled.");
        } catch (RuntimeException e) {
            redirectAttributes.addFlashAttribute("error", e.getMessage());
        }

        return "redirect:/events/" + eventId;
    }

    /**
     * Show the "My Registrations" page for the logged-in user.
     */
    @GetMapping("/my")
    public String myRegistrations(Authentication authentication, Model model) {
        User user = getLoggedInUser(authentication);
        model.addAttribute("registrations", registrationService.getUserRegistrations(user));
        return "registrations/my-registrations";
    }

    /**
     * Show dedicated printable Event Entry Pass.
     */
    @GetMapping("/pass/{regId}")
    public String viewPass(@PathVariable Long regId, Authentication authentication, Model model) {
        User user = getLoggedInUser(authentication);
        Optional<Registration> optReg = registrationService.getRegistrationById(regId);
        if (optReg.isEmpty()) {
            return "redirect:/registrations/my";
        }
        Registration reg = optReg.get();
        // Ensure user can only view their own pass (or admin)
        if (!reg.getUser().getId().equals(user.getId()) && user.getRole() != com.college.events.model.Role.ADMIN) {
            return "redirect:/registrations/my";
        }
        model.addAttribute("registration", reg);
        return "registrations/pass";
    }

    private User getLoggedInUser(Authentication authentication) {
        return userService.findByEmail(authentication.getName())
                .orElseThrow(() -> new RuntimeException("User not found"));
    }
}
