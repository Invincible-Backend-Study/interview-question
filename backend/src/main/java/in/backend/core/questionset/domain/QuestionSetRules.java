package in.backend.core.questionset.domain;


import in.backend.core.questionset.domain.policy.QuestionSetPolicy;
import jakarta.persistence.Embeddable;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;


@Getter
@Embeddable
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class QuestionSetRules {
    private Integer defaultTailQuestionDepth;
    private Integer defaultTimeToThink;
    private Integer defaultTimeToAnswer;


    @Builder
    public QuestionSetRules(
            Integer defaultTailQuestionDepth,
            Integer defaultTimeToThink,
            Integer defaultTimeToAnswer
    ) {
        this.defaultTailQuestionDepth = defaultTailQuestionDepth;
        this.defaultTimeToThink = defaultTimeToThink;
        this.defaultTimeToAnswer = defaultTimeToAnswer;

        QuestionSetPolicy.validate(this);
    }

}
