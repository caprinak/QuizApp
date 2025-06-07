package io.satori.quiz.service;

import io.satori.quiz.dao.QuestionDao;
import io.satori.quiz.dao.QuizDao;
import io.satori.quiz.model.Question;
import io.satori.quiz.model.QuestionWrapper;
import io.satori.quiz.model.Quiz;
import io.satori.quiz.model.Response;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class QuizService {


    QuizDao quizDao;

    QuestionDao questionDao;


    public QuizService(QuizDao quizDao, QuestionDao questionDao) {
        this.quizDao = quizDao;
        this.questionDao = questionDao;
    }

    public ResponseEntity<String> createQuiz(String category, int numQ, String title) {

        List<Question> questions = questionDao.findRandomQuestionsByCategory(category, numQ);

        Quiz quiz = new Quiz();
        quiz.setTitle(title);
        quiz.setQuestions(questions);
        quizDao.save(quiz);

        return new ResponseEntity<>("Success", HttpStatus.CREATED);

    }

    public ResponseEntity<List<QuestionWrapper>> getQuizQuestions(Integer id) {
        Optional<Quiz> quiz = quizDao.findById(id);
        List<Question> questionsFromDB = quiz.isPresent()? quiz.get().getQuestions() : new ArrayList<>();
        List<QuestionWrapper> questionsForUser = new ArrayList<>();
        for(Question q : questionsFromDB){
            QuestionWrapper qw = new QuestionWrapper(q.getId(), q.getQuestionTitle(), q.getOption1(), q.getOption2(), q.getOption3(), q.getOption4());
            questionsForUser.add(qw);
        }

        return new ResponseEntity<>(questionsForUser, HttpStatus.OK);

    }

    public ResponseEntity<Integer> calculateResult(Integer id, List<Response> responses) {
        Optional<Quiz> optionalQuiz = quizDao.findById(id);
        if(optionalQuiz.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        Quiz quiz = optionalQuiz.get();

        List<Question> questions = quiz.getQuestions();
        int right = 0;
        int i = 0;
        for(Response response : responses){
//            // Boundary check: ensure we don't try to access a question that doesn't exist
//            if (i >= questions.size()) {
//                // Optionally log this: more responses submitted than questions available for this quiz
//                break;
//            }
            Question currentQuestion = questions.get(i);
            String userAnswerText = response.getResponse(); // Get response text once per iteration

            // It's good practice to handle potential null responses
            if (userAnswerText == null) {
                i++; // Still increment to keep response and question indices aligned if processing continues
                continue;
            }

            if(userAnswerText.equals(currentQuestion.getRightAnswer())) {
                right++;
            } else if(userAnswerText.startsWith("option")) { // Using startsWith is slightly more precise than contains
                String actualRightAnswer = currentQuestion.getRightAnswer();
                String chosenOptionText = ""; // Text of the option selected by the user via "optionX" key

                switch (userAnswerText) { // Use the fetched userAnswerText
                    case "option1":
                        chosenOptionText = currentQuestion.getOption1();
                        break;
                    case "option2":
                        chosenOptionText = currentQuestion.getOption2();
                        break;
                    case "option3":
                        chosenOptionText = currentQuestion.getOption3();
                        break;
                    case "option4":
                        chosenOptionText = currentQuestion.getOption4();
                        break;
                    // default: // Consider if you need to handle cases like "option5" or malformed keys
                }

                // Check if a valid option was resolved and if it matches the right answer
                if(!chosenOptionText.isEmpty() && chosenOptionText.equals(actualRightAnswer)){
                    right++;
                }
            }
            i++;
        }
        return new ResponseEntity<>(right, HttpStatus.OK);
    }
}
