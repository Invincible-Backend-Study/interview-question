package in.backend.core.question.presentation.payload;

import in.backend.core.question.entity.QuestionEntity;
import lombok.Builder;

@Builder
public record QuestionAdminDetailResponse(
        Long questionId,
        Long questionSetId,
        String question,
        Integer sequence
) {
    public static QuestionAdminDetailResponse from(QuestionEntity question) {
        return QuestionAdminDetailResponse.builder()
                .questionId(question.getId())
                .questionSetId(question.getQuestionSetId())
                .question(question.getContent())
                .sequence(question.getSequence())
                .build();
    }
}
