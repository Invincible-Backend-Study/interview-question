package in.backend.report.domain;


import jakarta.persistence.Embeddable;

@Embeddable
public class AIFeedback {
    private int score;
    private String feedback;
    private String tailQuestion;
}
