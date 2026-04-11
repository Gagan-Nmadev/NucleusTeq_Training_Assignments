### Java Training - Session 2 (Spring Boot Assignment)

## Overview

This project is developed as part of Session 2 of the Java training assignment.

It is a Spring Boot REST API application that demonstrates core backend concepts such as layered architecture, dependency injection, and component-based design.

The application follows clean coding practices with proper separation of concerns and uses in-memory data instead of a database.

---

## Architecture

The project follows a strict layered architecture:

Controller → Service → Repository
Controller → Service → Component

* **Controller** - Handles HTTP requests and responses
* **Service** - Contains business logic
* **Repository** - Manages in-memory data
* **Component** - Provides reusable helper logic

---

## Key Design Principles

* Constructor-based dependency injection is used
* No field or setter injection
* Business logic is only inside the Service layer
* Controller does not directly interact with Repository
* Clean separation of concerns

---

## Systems Implemented

### 1. User Management System

* Fetch all users
* Fetch user by ID
* Create new user
* Data is stored using in-memory list

---

### 2. Notification System

* API triggers a notification
* Message generation handled by NotificationComponent

---

### 3. Dynamic Message Formatter System

* Returns different messages based on type
* Uses:

  * ShortMessageFormatter
  * LongMessageFormatter
* Decision logic handled inside Service

---

## API Endpoints

### User Management

| Method | Endpoint    | Description       |
| ------ | ----------- | ----------------- |
| GET    | /users      | Fetch all users   |
| GET    | /users/{id} | Fetch user by id  |
| POST   | /users      | Create a new user |

---

### Notification

| Method | Endpoint | Description          |
| ------ | -------- | -------------------- |
| GET    | /notify  | Trigger notification |

---

### Message Formatter

| Method | Endpoint            | Description       |
| ------ | ------------------- | ----------------- |
| GET    | /message?type=SHORT | Get short message |
| GET    | /message?type=LONG  | Get long message  |

---

## Technologies Used

* Java 17
* Spring Boot
* Maven
* REST APIs

---

## Spring Concepts Demonstrated

* IoC (Inversion of Control)
* Dependency Injection (Constructor-based)
* Component Scanning

---

## Annotations Used

* @RestController
* @Service
* @Repository
* @Component

---

## How To Run

### Prerequisites

* Java 17
* Maven

### Steps

```bash
# Clone repository
git clone https://github.com/GaganNamdev/GaganNamdev_java_training.git

# Navigate to project
cd GaganNamdev_java_training/java/session2

# Run application
mvn spring-boot:run
```

---

## Application URL

http://localhost:8080

---

## Sample Requests

### Fetch All Users

GET http://localhost:8080/users

### Fetch User By Id

GET http://localhost:8080/users/1

### Create User

POST http://localhost:8080/users

```json
{
  "id": 3,
  "name": "Shyam"
}
```

---

### Notification

GET http://localhost:8080/notify

---

### Message API

GET http://localhost:8080/message?type=SHORT
GET http://localhost:8080/message?type=LONG

---

## Project Structure

```
GaganNamdev_java_training/
 ├── java/
 │    └── session2/
 │         └── springboot/
 │              ├── controller/
 │              ├── service/
 │              ├── repository/
 │              ├── model/
 │              └── component/
 └── session1/
```

