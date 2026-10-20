# Event Registration System

> College Event Registration System built with Spring Boot, Thymeleaf, and H2.

## Features & Roles

### 👤 Student / Attendee
- Register an account directly from the register page with the "Student / Attendee" role.
- Browse upcoming events and search events by name.
- View detailed event information and remaining ticket capacity.
- Register for events with instant duplicate-registration and capacity checks.
- View personal registrations under "My Registrations".
- Cancel an existing registration anytime.

### 🛡️ Admin / Organizer
- Register an account with the "Event Organizer / Admin" role or use the seeded admin account.
- Access the Admin Dashboard with total statistics and per-event registration numbers.
- Create new events, update details (dates, venue, capacity), or delete events.
- View real-time attendee lists for any event with instant search/filter.
- Remove attendees from an event directly within the app.

## Tech Stack
- **Backend:** Java 17, Spring Boot 3 (Spring Web, Spring Data JPA, Spring Security, Validation)
- **Database:** H2 (in-memory)
- **Frontend / View:** Thymeleaf + Bootstrap 5 + Bootstrap Icons (via CDN)
- **Build Tool:** Maven

## Running the Application

```bash
mvn spring-boot:run
```
Open **http://localhost:8080** in your browser.

### Seed Accounts (Pre-loaded)
| Role | Email | Password |
|---|---|---|
| Admin | `admin@college.com` | `admin123` |
| Attendee | `john@college.com` | `password` |
| Attendee | `jane@college.com` | `password` |

You can also create your own Admin or Attendee account directly on the Registration page.
