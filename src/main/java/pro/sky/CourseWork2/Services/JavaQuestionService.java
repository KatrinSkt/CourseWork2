package pro.sky.CourseWork2.Services;

import org.springframework.stereotype.Service;
import pro.sky.CourseWork2.Exceptions.QuestionAlreadyAddedExceptions;
import pro.sky.CourseWork2.Exceptions.QuestionNotFoundException;
import pro.sky.CourseWork2.Exceptions.QuestionsAreEmptyException;
import pro.sky.CourseWork2.model.Question;

import java.util.*;
import java.util.concurrent.ThreadLocalRandom;

@Service
public class JavaQuestionService implements QuestionService {

    private final Set<Question> questions = new HashSet<>();

    @Override
    public Question add(String question, String answer) {
        return add(new Question(question, answer));
    }

    @Override
    public Question add(Question question) {
        if (!questions.add(question)) {
            throw new QuestionAlreadyAddedExceptions();
        }
        return question;
    }

    @Override
    public Question remove(Question question) {
        if (!questions.remove(question)) {
            throw new QuestionNotFoundException();
        }
        return question;
    }

    @Override
    public Collection<Question> getAll() {
        return Collections.unmodifiableCollection(questions);
    }

    @Override
    public Question getRandomQuestion() {
        if (questions.isEmpty()){
            throw new QuestionsAreEmptyException();
        }
        int randomQuestion = ThreadLocalRandom.current().nextInt(questions.size());
        return questions.stream()
                .skip(randomQuestion)
                .findFirst()
                .orElseThrow(QuestionNotFoundException::new);
    }
}

