package in.backend.core.interview.entity;


import in.backend.core.interview.entity.policy.InterviewPolicy;
import jakarta.persistence.Embeddable;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;


@Getter
@Embeddable
@NoArgsConstructor
public class InterviewSettings {
    private int tailQuestionDepth;
    private int timeToThink;
    private int timeToAnswer;

    @Builder
    public InterviewSettings(int tailQuestionDepth, int timeToThink, int timeToAnswer) {
        this.tailQuestionDepth = tailQuestionDepth;
        this.timeToThink = timeToThink;
        this.timeToAnswer = timeToAnswer;

        InterviewPolicy.validate(this);

    }


}
