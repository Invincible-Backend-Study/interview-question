package in.backend.core.question.presentation.payload;

public record QuestionDetailResponse(
        Long questionId,
        Long questionSetId,
        String question,
        Integer sequence
) {
}
