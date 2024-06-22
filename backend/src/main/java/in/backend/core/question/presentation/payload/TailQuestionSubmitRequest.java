package in.backend.core.question.presentation.payload;

import in.backend.core.interview.application.InterviewSubmitCommand.AnswerInfo;
import in.backend.core.interview.application.InterviewSubmitCommand.FeedbackInfo;
import in.backend.core.question.application.TailQuestionSubmitCommand;
import in.backend.core.question.entity.AnswerState;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;

@Builder
public record TailQuestionSubmitRequest(
        @NotNull Long interviewQuestionId,
        @NotNull Long tailQuestionId,
        @NotNull String answerState,
        @NotNull String aiFeedback,
        @NotNull String tailQuestion,
        @NotNull String originalContent,
        @NotNull Integer timeToAnswer,
        @NotNull String answerContent

) {
    public TailQuestionSubmitCommand to() {

        var feedback = FeedbackInfo.builder()
                .aiFeedback(aiFeedback)
                .originalContent(originalContent)
                .tailQuestion(tailQuestion)
                .build();

        var answer = AnswerInfo.builder()
                .timeToAnswer(timeToAnswer)
                .content(answerContent)
                .build();

        return TailQuestionSubmitCommand.builder()
                .interviewQuestionId(interviewQuestionId)
                .tailQuestionId(tailQuestionId)
                .answerState(AnswerState.valueOf(answerState))
                .feedbackInfo(feedback)
                .answerInfo(answer)
                .build();
    }
}
