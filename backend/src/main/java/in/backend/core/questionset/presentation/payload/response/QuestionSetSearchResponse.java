package in.backend.core.questionset.presentation.payload.response;

import io.swagger.v3.oas.annotations.media.Schema;

public record QuestionSetSearchResponse(

        @Schema(description = "question set id")
        Long id,

        @Schema(description = "question set 이름")
        String title,

        @Schema(nullable = true, description = "썸네일 없을 수 있음")
        String thumbnail
) {
}
