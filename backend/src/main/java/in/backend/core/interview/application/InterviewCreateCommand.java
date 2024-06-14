package in.backend.core.interview.application;

import in.backend.core.interview.entity.InterviewSettings;
import in.backend.core.questionset.entity.QuestionSetRules;
import java.util.Objects;
import lombok.Builder;

@Builder
public record InterviewCreateCommand(
        Long questionSetId,

        Integer totalOfProblemCount,

        Integer tailQuestionDepth,

        Integer timeToThink,

        Integer timeToAnswer

) {
    private static <T> T getOrElse(T input, T orElse) {
        return Objects.isNull(input) ? orElse : input;
    }

    public InterviewSettings toInterviewSettings(QuestionSetRules questionSetRules) {
        return InterviewSettings.builder()
                .tailQuestionDepth(getOrElse(tailQuestionDepth, questionSetRules.getDefaultTailQuestionDepth()))
                .timeToThink(getOrElse(timeToThink, questionSetRules.getDefaultTimeToThink()))
                .timeToAnswer(getOrElse(timeToAnswer, questionSetRules.getDefaultTimeToAnswer()))
                .build();
    }
}
