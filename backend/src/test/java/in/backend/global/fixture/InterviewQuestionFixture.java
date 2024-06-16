package in.backend.global.fixture;

import in.backend.core.interview.entity.InterviewQuestionEntity;

public class InterviewQuestionFixture {

    public static InterviewQuestionEntity create(Long interviewId) {
        return InterviewQuestionEntity.builder()
                .memberId(1L)
                .interviewId(interviewId)
                .questionId(1L)
                .questionContent("테스트 질문")
                .remainTailQuestionCount(1)
                .build();
    }

    public static InterviewQuestionEntity create(Long interviewId, int remainTailQuestionCount) {
        return InterviewQuestionEntity.builder()
                .memberId(1L)
                .interviewId(interviewId)
                .questionId(1L)
                .questionContent("테스트 질문")
                .remainTailQuestionCount(remainTailQuestionCount)
                .build();
    }
}
