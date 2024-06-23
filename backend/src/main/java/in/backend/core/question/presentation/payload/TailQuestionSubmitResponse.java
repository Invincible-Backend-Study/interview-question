package in.backend.core.question.presentation.payload;

import in.backend.core.question.application.TailQuestionSubmitResult;
import lombok.Builder;

@Builder
public record TailQuestionSubmitResponse(
        Long interviewQuestionId,
        Long tailQuestionId,
        String question
) {

    public static TailQuestionSubmitResponse from(TailQuestionSubmitResult result) {
        return TailQuestionSubmitResponse.builder()
                .interviewQuestionId(result.interviewQuestionId())
                .tailQuestionId(result.tailQuestionId())
                .question(result.question())
                .build();
    }
}
