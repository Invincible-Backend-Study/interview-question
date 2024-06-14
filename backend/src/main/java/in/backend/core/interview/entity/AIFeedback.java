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

    private String content;

    private int score;


    @Column(length = 1000)
    private String origianlContent;


    @Builder(access = AccessLevel.PROTECTED)
    protected AIFeedback(String tailQuestion, String content, int score, String origianlContent) {
        this.tailQuestion = tailQuestion;
        this.content = content;
        this.score = score;
        this.origianlContent = origianlContent;
    }

    public static AIFeedback empty() {
        return null;
    }

    public static AIFeedback from(FeedbackInfo feedbackInfo) {
        return AIFeedback.builder()
                .content(feedbackInfo.aiFeedback())
                .origianlContent(feedbackInfo.originalContent())
                .score(feedbackInfo.score())
                .tailQuestion(feedbackInfo.tailQuestion())
                .build();
    }

}
