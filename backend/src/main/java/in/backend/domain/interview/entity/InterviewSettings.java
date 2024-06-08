package in.backend.domain.interview.entity;


import jakarta.persistence.Embeddable;

@Embeddable
public class InterviewSettings {
    private int tailQuestionDepth;
    private int timeToWait;
    private int timeToAnswer;
}
