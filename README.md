# Job Portal Backend

This repository contains the backend implementation for a Job Portal application. The backend is built with Java and follows modern best practices for RESTful API development, security, and scalability.

---

## 🛠 Tech Stack

- **Java 17**
- **Spring Boot**: Core framework for building the RESTful API.
- **Spring Data MongoDB**: For persistence, working with MongoDB as the primary database.
- **Spring Security & JWT**: For authentication and secure API endpoints.
- **Redis**: Used for temporary OTP storage and validation.
- **Jakarta Validation**: For DTO and input validation.
- **Maven**: Build and dependency management.
- **Docker**: Containerization, enabling easy deployment.
- **JavaMailSender**: For sending emails (such as OTPs).
- **Lombok**: Code reduction for DTOs and entities.

---

## 🚀 Features & What’s Implemented

- **User Registration & Authentication**
  - Register and login endpoints for users.
  - Email-based OTP verification using Redis.
  - Password change/reset via OTP.
  - JWT token-based authentication for secure access.

- **Job Management**
  - Post new jobs, view all jobs, view a specific job.
  - Apply to jobs as a user.
  - See jobs posted by a specific user.

- **Profile Management**
  - Create and update user profiles.
  - Fetch profile details.

- **Notification System**
  - Users receive notifications for relevant activities.
  - Mark notifications as read/unread.

- **Security**
  - JWT-based stateless authentication.
  - Spring Security configuration for route protection.
  - Password hashing with BCrypt.

- **Email & OTP**
  - OTP sending and verification for authentication flows.
  - Email notifications and validation.

- **Utilities**
  - Helper methods for generating OTPs, sequence generation for MongoDB, etc.

---

## 📁 Project Structure

```
.
├── Dockerfile
├── pom.xml
├── src/
│   ├── main/java/com/jobportal/
│   │   ├── API/                # REST controllers
│   │   ├── DTO/                # Data transfer objects
│   │   ├── Entity/             # MongoDB entities
│   │   ├── Repository/         # MongoDB and Redis repositories
│   │   ├── Service/            # Business logic
│   │   ├── JWT/                # JWT utilities and filters
│   │   ├── Utility/            # Helper utilities
│   │   └── SecurityConfig.java # Security configuration
└── ...
```

---

## 🌐 Frontend

The frontend for this project is available here:  
[Job Portal Frontend Repository](https://github.com/Jainharshit09/Job-portal)

---

## 🏁 Getting Started

### Prerequisites

- Java 17+
- Maven 3.6+
- Docker (optional)
- MongoDB and Redis running

### Build and Run

#### Using Maven

```bash
./mvnw clean install
./mvnw spring-boot:run
```

#### Using Docker

```bash
docker build -t job-portal-backend .
docker run -p 8080:8080 job-portal-backend
```

---

## 📚 API Overview

Some main endpoints include:
- `POST /users/register` – Register a new user
- `POST /users/login` – User login
- `POST /users/sendOtp/{email}` – Send OTP for verification
- `GET /users/verifyOtp/{email}/{otp}` – Verify OTP
- `POST /users/changePassword` – Change password

- `POST /jobs/post` – Post a new job
- `GET /jobs/getAll` – List all jobs
- `GET /jobs/get/{id}` – Get job by ID
- `POST /jobs/apply/{id}` – Apply for a job

- `GET /profile/{id}` – Get user profile
- More in source code: see `/src/main/java/com/jobportal/API/`

---

## 🤝 Contributing

Feel free to fork this repository and submit pull requests. For major changes, please open an issue first to discuss your ideas.
