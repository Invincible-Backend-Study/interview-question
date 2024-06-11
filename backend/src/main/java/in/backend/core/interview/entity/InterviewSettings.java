package in.backend.core.interview.entity;


import jakarta.persistence.Embeddable;
import lombok.Builder;

@Embeddable
public class InterviewSettings {
    private int tailQuestionDepth;
    private int timeToThink;
    private int timeToAnswer;

    @Builder
    public InterviewSettings(int tailQuestionDepth, int timeToThink, int timeToAnswer) {
        this.tailQuestionDepth = tailQuestionDepth;
        this.timeToThink = timeToThink;
        this.timeToAnswer = timeToAnswer;
    }
}
