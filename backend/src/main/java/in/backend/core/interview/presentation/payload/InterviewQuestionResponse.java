package in.backend.core.interview.presentation.payload;

import in.backend.core.interview.application.InterviewQuestionInfo;
import lombok.Builder;

@Builder
public record InterviewQuestionResponse(
        Long interviewId,
        Long interviewQuestionId,
        String question,
        int index,
        int remainTailQuestionCount
) {
    public static InterviewQuestionResponse from(InterviewQuestionInfo interviewQuestionInfo) {
        return InterviewQuestionResponse.builder()
                .interviewId(interviewQuestionInfo.interviewId())
                .interviewQuestionId(interviewQuestionInfo.interviewQuestionId())
                .question(interviewQuestionInfo.question())
                .index(interviewQuestionInfo.index())
                .remainTailQuestionCount(interviewQuestionInfo.remainTailQuestionCount())
                .build();
    }
}
