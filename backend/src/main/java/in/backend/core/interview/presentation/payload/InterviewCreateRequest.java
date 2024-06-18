package in.backend.core.interview.presentation.payload;

import in.backend.core.interview.application.InterviewCreateCommand;
import jakarta.validation.constraints.NotNull;

public record InterviewCreateRequest(
        @NotNull Long questionSetId,
        @NotNull Integer tailQuestionDepth,
        @NotNull Integer totalProblemCount,
        @NotNull Integer timeToAnswer,
        @NotNull Integer timeToThink

) {

    public InterviewCreateCommand to() {
        return InterviewCreateCommand.builder()
                .tailQuestionDepth(tailQuestionDepth)
                .totalOfProblemCount(totalProblemCount)
                .timeToAnswer(timeToAnswer)
                .questionSetId(questionSetId)
                .timeToThink(timeToThink)
                .build();
    }
}
