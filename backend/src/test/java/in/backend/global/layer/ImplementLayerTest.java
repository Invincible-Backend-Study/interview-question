package in.backend.global.layer;


import in.backend.core.interview.repository.InterviewQuestionRepository;
import in.backend.core.interview.repository.InterviewRepository;
import in.backend.core.question.repository.QuestionRepository;
import in.backend.core.questionset.repository.QuestionSetRepository;
import jakarta.persistence.EntityManager;
import java.util.function.Supplier;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;


@Transactional
@SpringBootTest
@ActiveProfiles("test")
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
public class ImplementLayerTest {


    @Autowired
    protected QuestionSetRepository questionSetRepository;

    @Autowired
    protected QuestionRepository questionRepository;


    @Autowired
    protected InterviewRepository interviewRepository;

    @Autowired
    protected InterviewQuestionRepository interviewQuestionRepository;

    @Autowired
    protected EntityManager entityManager;

    protected void given(Runnable runnable) {
        runnable.run();
        entityManager.clear();
    }

    protected <T> T given(Supplier<T> supplier) {
        var value = supplier.get();

        entityManager.clear();

        return value;
    }

}