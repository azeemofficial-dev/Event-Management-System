package com.college.events.controller;

import com.college.events.model.Event;
import com.college.events.service.EventService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Optional;

/**
 * Admin-only CRUD operations for events.
 *
 * @author Azeem
 */
@Controller
@RequestMapping("/admin/events")
public class AdminEventController {

    private final EventService eventService;

    public AdminEventController(EventService eventService) {
        this.eventService = eventService;
    }

    @GetMapping
    public String listAllEvents(Model model) {
        model.addAttribute("events", eventService.getAllEvents());
        return "events/list";
    }

    @GetMapping("/new")
    public String showCreateForm(Model model) {
        model.addAttribute("event", new Event());
        model.addAttribute("isEdit", false);
        return "events/admin-form";
    }

    @PostMapping("/save")
    public String saveEvent(@Valid @ModelAttribute("event") Event event,
                            BindingResult result,
                            Model model,
                            RedirectAttributes redirectAttributes) {
        if (result.hasErrors()) {
            model.addAttribute("isEdit", event.getId() != null);
            return "events/admin-form";
        }

        eventService.saveEvent(event);
        redirectAttributes.addFlashAttribute("success", "Event saved successfully!");
        return "redirect:/admin/events";
    }

    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable Long id, Model model) {
        Optional<Event> optEvent = eventService.getEventById(id);
        if (optEvent.isEmpty()) {
            return "redirect:/admin/events";
        }
        model.addAttribute("event", optEvent.get());
        model.addAttribute("isEdit", true);
        return "events/admin-form";
    }

    @GetMapping("/delete/{id}")
    public String deleteEvent(@PathVariable Long id, RedirectAttributes redirectAttributes) {
        eventService.deleteEvent(id);
        redirectAttributes.addFlashAttribute("success", "Event deleted successfully!");
        return "redirect:/admin/events";
    }
}
