package in.backend.core.questionset.presentation.payload;

import in.backend.core.questionset.entity.QuestionSetEntity;
import lombok.Builder;


@Builder
public record QuestionSetSearchResponse(
        Long id,
        String title
) {

    public static QuestionSetSearchResponse from(QuestionSetEntity questionSet) {
        return QuestionSetSearchResponse.builder()
                .id(questionSet.getId())
                .title(questionSet.getTitle())
                .build();
    }
}
