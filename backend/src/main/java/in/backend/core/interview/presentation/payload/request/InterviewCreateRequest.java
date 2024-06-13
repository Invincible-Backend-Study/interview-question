package in.backend.core.interview.presentation.payload.request;


import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;

@Builder
public record InterviewCreateRequest(
        @Schema(description = "question-set id")
        @NotNull
        Long questionSetId,

        @Schema(description = "풀어볼 문제 수")
        @NotNull
        Integer totalOfProblemCount,

        @Schema(description = "꼬리 질문 depth", nullable = true)
        Integer tailQuestionDepth,

        @Schema(description = "문항 당 대기 시간(초)", nullable = true)
        Integer timeToThink,

        @Schema(description = "문항 당 답변에 걸리는 시간(초)", nullable = true)
        Integer timeToAnswer
) {
}
