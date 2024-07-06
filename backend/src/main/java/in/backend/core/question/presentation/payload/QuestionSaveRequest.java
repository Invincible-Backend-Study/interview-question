package in.backend.core.question.presentation.payload;

import in.backend.global.entity.ActionType;

public record QuestionSaveRequest(
        ActionType action,
        Long questionId,
        Long questionSetId,
        Integer sequence,
        String question
) {
}
