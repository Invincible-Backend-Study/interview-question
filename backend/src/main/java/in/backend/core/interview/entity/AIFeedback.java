package in.backend.core.interview.entity;


import in.backend.core.interview.application.InterviewSubmitCommand.FeedbackInfo;
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

    private Integer score;

    @Builder(access = AccessLevel.PROTECTED)
    protected AIFeedback(String tailQuestion, String feedbackContent, int score) {
        this.tailQuestion = tailQuestion;
        this.feedbackContent = feedbackContent;
        this.score = score;
    }

    public static AIFeedback empty() {
        return new AIFeedback();
    }

    public static AIFeedback from(FeedbackInfo feedbackInfo) {
        return AIFeedback.builder()
                .feedbackContent(feedbackInfo.aiFeedback())
                .score(feedbackInfo.score())
                .tailQuestion(feedbackInfo.tailQuestion())
                .build();
    }

}
