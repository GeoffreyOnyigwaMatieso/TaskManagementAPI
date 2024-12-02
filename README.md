
# Task Management System

## Overview

The **Task Management System** is a Spring Boot application designed to manage tasks and associated tags. It provides an API for creating, updating, retrieving, and managing tasks, along with their tags. This system is designed to be a robust and scalable solution for task organization.

## Features

- **CRUD Operations for Tasks**: Create, Read, Update, and Delete tasks.
- **Tag Management**: Assign tags to tasks, ensuring better categorization and organization.
- **Relational Data Handling**: Manage relationships between tasks and tags seamlessly.

## Technologies Used

- **Backend Framework**: Spring Boot
- **Database**: H2 (can be replaced with MySQL/PostgreSQL in production)
- **ORM**: Hibernate/JPA
- **Dependency Management**: Maven
- **Serialization**: Jackson for JSON serialization/deserialization
- **Lombok**: Simplifies boilerplate code like getters, setters, etc.

## API Endpoints

### Tasks
1. **Get Task by ID**
   - `GET /api/tasks/{id}`
   - Fetch a task by its unique ID.

2. **Create a Task**
   - `POST /api/tasks`
   - Request Body:  
     ```json
     {
       "title": "Task Title",
       "completed": true,
       "tags": ["sleeping", "eating"]
     }
     ```
   - Response:  
     ```json
     {
       "id": 1,
       "title": "Task Title",
       "completed": true,
       "tags": ["sleeping", "eating"]
     }
     ```

3. **Update a Task**
   - `PUT /api/tasks/{id}`
   - Request Body (same as Create Task):
   - Response: Updated task.

4. **Delete a Task**
   - `DELETE /api/tasks/{id}`

### Tags
1. **Get All Tags**
   - `GET /api/tags`
   - Response:  
     ```json
     [
       {
         "id": 1,
         "name": "tag1"
       },
       {
         "id": 2,
         "name": "tag2"
       }
     ]
     ```

## Data Models

### Task
| Field   | Type        | Description             |
|---------|-------------|-------------------------|
| `id`    | Long        | Unique identifier       |
| `title` | String      | Title of the task       |
| `completed`| boolean     | Status of the task      |
| `tags`  | Set<Tag>    | Tags associated with it |

### Tag
| Field   | Type    | Description          |
|---------|---------|----------------------|
| `id`    | Long    | Unique identifier    |
| `name`  | String  | Name of the tag      |

## Running the Application

### Prerequisites
1. Java 17+
2. Maven 3.8+
3. IDE (e.g., IntelliJ IDEA, Eclipse)

### Steps
1. Clone the repository:
   ```bash
   git clone https://github.com/your-repo/task-management-system.git
   ```
2. Navigate to the project directory:
   ```bash
   cd task-management-system
   ```
3. Check the `application.properties` file for database configuration. Ensure the settings match your local database setup.
4. Build the project:
   ```bash
   mvn clean install
   ```
5. Run the application:
   ```bash
   mvn spring-boot:run
   ```
6. Access the application at:  
   `http://localhost:8080`

### Swagger Documentation
- The application provides Swagger API documentation, accessible at:  
  `http://localhost:8080/swagger-ui.html`  

## Branching Strategy

- **Default Branch**: I have currently set `develop` as our default branch. Ensure that all feature branches are based on `develop` and create pull requests targeting `develop` for new changes.

## Testing the Application

Use tools like **Postman** or **cURL** to test the API endpoints. Alternatively, Swagger/OpenAPI can be used for interactive API testing.

