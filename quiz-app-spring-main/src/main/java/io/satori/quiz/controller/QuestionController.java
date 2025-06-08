package io.satori.quiz.controller;

import io.satori.quiz.dto.QuestionCreationRequestDto;
import io.satori.quiz.dto.QuestionDto;
import io.satori.quiz.service.QuestionService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("questions")
public class QuestionController {

    private final QuestionService questionService;

    // Constructor injection
    public QuestionController(QuestionService questionService) {
        this.questionService = questionService;
    }

    @GetMapping("")
    public ResponseEntity<List<QuestionDto>> getAllQuestions() {
        return ResponseEntity.ok(questionService.getAllQuestions());
    }

    @GetMapping("category/{category}")
    public ResponseEntity<List<QuestionDto>> getQuestionsByCategory(@PathVariable String category) {
        return ResponseEntity.ok(questionService.getQuestionsByCategory(category));
    }

    @PostMapping("")
    public ResponseEntity<QuestionDto> addQuestion(@RequestBody QuestionCreationRequestDto questionDto) {
        // The service now returns a DTO, so we can directly use it.
        QuestionDto createdQuestion = questionService.addQuestion(questionDto);
        return new ResponseEntity<>(createdQuestion, HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteQuestion(@PathVariable Integer id) {
        questionService.deleteQuestion(id);
        return ResponseEntity.noContent().build(); // Standard for successful deletion with no body
    }
}