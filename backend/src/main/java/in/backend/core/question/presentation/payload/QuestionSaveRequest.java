package in.backend.core.question.presentation.payload;

import in.backend.core.question.application.QuestionSaveCommand;
import in.backend.global.entity.ActionType;

public record QuestionSaveRequest(
        String action,
        Long questionId,
        Long questionSetId,
        Integer sequence,
        String question
) {

    public QuestionSaveCommand toDto() {
        return QuestionSaveCommand.builder()
                .action(ActionType.valueOf(action))
                .questionId(questionId)
                .questionSetId(questionSetId)
                .sequence(sequence)
                .question(question)
                .build();
    }
}
