package pro.sky.CourseWork2;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import pro.sky.CourseWork2.Exceptions.IncorrectAmountException;
import pro.sky.CourseWork2.Services.ExaminerServiceImpl;
import pro.sky.CourseWork2.Services.QuestionService;
import pro.sky.CourseWork2.model.Question;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ExaminerServiceImplTest {
    @Mock
    private QuestionService questionService;

    @InjectMocks
    private ExaminerServiceImpl examinerService;
    private final List<Question> questions = List.of(
            new Question("Вопрос 1", "Ответ 1"),
            new Question("Вопрос 2", "Ответ 2"),
            new Question("Вопрос 3", "Ответ 3"),
            new Question("Вопрос 4", "Ответ 4"),
            new Question("Вопрос 5", "Ответ 5"));

    @BeforeEach
    public void beforeEach() {
        when(questionService.getAll()).thenReturn(questions);
    }

    @ParameterizedTest
    @ValueSource(ints = {6, 0, -4})
    public void getQuestionNegativeTest(int amount) {
        assertThatExceptionOfType(IncorrectAmountException.class)
                .isThrownBy(() -> examinerService.getQuestion(amount));
    }

    @Test
    public void getQuestionTest() {
        when(questionService.getRandomQuestion()).thenReturn(
                new Question("Вопрос 3", "Ответ 3"),
                new Question("Вопрос 3", "Ответ 3"),
                new Question("Вопрос 2", "Ответ 2"),
                new Question("Вопрос 1", "Ответ 1"),
                new Question("Вопрос 5", "Ответ 5"),
                new Question("Вопрос 5", "Ответ 5")
        );
        assertThat(examinerService.getQuestion(4)).containsExactlyInAnyOrder(
                new Question("Вопрос 1", "Ответ 1"),
                new Question("Вопрос 2", "Ответ 2"),
                new Question("Вопрос 3", "Ответ 3"),
                new Question("Вопрос 5", "Ответ 5"));
    }
}
