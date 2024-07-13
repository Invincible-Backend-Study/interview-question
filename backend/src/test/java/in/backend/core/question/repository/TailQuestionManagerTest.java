package in.backend.core.question.repository;

import static org.assertj.core.api.Assertions.assertThat;

import in.backend.core.interview.application.InterviewSubmitCommand.AnswerInfo;
import in.backend.core.interview.application.InterviewSubmitCommand.FeedbackInfo;
import in.backend.core.question.application.TailQuestionSubmitCommand;
import in.backend.core.question.entity.AnswerState;
import in.backend.core.question.infrastrcuture.TailQuestionManager;
import in.backend.global.fixture.InterviewQuestionFixture;
import in.backend.global.layer.ImplementLayerTest;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;


class TailQuestionManagerTest extends ImplementLayerTest {

    @Autowired
    TailQuestionManager tailQuestionManager;

    @Test
    void 꼬리질문에_답변하면_다음_질문을_받습니다() {
        var interviewQuestion = interviewQuestionRepository.save(InterviewQuestionFixture.create(1L, 3));

        interviewQuestion.submit(
                AnswerState.COMPLETE,
                new AnswerInfo("", 1),
                new FeedbackInfo("대충 피드백", "1234", List.of(), 1)
        );

        interviewQuestionRepository.save(interviewQuestion);

        var tailQuestion = tailQuestionRepository.save(interviewQuestion.createTailQuestion()
                .orElseThrow(IllegalArgumentException::new));

        var tailQuestionSubmitResult = tailQuestionManager.submit(TailQuestionSubmitCommand.builder()
                .interviewQuestionId(tailQuestion.getInterviewQuestionId())
                .tailQuestionId(tailQuestion.getId())
                .answerState(AnswerState.COMPLETE)
                .answerInfo(new AnswerInfo("질문", 1))
                .feedbackInfo(new FeedbackInfo("피드백", "다음 질문", List.of(), 100))
                .build(), 1L);

        assertThat(tailQuestionSubmitResult.question()).isEqualTo("다음 질문");
    }

}