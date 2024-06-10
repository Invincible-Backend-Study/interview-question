package in.backend.core.questionset.application;

import in.backend.core.questionset.entity.QuestionSetEntity;
import in.backend.core.questionset.entity.QuestionSetRules;

public record QuestionSetInfo(
        String title,
        Long adminId,
        Long defaultTailQuestionDepth,
        Long defaultTimeToThink,
        Long defaultTimeToAnswer
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
