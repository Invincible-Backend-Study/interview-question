package in.backend.core.interview.application;

import in.backend.core.interview.entity.InterviewEntity;
import in.backend.core.interview.entity.InterviewQuestionEntity;
import lombok.Builder;

@Builder
public record InterviewQuestionInfo(
        Long interviewId,
        Long interviewQuestionId,
        String question,
        int size,
        int index,
        int remainTailQuestionCount
) {
    public static InterviewQuestionInfo from(
            InterviewEntity interview,
            InterviewQuestionEntity interviewQuestion
    ) {

        return InterviewQuestionInfo.builder()
                .interviewId(interview.getId())
                .interviewQuestionId(interviewQuestion.getId())
                .question(interviewQuestion.getQuestionContent())
                .remainTailQuestionCount(interviewQuestion.getRemainTailQuestionCount())
                .size(interview.getSize())
                .index(interview.getIndex())
                .build();
    }
}
