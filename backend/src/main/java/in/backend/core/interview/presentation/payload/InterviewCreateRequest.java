package in.backend.core.interview.presentation.payload;

import in.backend.core.interview.application.InterviewCreateCommand;

public record InterviewCreateRequest(
        Long questionSetId,
        Integer tailQuestionDepth,
        Integer totalProblemCount,
        Integer timeToAnswer,
        Integer timeToThink

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
