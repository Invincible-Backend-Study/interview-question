package in.backend.core.question.presentation.payload;

import in.backend.core.interview.application.InterviewSubmitCommand.AnswerInfo;
import in.backend.core.interview.application.InterviewSubmitCommand.FeedbackInfo;
import in.backend.core.question.application.TailQuestionSubmitCommand;
import in.backend.core.question.entity.AnswerState;
import jakarta.validation.constraints.NotNull;
import java.util.List;
import lombok.Builder;

@Builder
public record TailQuestionSubmitRequest(
        @NotNull Long interviewQuestionId,
        @NotNull Long tailQuestionId,
        @NotNull String answerState,
        @NotNull String aiFeedback,
        @NotNull String tailQuestion,
        @NotNull Integer timeToAnswer,
        @NotNull String answerContent,
        @NotNull Integer score,
        List<String> referenceLinks

) {
    public TailQuestionSubmitCommand to() {
        var feedback = FeedbackInfo.builder()
                .aiFeedback(aiFeedback)
                .tailQuestion(tailQuestion)
                .score(score)
                .referenceLinks(referenceLinks)
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
