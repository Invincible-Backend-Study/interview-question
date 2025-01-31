package in.backend.global.layer;


import in.backend.core.auth.domain.Visitor;
import in.backend.core.auth.infrastrcutrue.RefreshTokenRepository;
import in.backend.core.interview.infrastructure.InterviewQuestionRepository;
import in.backend.core.interview.infrastructure.InterviewRepository;
import in.backend.core.member.infrastructure.MemberRepository;
import in.backend.core.question.infrastrcuture.QuestionRepository;
import in.backend.core.question.infrastrcuture.TailQuestionRepository;
import in.backend.core.questionset.infrastructure.QuestionSetRepository;
import in.backend.global.utils.DatabaseCleaner;
import jakarta.persistence.EntityManager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;


@SpringBootTest
@ActiveProfiles("test")
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
public class ImplementLayerTest {

    @Autowired
    protected MemberRepository memberRepository;

    @Autowired
    protected EntityManager entityManager;

    @Autowired
    protected QuestionSetRepository questionSetRepository;

    @Autowired
    protected QuestionRepository questionRepository;

    @Autowired
    protected InterviewRepository interviewRepository;

    @Autowired
    protected InterviewQuestionRepository interviewQuestionRepository;

    @Autowired
    protected TailQuestionRepository tailQuestionRepository;

    @Autowired
    protected RefreshTokenRepository refreshTokenRepository;


    @Autowired
    private DatabaseCleaner databaseCleaner;

    protected Long memberId() {
        return visitor().memberId();
    }

    protected Visitor visitor() {
        return Visitor.member(1L);
    }

    @AfterEach
    void teardown() {
        databaseCleaner.execute();
    }

}