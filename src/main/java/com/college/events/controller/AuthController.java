package com.college.events.controller;

import com.college.events.model.User;
import com.college.events.service.UserService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 * Handles login, registration, and logout pages.
 *
 * @author Azeem
 */
@Controller
@RequestMapping("/auth")
public class AuthController {

    private final UserService userService;

    public AuthController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/login")
    public String loginPage() {
        return "auth/login";
    }

    @GetMapping("/register")
    public String registerPage(Model model) {
        model.addAttribute("user", new User());
        return "auth/register";
    }

    @PostMapping("/register")
    public String registerUser(@Valid @ModelAttribute("user") User user,
                               BindingResult result,
                               RedirectAttributes redirectAttributes) {
        if (result.hasErrors()) {
            return "auth/register";
        }

        if (userService.emailExists(user.getEmail())) {
            result.rejectValue("email", "error.user", "An account with this email already exists");
            return "auth/register";
        }

        userService.registerUser(user.getFullName(), user.getEmail(), user.getPassword());
        redirectAttributes.addFlashAttribute("success", "Registration successful! Please login.");
        return "redirect:/auth/login";
    }
}
