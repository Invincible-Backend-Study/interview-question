package in.backend.core.interview.repository;


import static org.assertj.core.api.Assertions.assertThat;

import in.backend.core.interview.application.InterviewSubmitCommand;
import in.backend.core.interview.application.InterviewSubmitCommand.AnswerInfo;
import in.backend.core.interview.application.InterviewSubmitCommand.FeedbackInfo;
import in.backend.core.question.entity.AnswerState;
import in.backend.global.fixture.InterviewFixture;
import in.backend.global.fixture.InterviewQuestionFixture;
import in.backend.global.layer.ImplementLayerTest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

class InterviewManagerTest extends ImplementLayerTest {

    @Autowired
    InterviewManager interviewManager;

    @Test
    void 질문을_제출을_제출했을_때_꼬리질문_제출횟수가_남아있으면_꼬리질문을_만들어냅니다() {

        var tailQuestionCount = 1;
        var interview = interviewRepository.save(InterviewFixture.create());
        var interviewQuestion = interviewQuestionRepository.save(
                InterviewQuestionFixture.create(interview.getId(), tailQuestionCount)
        );

        var result = interviewManager.submit(memberId(), InterviewSubmitCommand.builder()
                .answerState(AnswerState.COMPLETE)
                .answer(new AnswerInfo("대답", 1))
                .feedback(new FeedbackInfo("피드백", "꼬리질문", "원본", 100))
                .currentIndex(interview.getIndex())
                .interviewId(interviewQuestion.getInterviewId())
                .interviewQuestionId(interviewQuestion.getId())
                .build()
        );

        var tailQuestion = tailQuestionRepository.findById(result.tailQuestionId())
                .orElseThrow(IllegalArgumentException::new);

        assertThat(interview.getIndex()).isEqualTo(1);
        assertThat(tailQuestionRepository.count()).isEqualTo(1);
        assertThat(tailQuestion.getQuestion()).isEqualTo("꼬리질문");


    }

    @Test
    void 질문_제출이후_꼬리질문_횟수가_남아_있지_않으면_꼬리질문을_생성하지_않습니다() {
        var tailQuestionCount = 0;
        var interview = interviewRepository.save(InterviewFixture.create(0));
        var interviewQuestion = interviewQuestionRepository.save(InterviewQuestionFixture.create(
                interview.getId(),
                tailQuestionCount
        ));
        interviewManager.submit(memberId(), InterviewSubmitCommand.builder()
                .answerState(AnswerState.COMPLETE)
                .answer(new AnswerInfo("대답", 1))
                .feedback(new FeedbackInfo("피드백", "꼬리질문", "원본", 100))
                .currentIndex(0)
                .interviewId(interview.getId())
                .interviewQuestionId(interviewQuestion.getId())
                .build()
        );

        assertThat(tailQuestionRepository.count()).isEqualTo(0);
    }

}