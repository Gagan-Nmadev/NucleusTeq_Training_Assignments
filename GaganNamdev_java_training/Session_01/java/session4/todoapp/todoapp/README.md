# Todo Application (Spring Boot)

A RESTful Todo API built using Spring Boot, following clean layered architecture and enterprise coding practices.

---

## Tech Stack

* Java 17
* Spring Boot
* Spring Data JPA (Hibernate)
* H2 Database (In-Memory)
* Maven
* SLF4J Logging

---

##  How to Run

1. Open project in IntelliJ
2. Run `TodoappApplication.java`
3. Application starts at:

```
http://localhost:8080
```

No external database required (H2 runs in-memory).

---

## 📁 Project Structure

```
src/main/java/com/training/todoapp/

├── controller/
│   └── TodoController.java

├── service/
│   └── TodoService.java

├── repository/
│   └── TodoRepository.java

├── dto/
│   └── TodoDTO.java

├── entity/
│   └── Todo.java

├── exception/
│   ├── GlobalExceptionHandler.java
│   └── TodoNotFoundException.java

├── client/
│   └── NotificationServiceClient.java
```

---

##  Architecture

```
Controller → Service → Repository
                  ↓
        NotificationServiceClient
```

* Controller handles HTTP requests
* Service contains business logic
* Repository interacts with database
* DTO used for request/response
* Entity not exposed directly

---

##  API Endpoints

| Method | Endpoint    | Description    |
| ------ | ----------- | -------------- |
| POST   | /todos      | Create Todo    |
| GET    | /todos      | Get All Todos  |
| GET    | /todos/{id} | Get Todo by ID |
| PUT    | /todos/{id} | Update Todo    |
| PATCH  | /todos/{id} | Partial Update |
| DELETE | /todos/{id} | Delete Todo    |

---

##  Sample Request

### Create Todo

```
POST /todos
```

```json
{
  "title": "Learn Spring Boot",
  "description": "Complete assignment"
}
```

---

##  Sample Response

```json
{
  "id": 1,
  "title": "Learn Spring Boot",
  "description": "Complete assignment",
  "status": "PENDING",
  "createdAt": "2026-04-19T10:00:00"
}
```

---

## ⚠ Validation Rules

* `title` → required, minimum 3 characters
* `status` → PENDING / COMPLETED

---

## \ Status Rules

* Default status = **PENDING**
* Allowed transitions:

    * PENDING → COMPLETED
    * COMPLETED → PENDING

 Same status update → returns error (400)

---

##  Error Handling

| Scenario              | Status |
| --------------------- | ------ |
| Todo not found        | 404    |
| Validation failure    | 400    |
| Invalid status        | 400    |
| Invalid path variable | 400    |
| Internal server error | 500    |

Response format:

```json
{
  "error": "message"
}
```

---

## Notification Service

A simulated external service (`NotificationServiceClient`) is used to log events:

* Todo created
* Status changed
* Todo deleted

---

## Logging

SLF4J is used for logging across layers:

* Controller → incoming requests
* Service → business operations
* Client → notification events

---

## Key Features

* Constructor-based Dependency Injection
* Clean layered architecture
* Manual DTO mapping
* Global exception handling
* Logging implementation
* In-memory database

---

## Notes

* No real database used
* Project follows Spring Boot best practices
* Code is clean and maintainable
