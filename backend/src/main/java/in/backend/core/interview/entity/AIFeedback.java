package in.backend.core.interview.entity;


import in.backend.core.interview.application.InterviewSubmitCommand.FeedbackInfo;
import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Embeddable
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class AIFeedback {


    private String tailQuestion;

    private String feedbackContent;

    private int score;

    @Column(length = 1000)
    private String originalContent;

    @Builder(access = AccessLevel.PROTECTED)
    protected AIFeedback(String tailQuestion, String feedbackContent, int score, String originalContent) {
        this.tailQuestion = tailQuestion;
        this.feedbackContent = feedbackContent;
        this.score = score;
        this.originalContent = originalContent;
    }

    public static AIFeedback empty() {
        return new AIFeedback();
    }

    public static AIFeedback from(FeedbackInfo feedbackInfo) {
        return AIFeedback.builder()
                .feedbackContent(feedbackInfo.aiFeedback())
                .originalContent(feedbackInfo.originalContent())
                .score(feedbackInfo.score())
                .tailQuestion(feedbackInfo.tailQuestion())
                .build();
    }

}
