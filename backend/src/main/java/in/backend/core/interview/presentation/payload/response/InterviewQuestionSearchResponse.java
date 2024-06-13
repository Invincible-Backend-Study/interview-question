package in.backend.core.interview.presentation.payload.response;

import in.backend.core.interview.entity.InterviewQuestionEntity;

public record InterviewQuestionSearchResponse(
        String content,
        int remainTailQuestionCount,
        Long interviewId,
        Long interviewQuestionId
) {
    public static InterviewQuestionSearchResponse from(InterviewQuestionEntity currentInterviewQuestion) {
        return new InterviewQuestionSearchResponse(
                currentInterviewQuestion.getQuestion(),
                currentInterviewQuestion.getRemainTailQuestionCount(),
                currentInterviewQuestion.getInterviewId(),
                currentInterviewQuestion.getId()
        );
    }
}
