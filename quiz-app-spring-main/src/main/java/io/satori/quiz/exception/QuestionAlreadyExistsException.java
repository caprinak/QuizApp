package io.satori.quiz.exception;

public class QuestionAlreadyExistsException extends RuntimeException {
    public QuestionAlreadyExistsException(String message) {
        super(message);
    }
}