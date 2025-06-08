package io.satori.quiz.service;

import io.satori.quiz.dao.QuestionDao;
import io.satori.quiz.dto.QuestionCreationRequestDto;
import io.satori.quiz.dto.QuestionDto;
import io.satori.quiz.exception.QuestionAlreadyExistsException;
import io.satori.quiz.exception.ResourceNotFoundException;
import io.satori.quiz.mapper.QuestionMapper;
import io.satori.quiz.model.Question;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class QuestionService {
    private final QuestionDao questionDao;
    private final QuestionMapper questionMapper;

    // Constructor injection is preferred
    public QuestionService(QuestionDao questionDao, QuestionMapper questionMapper) {
        this.questionDao = questionDao;
        this.questionMapper = questionMapper;
    }

    public List<QuestionDto> getAllQuestions() {
        // For getAllQuestions, you might want a DTO that includes the right answer
        // if this is for an admin view. For now, using QuestionDto.
        // If QuestionDto should not have rightAnswer, create a different DTO for this.
        // For simplicity, let's assume QuestionDto is fine for now, or create AdminQuestionDto.
        // Let's map to QuestionDto which hides the right answer by default.
        // If you need all details including right answer, create a specific DTO for that.
        List<Question> questions = questionDao.findAll();
        return questionMapper.questionsToQuestionDtos(questions); // This will use the mapping that omits rightAnswer
    }

    public List<QuestionDto> getQuestionsByCategory(String category) {
        List<Question> questions = questionDao.findByCategory(category);
        if (questions.isEmpty()) {
            // Optionally throw ResourceNotFoundException if category is valid but has no questions
            // or return an empty list as is.
        }
        return questionMapper.questionsToQuestionDtos(questions);
    }

    @Transactional
    public QuestionDto addQuestion(QuestionCreationRequestDto questionRequestDto) {
        if (questionDao.existsByQuestionTitle(questionRequestDto.getQuestionTitle())) {
            throw new QuestionAlreadyExistsException("Question with title '" + questionRequestDto.getQuestionTitle() + "' already exists.");
        }
        Question question = questionMapper.questionCreationRequestDtoToQuestion(questionRequestDto);
        Question savedQuestion = questionDao.save(question);
        // Return a DTO that represents the created question, potentially without the right answer
        // if QuestionDto is designed that way, or a more complete DTO if needed.
        return questionMapper.questionToQuestionDto(savedQuestion);
    }

    @Transactional
    public void deleteQuestion(Integer id) {
        if (!questionDao.existsById(id)) {
            throw new ResourceNotFoundException("Question not found with id: " + id + " for deletion.");
        }
        questionDao.deleteById(id);
    }
}