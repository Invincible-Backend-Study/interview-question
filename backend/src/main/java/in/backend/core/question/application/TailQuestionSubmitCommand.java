package in.backend.core.question.application;

import in.backend.core.interview.application.InterviewSubmitCommand.AnswerInfo;
import in.backend.core.interview.application.InterviewSubmitCommand.FeedbackInfo;
import in.backend.core.question.entity.AnswerState;
import lombok.Builder;

@Builder
public record TailQuestionSubmitCommand(
        Long interviewQuestionId,
        Long tailQuestionId,

        AnswerState answerState,
        AnswerInfo answerInfo,
        FeedbackInfo feedbackInfo
) {
}
