package in.backend.core.question.application;

import in.backend.global.entity.ActionType;

public record QuestionSaveCommand(
        ActionType action,
        Long questionId,
        Long questionSetId,
        Integer sequence,
        String question
) {
}
