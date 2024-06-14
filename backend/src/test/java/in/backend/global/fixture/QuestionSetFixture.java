package in.backend.global.fixture;

import in.backend.core.questionset.entity.QuestionSetEntity;
import in.backend.core.questionset.entity.QuestionSetRules;

public class QuestionSetFixture {

    public static QuestionSetEntity create() {
        return QuestionSetEntity.builder()
                .adminId(1L)
                .questionSetRules(createRules())
                .title("question set")
                .build();
    }

    public static QuestionSetRules createRules() {
        return QuestionSetRules.builder()
                .defaultTailQuestionDepth(1)
                .defaultTimeToAnswer(10)
                .defaultTimeToThink(10)
                .build();
    }
}
