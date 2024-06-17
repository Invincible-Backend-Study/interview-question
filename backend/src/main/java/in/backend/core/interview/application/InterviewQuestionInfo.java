package in.backend.core.interview.application;

import lombok.Builder;

@Builder
public record InterviewQuestionInfo(
        Long interviewId,
        Long interviewQuestionId,
        String question,
        int index,
        int remainTailQuestionCount
) {
}
