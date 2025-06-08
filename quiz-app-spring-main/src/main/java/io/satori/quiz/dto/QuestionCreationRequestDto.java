package io.satori.quiz.dto;

import lombok.Data;

@Data
public class QuestionCreationRequestDto {
    private String questionTitle;
    private String option1;
    private String option2;
    private String option3;
    private String option4;
    private String rightAnswer;
    private String difficultylevel; // Ensure consistent naming with your Question entity
    private String category;
}