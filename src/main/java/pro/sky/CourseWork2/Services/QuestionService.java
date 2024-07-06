package pro.sky.CourseWork2.Services;

import org.springframework.stereotype.Service;
import pro.sky.CourseWork2.model.Question;

import java.util.Collection;


public interface QuestionService {

    public Question add(String question, String answer);

    public Question add(Question question);

    public Question remove(Question question);

    Collection<Question> getAll();

    Question getRandomQuestion();
}
