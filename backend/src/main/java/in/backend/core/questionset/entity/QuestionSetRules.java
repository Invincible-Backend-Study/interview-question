package in.backend.core.questionset.entity;


import in.backend.core.questionset.entity.policy.QuestionSetPolicy;
import jakarta.persistence.Embeddable;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;


@Getter
@Embeddable
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class QuestionSetRules {
    private Long defaultTailQuestionDepth;
    private Long defaultTimeToThink;
    private Long defaultTimeToAnswer;


    @Builder
    public QuestionSetRules(
            Long defaultTailQuestionDepth,
            Long defaultTimeToThink,
            Long defaultTimeToAnswer
    ) {
        this.defaultTailQuestionDepth = defaultTailQuestionDepth;
        this.defaultTimeToThink = defaultTimeToThink;
        this.defaultTimeToAnswer = defaultTimeToAnswer;

        QuestionSetPolicy.validate(this);
    }

}
