package in.backend.global.fixture;


import in.backend.core.questionset.domain.QuestionSetEntity;

public class QuestionSetFixture {

    public static QuestionSetEntity create() {
        return QuestionSetEntity.builder()
                .adminId(1L)
                .title("title")
                .questionSetRules(QuestionSetRulesFixture.create())
                .build();
    }
}
