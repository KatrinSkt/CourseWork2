package pro.sky.CourseWork2.Services;

import org.springframework.stereotype.Service;
import pro.sky.CourseWork2.Exceptions.IncorrectAmountException;
import pro.sky.CourseWork2.model.Question;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class ExaminerServiceImpl implements ExaminerService {

    private final QuestionService questionService;

    public ExaminerServiceImpl(QuestionService questionService) {
        this.questionService = questionService;
    }


    @Override
    public Collection<Question> getQuestion(int amount) {
        if (questionService.getAll().size() < amount || amount <= 0) {
            throw new IncorrectAmountException();
        }
        Set<Question> questions = new HashSet<>();
        while (questions.size() < amount) {
            questions.add(questionService.getRandomQuestion());
        }
        return questions;
    }
}