package in.backend.core.tailquestion.presentation.payload.request;


public record TailQuestionSubmitRequest(
        Long interviewQuestionId,
        Long tailQuestionId,
        String feedback,
        String tailQuestionContent,
        Long score
) {
}
