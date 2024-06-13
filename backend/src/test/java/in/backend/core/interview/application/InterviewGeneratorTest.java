package in.backend.core.interview.application;

import static org.assertj.core.api.Assertions.assertThat;

import in.backend.core.interview.presentation.payload.request.InterviewCreateRequest;
import in.backend.core.interview.repository.InterviewQuestionRepository;
import in.backend.core.interview.repository.InterviewRepository;
import in.backend.core.question.repository.QuestionRepository;
import in.backend.core.questionset.repository.QuestionSetRepository;
import in.backend.global.fixture.QuestionEntityFixture;
import in.backend.global.fixture.QuestionSetFixture;
import in.backend.global.layer.ServiceTest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;


class InterviewGeneratorTest extends ServiceTest {
    @Autowired
    private InterviewGenerator interviewGenerator;
    @Autowired
    private QuestionRepository questionRepository;
    @Autowired
    private InterviewQuestionRepository interviewQuestionRepository;
    @Autowired
    private InterviewRepository interviewRepository;
    @Autowired
    private QuestionSetRepository questionSetRepository;

    @Test
    void 인터뷰를_생성하면_인터뷰_항목을_같이_만듭니다() {

        var questionSet = questionSetRepository.save(QuestionSetFixture.create());
        questionRepository.saveAll(QuestionEntityFixture.create(questionSet, 10));

        var interviewCreateRequest = InterviewCreateRequest.builder()
                .questionSetId(questionSet.getId())
                .totalOfProblemCount(10)
                .tailQuestionDepth(3)
                .build();

        interviewGenerator.generate(1L, interviewCreateRequest);

        assertThat(interviewRepository.count()).isEqualTo(1);
        assertThat(interviewQuestionRepository.count()).isEqualTo(10);
    }

}