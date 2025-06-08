package io.satori.quiz.dto;

import lombok.Data;

@Data
public class QuestionDto {
    private Integer id;
    private String questionTitle;
    private String option1;
    private String option2;
    private String option3;
    private String option4;
    // No rightAnswer here, as this is for client display when getting a quiz
}