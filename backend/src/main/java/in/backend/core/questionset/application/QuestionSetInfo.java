package in.backend.core.questionset.application;

import in.backend.core.questionset.domain.QuestionSetEntity;
import in.backend.core.questionset.domain.QuestionSetRules;

public record QuestionSetInfo(
        String title,
        Long adminId,
        Integer defaultTailQuestionDepth,
        Integer defaultTimeToThink,
        Integer defaultTimeToAnswer
) {
    public QuestionSetEntity toEntity() {
        var questionSetRules = QuestionSetRules.builder()
                .defaultTimeToAnswer(defaultTimeToAnswer)
                .defaultTailQuestionDepth(defaultTailQuestionDepth)
                .defaultTimeToThink(defaultTimeToThink)
                .build();

        return QuestionSetEntity.builder()
                .adminId(adminId)
                .title(title)
                .questionSetRules(questionSetRules)
                .build();
    }
}
