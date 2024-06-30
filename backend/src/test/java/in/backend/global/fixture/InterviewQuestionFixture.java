package in.backend.global.fixture;

import in.backend.core.interview.entity.InterviewEntity;
import in.backend.core.interview.entity.InterviewQuestionEntity;
import java.util.List;
import java.util.stream.Stream;

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

    public static InterviewQuestionEntity create(String questionContent, int remainTailQuestionCount) {
        return InterviewQuestionEntity.builder()
                .memberId(1L)
                .questionId(1L)
                .interviewId(1L)
                .questionContent(questionContent)
                .remainTailQuestionCount(remainTailQuestionCount)
                .build();
    }

    public static InterviewQuestionEntity create(String questionContent) {
        return InterviewQuestionEntity.builder()
                .memberId(1L)
                .questionId(1L)
                .interviewId(1L)
                .questionContent(questionContent)
                .remainTailQuestionCount(1)
                .build();
    }

    public static InterviewQuestionEntity create() {
        return InterviewQuestionFixture.create("질문");
    }

    public static InterviewQuestionEntity create(int remainTailQuestionCount) {
        return create(1L, remainTailQuestionCount);
    }

    public static List<InterviewQuestionEntity> creates(InterviewEntity interviewEntity, int n) {
        return Stream.generate(() -> create(interviewEntity.getId()))
                .limit(n)
                .toList();
    }
}
