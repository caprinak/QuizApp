package io.satori.quiz.bootstrap;

import io.satori.quiz.model.Question;
import java.util.ArrayList;
import java.util.List;

public class QuestionGenerator {

    public static List<Question> generateQuestions() {
        List<Question> questions = new ArrayList<>();

        // Baby Questions
        questions.add(Question.builder()
                .questionTitle("What is a common first word for a baby?")
                .option1("Mama")
                .option2("Dada")
                .option3("Ball")
                .option4("Cat")
                .rightAnswer("Mama")
                .difficultylevel("Easy")
                .category("Baby")
                .build());

        questions.add(Question.builder()
                .questionTitle("At what age do babies typically start to crawl?")
                .option1("1-3 months")
                .option2("4-6 months")
                .option3("6-10 months")
                .option4("12-15 months")
                .rightAnswer("6-10 months")
                .difficultylevel("Medium")
                .category("Baby")
                .build());

        // English Questions
        questions.add(Question.builder()
                .questionTitle("Which word is a synonym for 'happy'?")
                .option1("Sad")
                .option2("Joyful")
                .option3("Angry")
                .option4("Tired")
                .rightAnswer("Joyful")
                .difficultylevel("Easy")
                .category("English")
                .build());

        questions.add(Question.builder()
                .questionTitle("What is the past tense of 'go'?")
                .option1("Goed")
                .option2("Went")
                .option3("Gone")
                .option4("Going")
                .rightAnswer("Went")
                .difficultylevel("Easy")
                .category("English")
                .build());

        questions.add(Question.builder()
                .questionTitle("Identify the adjective: 'The quick brown fox jumps.'")
                .option1("quick")
                .option2("fox")
                .option3("jumps")
                .option4("brown")
                .rightAnswer("quick") // Could also be "brown" - let's pick one for simplicity
                .difficultylevel("Medium")
                .category("English")
                .build());

        // Java Questions
        questions.add(Question.builder()
                .questionTitle("What keyword is used to define a constant in Java?")
                .option1("const")
                .option2("static")
                .option3("final")
                .option4("let")
                .rightAnswer("final")
                .difficultylevel("Easy")
                .category("Java")
                .build());

        questions.add(Question.builder()
                .questionTitle("Which of these is NOT a primitive data type in Java?")
                .option1("int")
                .option2("String")
                .option3("boolean")
                .option4("char")
                .rightAnswer("String")
                .difficultylevel("Medium")
                .category("Java")
                .build());

        questions.add(Question.builder()
                .questionTitle("What is the main purpose of the 'static' keyword in Java?")
                .option1("To make a variable constant")
                .option2("To allow a method to be called without creating an object")
                .option3("To define a class that cannot be inherited")
                .option4("To handle exceptions")
                .rightAnswer("To allow a method to be called without creating an object")
                .difficultylevel("Hard")
                .category("Java")
                .build());

        // Spring Questions
        questions.add(Question.builder()
                .questionTitle("What annotation is used to mark a class as a Spring MVC controller?")
                .option1("@Service")
                .option2("@Component")
                .option3("@Controller")
                .option4("@Repository")
                .rightAnswer("@Controller")
                .difficultylevel("Easy")
                .category("Spring")
                .build());

        questions.add(Question.builder()
                .questionTitle("Which Spring module provides comprehensive transaction management support?")
                .option1("Spring Core")
                .option2("Spring AOP")
                .option3("Spring Web")
                .option4("Spring TX (Transaction)")
                .rightAnswer("Spring TX (Transaction)")
                .difficultylevel("Medium")
                .category("Spring")
                .build());

        questions.add(Question.builder()
                .questionTitle("What is Dependency Injection in Spring?")
                .option1("A way to manage database connections")
                .option2("A design pattern where objects receive other objects they depend on")
                .option3("A security feature")
                .option4("A templating engine")
                .rightAnswer("A design pattern where objects receive other objects they depend on")
                .difficultylevel("Medium")
                .category("Spring")
                .build());

        // Angular Questions
        questions.add(Question.builder()
                .questionTitle("What is the command to create a new Angular component using Angular CLI?")
                .option1("ng add component <name>")
                .option2("ng generate component <name>")
                .option3("ng new component <name>")
                .option4("ng make component <name>")
                .rightAnswer("ng generate component <name>")
                .difficultylevel("Easy")
                .category("Angular")
                .build());

        questions.add(Question.builder()
                .questionTitle("Which file is the main entry point for an Angular application?")
                .option1("app.component.ts")
                .option2("index.html")
                .option3("main.ts")
                .option4("angular.json")
                .rightAnswer("main.ts")
                .difficultylevel("Medium")
                .category("Angular")
                .build());

        questions.add(Question.builder()
                .questionTitle("What is data binding in Angular?")
                .option1("Connecting to a database")
                .option2("A way to synchronize data between the model and the view")
                .option3("A routing mechanism")
                .option4("A testing framework")
                .rightAnswer("A way to synchronize data between the model and the view")
                .difficultylevel("Medium")
                .category("Angular")
                .build());

        return questions;
    }

//    public static void main(String[] args) {
//        List<Question> allQuestions = generateQuestions();
//        for (Question q : allQuestions) {
//            System.out.println(q); // Relies on the @ToString from Lombok
//        }
//    }
}
