package io.satori.quiz.service;

import io.satori.quiz.model.Question;
import io.satori.quiz.dao.QuestionDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class QuestionService {
    QuestionDao questionDao;
    List<Question> allQuestions;
    public QuestionService(QuestionDao questionDao) {
        this.questionDao = questionDao;
        allQuestions = questionDao.findAll();
    }


    public ResponseEntity<List<Question>> getAllQuestions() {
        try {
            return new ResponseEntity<>(allQuestions, HttpStatus.OK);
        }catch (Exception e){
            e.printStackTrace();
        }
        return new ResponseEntity<>(new ArrayList<>(), HttpStatus.BAD_REQUEST);
    }

    public ResponseEntity<List<Question>> getQuestionsByCategory(String category) {
        try {
            return new ResponseEntity<>(questionDao.findByCategory(category),HttpStatus.OK);
        }catch (Exception e){
            e.printStackTrace();
        }
        return new ResponseEntity<>(new ArrayList<>(), HttpStatus.BAD_REQUEST);

    }

    public ResponseEntity<String> addQuestion(Question question) {
       boolean exists = allQuestions.stream().anyMatch(q -> q.getQuestionTitle().equals(question.getQuestionTitle()));
       if(exists){
         return new ResponseEntity<>("questions already exist",HttpStatus.CONFLICT)  ;
       }
        questionDao.save(question);
        return new ResponseEntity<>("success",HttpStatus.CREATED);
    }

    public ResponseEntity<String> deleteQuestion(Integer id) {
        questionDao.deleteById(id);
        return new ResponseEntity<>("deleted",HttpStatus.NO_CONTENT);
    }
}
