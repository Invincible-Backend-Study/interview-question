package in.backend.core.questionset.presentation.payload;

import in.backend.core.questionset.entity.QuestionSetEntity;
import lombok.Builder;


@Builder
public record QuestionSetDetailResponse(
        Long questionSetId,
        String title,
        String description,
        String thumbnailUrl,
        Integer defaultTailQuestionDepth
) {

    public static QuestionSetDetailResponse from(QuestionSetEntity questionSet) {
        return QuestionSetDetailResponse.builder()
                .questionSetId(questionSet.getId())
                .title(questionSet.getTitle())
                .description(questionSet.getDescription())
                .thumbnailUrl(questionSet.getThumbnailUrl())
                .defaultTailQuestionDepth(questionSet.getTailQuestionDepth())
                .build();
    }
}
