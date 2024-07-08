package in.backend.core.question.application;

import in.backend.global.entity.ActionType;
import lombok.Builder;

@Builder
public record QuestionSaveCommand(
        ActionType action,
        Long questionId,
        Long questionSetId,
        Integer sequence,
        String question
) {
}
