package in.backend.core.questionset.presentation.payload;

import in.backend.core.questionset.entity.QuestionSetEntity;

public record QuestionSetCreateResponse(
        Long questionSetId,
        String thumbnailUrl
) {

    public static QuestionSetCreateResponse from(QuestionSetEntity questionSet) {
        return new QuestionSetCreateResponse(
                questionSet.getId(),
                questionSet.getThumbnailUrl()
        );
    }
}
