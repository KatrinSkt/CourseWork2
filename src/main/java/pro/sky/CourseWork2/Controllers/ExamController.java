package pro.sky.CourseWork2.Controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import pro.sky.CourseWork2.Services.ExaminerService;
import pro.sky.CourseWork2.model.Question;

import java.util.Collection;

@RestController
public class ExamController {
    private final ExaminerService examinerService;

    public ExamController(ExaminerService examinerService) {
        this.examinerService = examinerService;
    }

    @GetMapping("/exam/get/{amount}")
    public Collection<Question> getQuestion(@PathVariable int amount) {
        return examinerService.getQuestion(amount);
    }
}
