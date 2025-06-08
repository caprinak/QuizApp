package io.satori.quiz.service;

import io.satori.quiz.dao.QuestionDao;
import io.satori.quiz.dao.QuizDao;
import io.satori.quiz.dto.QuestionDto; // Use QuestionDto
import io.satori.quiz.exception.ResourceNotFoundException;
import io.satori.quiz.mapper.QuestionMapper; // Import QuestionMapper
import io.satori.quiz.model.Question;
import io.satori.quiz.model.Quiz;
import io.satori.quiz.model.Response;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class QuizService {

    private final QuizDao quizDao;
    private final QuestionDao questionDao;
    private final QuestionMapper questionMapper; // Added QuestionMapper

    public QuizService(QuizDao quizDao, QuestionDao questionDao, QuestionMapper questionMapper) {
        this.quizDao = quizDao;
        this.questionDao = questionDao;
        this.questionMapper = questionMapper; // Initialize mapper
    }

    @Transactional
    public Integer createQuiz(String category, int numQ, String title) {
        List<Question> questions = questionDao.findRandomQuestionsByCategory(category, numQ);
        if (questions.isEmpty() && numQ > 0) {
            throw new ResourceNotFoundException("No questions found for category: " + category + " to create a quiz with " + numQ + " questions.");
        }
        Quiz quiz = new Quiz();
        quiz.setTitle(title);
        quiz.setQuestions(questions);
        Quiz savedQuiz = quizDao.save(quiz);
        return savedQuiz.getId();
    }

    public List<QuestionDto> getQuizQuestions(Integer id) {
        Quiz quiz = quizDao.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Quiz not found with id: " + id));
        // Use QuestionMapper to convert List<Question> to List<QuestionDto>
        return questionMapper.questionsToQuestionDtos(quiz.getQuestions());
    }

    public Integer calculateResult(Integer id, List<Response> responses) {
        Quiz quiz = quizDao.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Quiz not found with id: " + id));

        List<Question> questions = quiz.getQuestions();
        int right = 0;
        int i = 0;
        for (Response response : responses) {
            if (i >= questions.size()) {
                // Log this: more responses submitted than questions available
                break;
            }
            Question currentQuestion = questions.get(i);
            String userAnswerText = response.getResponse();

            if (userAnswerText == null) {
                i++;
                continue;
            }

            if (userAnswerText.equals(currentQuestion.getRightAnswer())) {
                right++;
            } else if (userAnswerText.startsWith("option")) {
                String actualRightAnswer = currentQuestion.getRightAnswer();
                String chosenOptionText = "";

                switch (userAnswerText) {
                    case "option1": chosenOptionText = currentQuestion.getOption1(); break;
                    case "option2": chosenOptionText = currentQuestion.getOption2(); break;
                    case "option3": chosenOptionText = currentQuestion.getOption3(); break;
                    case "option4": chosenOptionText = currentQuestion.getOption4(); break;
                }
                if (!chosenOptionText.isEmpty() && chosenOptionText.equals(actualRightAnswer)) {
                    right++;
                }
            }
            i++;
        }
        return right;
    }
}