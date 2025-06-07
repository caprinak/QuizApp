package io.satori.quiz.bootstrap;

import io.satori.quiz.dao.QuestionDao;
import io.satori.quiz.model.Question;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.util.List;

import static io.satori.quiz.bootstrap.QuestionGenerator.generateQuestions;

@Component
@Profile("!prod")
public class Bootstrap implements CommandLineRunner {
    @Autowired
    QuestionDao questionDao;
    @Override
    public void run(String... args) throws Exception {
        if(questionDao.count() == 0){
            List<Question> allQuestions = generateQuestions();
            questionDao.saveAll(allQuestions);
            System.out.println("Bootstrap: Loaded " + allQuestions.size() + " questions into the database.");
        }else {
            System.out.println("Boots Questions already exist in the database. Skipping data loading.");

        }

    }
}
