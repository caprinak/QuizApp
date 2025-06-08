package io.satori.quiz.controller;

import io.satori.quiz.dto.QuestionDto; // Use QuestionDto
import io.satori.quiz.model.Response;
import io.satori.quiz.service.QuizService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("quizzes")
public class QuizController {

    private final QuizService quizService;

    // Constructor injection
    public QuizController(QuizService quizService) {
        this.quizService = quizService;
    }

    @PostMapping("")
    public ResponseEntity<Map<String, Object>> createQuiz(@RequestParam String category, @RequestParam int numQ, @RequestParam String title) {
        Integer quizId = quizService.createQuiz(category, numQ, title);
        Map<String, Object> responseBody = Map.of(
                "message", "Quiz created successfully with ID: " + quizId,
                "quizId", quizId
        );
        return new ResponseEntity<>(responseBody, HttpStatus.CREATED);
    }

    @GetMapping("{id}")
    public ResponseEntity<List<QuestionDto>> getQuizQuestions(@PathVariable Integer id) {
        return ResponseEntity.ok(quizService.getQuizQuestions(id));
    }

    @PostMapping("{id}")
    public ResponseEntity<Integer> submitQuiz(@PathVariable Integer id, @RequestBody List<Response> responses) {
        return ResponseEntity.ok(quizService.calculateResult(id, responses));
    }
}