package in.backend.global.fixture;

import in.backend.core.questionset.domain.QuestionSetRules;

public class QuestionSetRulesFixture {

    public static QuestionSetRules create() {
        return QuestionSetRules.builder()
                .defaultTailQuestionDepth(3)
                .defaultTimeToAnswer(10)
                .defaultTimeToThink(10)
                .build();
    }
}
