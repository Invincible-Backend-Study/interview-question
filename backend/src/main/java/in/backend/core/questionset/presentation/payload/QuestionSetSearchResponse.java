package in.backend.core.questionset.presentation.payload;

import in.backend.core.questionset.application.QuestionSetInfo;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;


@Builder
@Schema(description = "question set", name = "question set 조회 결과")
public record QuestionSetSearchResponse(
        Long questionSetId,
        String description,
        String title,
        Long count,
        Integer tailQuestionDepth,
        String thumbnailUrl

) {
    public static QuestionSetSearchResponse from(QuestionSetInfo questionSet) {
        return QuestionSetSearchResponse.builder()
                .questionSetId(questionSet.questionSetId())
                .title(questionSet.title())
                .description(questionSet.description())
                .count(questionSet.count())
                .tailQuestionDepth(questionSet.tailQuestionDepth())
                .thumbnailUrl(questionSet.thumbnailUrl())
                .build();
    }
}
