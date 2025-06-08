# Spring Boot Quiz Application

## Description

This is a backend application for a quiz platform, built using Spring Boot. It allows for the creation and management of questions, generation of quizzes based on categories, and submission of quiz answers for scoring. This project demonstrates key concepts of building RESTful APIs with the Spring Framework.

## Features

*   **Question Management (CRUD):**
    *   Add new questions with multiple-choice options and a correct answer.
    *   View all questions.
    *   View questions by category.
    *   Update existing questions.
    *   Delete questions.
*   **Quiz Generation:**
    *   Dynamically create quizzes by specifying a category, number of questions, and a title.
*   **Quiz Gameplay:**
    *   Retrieve a quiz by its ID to display questions to the user.
    *   Submit answers for a quiz and receive a calculated score.
*   **DTO Pattern:** Uses Data Transfer Objects (DTOs) for API request/response, mapped using MapStruct.
*   **Global Exception Handling:** Centralized error handling for custom exceptions like `ResourceNotFoundException` and `QuestionAlreadyExistsException`.

## Technologies Used

*   **Java 17** (or your specific Java version)
*   **Spring Boot** (e.g., 3.x.x)
    *   Spring Web: For building RESTful APIs.
    *   Spring Data JPA: For data persistence.
    *   Spring Boot DevTools: For enhanced development experience.
*   **Hibernate:** As the JPA provider.
*   **PostgreSQL:** As the relational database.
*   **Maven:** As the build and dependency management tool.
*   **Lombok:** To reduce boilerplate code (getters, setters, constructors, etc.).
*   **MapStruct:** For mapping between DTOs and entity objects.
## API Endpoints

### Question Endpoints (`/questions`)

*   `GET /questions/allQuestions`: Retrieves all questions from the database.
*   `GET /questions/category/{category}`: Retrieves all questions for a specific category.
    *   Example: `/questions/category/Java`
*   `POST /questions/add`: Adds a new question.
    *   Request Body: `Question` JSON object.
    *   Example:
    * {
      "questionTitle": "What is the primary color of the sun in children’s drawings?",
      "option1": "Red",
      "option2": "Blue",
      "option3": "Yellow",
      "option4": "Green",
      "rightAnswer": "Yellow",
      "difficultylevel": "Easy",
      "category": "Baby"
      }
### Quiz Endpoints (`/quizzes`)
* `POST http://localhost:8080/quizzes?category=Baby&numQ=3&title=Babyverse101` : Create Quiz 1 title Babyverse101
* `GET http://localhost:8080/quizzes/1` : Get Quizz
* `POST http://localhost:8080/quizzes/1`: Submit Quiz
  *   Request Body: `Question` JSON object.
    *   Example:
       [
        {"id": 1, "response": "User's answer for question 1"},
        {"id": 2, "response": "optionB"},
        {
      "id": 15,
      "response": "[option4,option1]"
      }
  
        ]