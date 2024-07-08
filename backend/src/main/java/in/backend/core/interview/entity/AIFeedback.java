package in.backend.core.interview.entity;


import in.backend.core.interview.application.InterviewSubmitCommand.FeedbackInfo;
import in.backend.global.converter.ReferenceLinksConverter;
import jakarta.persistence.Column;
import jakarta.persistence.Convert;
import jakarta.persistence.Embeddable;
import java.util.List;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Embeddable
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class AIFeedback {

    private String tailQuestion;

    @Column(length = 2000)
    private String feedbackContent;

    private int score;

    @Column(length = 1000)
    @Convert(converter = ReferenceLinksConverter.class)
    private List<String> referenceLinks;


    @Builder(access = AccessLevel.PROTECTED)
    protected AIFeedback(String tailQuestion, String feedbackContent, int score, List<String> referenceLinks) {
        this.tailQuestion = tailQuestion;
        this.feedbackContent = feedbackContent;
        this.score = score;
        this.referenceLinks = referenceLinks;
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
