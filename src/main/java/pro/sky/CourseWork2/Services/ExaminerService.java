package pro.sky.CourseWork2.Services;

import org.springframework.stereotype.Service;
import pro.sky.CourseWork2.model.Question;

import java.util.Collection;

public interface ExaminerService {
    Collection<Question>getQuestion(int amount);
}
