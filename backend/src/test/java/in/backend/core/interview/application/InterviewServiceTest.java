package in.backend.core.interview.application;

import static org.assertj.core.api.Assertions.assertThat;

import in.backend.core.auth.domain.Visitor;
import in.backend.core.interview.repository.InterviewWriter;
import in.backend.global.fixture.QuestionFixture;
import in.backend.global.fixture.QuestionSetFixture;
import in.backend.global.layer.ImplementLayerTest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;


class InterviewServiceTest extends ImplementLayerTest {

    @Autowired
    InterviewService interviewService;

    @Autowired
    InterviewWriter interviewWriter;


    @Test
    void 인터뷰를_생성하고_현재_문제를_불러오면_첫_번째_질문을_가져옵니다() {
        var questionSetId = given(() -> {
            var questionSet = questionSetRepository.save(QuestionSetFixture.create());
            questionRepository.saveAll(QuestionFixture.creates(questionSet, 10));
            return questionSet.getId();
        });

        var interviewId = given(() -> interviewWriter.write(1L, InterviewCreateCommand.builder()
                .questionSetId(questionSetId)
                .totalOfProblemCount(5)
                .tailQuestionDepth(3)
                .build())
        );

        var interviewInfo = interviewService.loadByCurrentProblem(Visitor.member(1L), interviewId);

        assertThat(interviewInfo.index()).isEqualTo(0);
        assertThat(interviewInfo.remainTailQuestionCount()).isEqualTo(3);
    }


}