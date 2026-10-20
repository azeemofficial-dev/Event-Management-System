# Event Registration System

> College Event Registration System built with Spring Boot, Thymeleaf, and H2.

## Tech Stack

| Layer      | Choice                                                      |
|------------|-------------------------------------------------------------|
| Language   | Java 17                                                     |
| Framework  | Spring Boot 3 (Web, Data JPA, Validation)                   |
| Security   | Spring Security (form login, BCrypt, roles: ADMIN, ATTENDEE)|
| Database   | H2 (in-memory / file mode)                                  |
| View       | Thymeleaf + Spring Security dialect                         |
| UI         | Bootstrap 5 via CDN                                         |
| Build      | Maven                                                       |
| Automation | UiPath (attendee report bot / bulk registration bot)        |
| VCS        | Git & GitHub, PR-based merges into main                     |

## Team

| Member  | Responsibilities                                            |
|---------|-------------------------------------------------------------|
| Azeem   | Foundation, User & Event entities, Auth, Event CRUD, seed data, integration |
| Bhumika | Registration entity, register/cancel flow, "My Registrations" page         |
| Jiya    | Admin dashboard, attendee list, UiPath bot, README & docs                  |

## Getting Started

### Prerequisites
- Java 17+
- Maven 3.8+

### Run Locally
```bash
# Clone the repo
git clone https://github.com/azeemofficial-dev/Event-Management-System.git
cd Event-Management-System

# Build and run
mvn spring-boot:run
```
The app starts on **http://localhost:8080**.

### Default Accounts (Seed Data)
| Role     | Username           | Password   |
|----------|--------------------|------------|
| Admin    | admin@college.com  | admin123   |
| Attendee | john@college.com   | password   |
| Attendee | jane@college.com   | password   |

### H2 Console
Available at **http://localhost:8080/h2-console**  
JDBC URL: `jdbc:h2:mem:eventsdb`

## Project Structure

```
src/main/java/com/college/events/
├── config/          SecurityConfig, DataSeeder
├── model/           User, Role, Event, Registration
├── repository/      UserRepository, EventRepository, RegistrationRepository
├── service/         UserService, EventService, RegistrationService, DashboardService
└── controller/      AuthController, EventController, AdminEventController,
                     RegistrationController, AdminDashboardController
```

## Screenshots

> _(see docs/screenshots/)_

## License

This project is for academic purposes.
