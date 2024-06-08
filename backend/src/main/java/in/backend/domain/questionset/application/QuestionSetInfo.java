package in.backend.domain.questionset.application;

import in.backend.domain.questionset.entity.QuestionSetEntity;
import in.backend.domain.questionset.entity.QuestionSetRules;

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
