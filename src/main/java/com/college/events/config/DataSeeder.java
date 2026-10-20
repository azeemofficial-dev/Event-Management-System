package com.college.events.config;

import com.college.events.model.Event;
import com.college.events.model.Role;
import com.college.events.model.User;
import com.college.events.repository.EventRepository;
import com.college.events.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.time.LocalDate;
import java.time.LocalTime;

/**
 * Seeds the database with an admin account and sample events on startup.
 *
 * @author Azeem
 */
@Configuration
public class DataSeeder {

    @Bean
    CommandLineRunner seedData(UserRepository userRepository,
                               EventRepository eventRepository,
                               PasswordEncoder passwordEncoder) {
        return args -> {
            // ── Seed Users ──────────────────────────────────────
            if (userRepository.count() == 0) {
                User admin = new User(
                    "Admin User",
                    "admin@college.com",
                    passwordEncoder.encode("admin123"),
                    Role.ADMIN
                );
                userRepository.save(admin);

                User john = new User(
                    "John Doe",
                    "john@college.com",
                    passwordEncoder.encode("password"),
                    Role.ATTENDEE
                );
                userRepository.save(john);

                User jane = new User(
                    "Jane Smith",
                    "jane@college.com",
                    passwordEncoder.encode("password"),
                    Role.ATTENDEE
                );
                userRepository.save(jane);

                System.out.println("✔ Seeded 3 users (1 admin, 2 attendees)");
            }

            // ── Seed Events ─────────────────────────────────────
            if (eventRepository.count() == 0) {
                eventRepository.save(new Event(
                    "Tech Talk: Introduction to AI",
                    "A beginner-friendly session on artificial intelligence, covering fundamental concepts, real-world applications, and hands-on demos.",
                    LocalDate.now().plusDays(7),
                    LocalTime.of(10, 0),
                    LocalTime.of(12, 0),
                    "Auditorium A",
                    100
                ));

                eventRepository.save(new Event(
                    "Annual Cultural Fest",
                    "The flagship cultural festival featuring music, dance, drama, and art competitions. Open to all departments.",
                    LocalDate.now().plusDays(14),
                    LocalTime.of(9, 0),
                    LocalTime.of(18, 0),
                    "Main Ground",
                    500
                ));

                eventRepository.save(new Event(
                    "Hackathon 2026",
                    "A 24-hour coding marathon. Build innovative solutions in teams of up to 4. Prizes for top 3 teams.",
                    LocalDate.now().plusDays(21),
                    LocalTime.of(8, 0),
                    LocalTime.of(8, 0),
                    "CS Lab Block",
                    200
                ));

                eventRepository.save(new Event(
                    "Workshop: Web Development with Spring Boot",
                    "Hands-on workshop covering Spring Boot, Thymeleaf, and deploying web applications. Bring your laptop!",
                    LocalDate.now().plusDays(10),
                    LocalTime.of(14, 0),
                    LocalTime.of(17, 0),
                    "Seminar Hall 2",
                    60
                ));

                System.out.println("✔ Seeded 4 sample events");
            }
        };
    }
}
