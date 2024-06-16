package in.backend.core.interview.application;

import in.backend.core.question.entity.AnswerState;
import lombok.Builder;

@Builder
public record InterviewSubmitCommand(
        long interviewId,
        long interviewQuestionId,
        int currentIndex,

        AnswerState answerState,
        FeedbackInfo feedback,
        AnswerInfo answer

) {

    public record AnswerInfo(
            String content,
            int timeToAnswer
    ) {
    }

    public record FeedbackInfo(
            String aiFeedback,
            String tailQuestion,
            String originalContent,
            int score
    ) {
    }
}
