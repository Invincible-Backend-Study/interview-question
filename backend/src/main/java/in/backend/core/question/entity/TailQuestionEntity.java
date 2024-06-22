package in.backend.core.question.entity;


import in.backend.core.interview.application.InterviewSubmitCommand.AnswerInfo;
import in.backend.core.interview.application.InterviewSubmitCommand.FeedbackInfo;
import in.backend.core.interview.entity.AIFeedback;
import in.backend.core.interview.entity.Answer;
import jakarta.persistence.Column;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.util.Objects;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;


@ToString
@Getter
@Entity
@Table(name = "tail_questions")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class TailQuestionEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Long memberId;

    @Column(nullable = false)
    private Long interviewId;

    @Column(nullable = false)
    private Long interviewQuestionId;

    @Column(nullable = false)
    private String question;

    private AIFeedback aiFeedback;

    @Embedded
    private Answer answer = Answer.init();

    @Builder
    public TailQuestionEntity(Long interviewId, Long memberId, Long interviewQuestionId, String question) {
        this.interviewId = interviewId;
        this.interviewQuestionId = interviewQuestionId;
        this.memberId = memberId;
        this.question = question;
    }

    public void submit(AnswerState answerState, AnswerInfo answerInfo, FeedbackInfo feedbackInfo) {

        switch (answerState) {
            case PASS -> answer = Answer.pass();
            case COMPLETE -> {
                this.aiFeedback = AIFeedback.from(feedbackInfo);
                this.answer = Answer.complete(
                        answerInfo.content(),
                        answerInfo.timeToAnswer()
                );
            }
            default -> throw new UnsupportedOperationException();
        }
    }

    public boolean isComplete() {
        return answer.isComplete();
    }

    // null이 발생할 수 있을 것 같은데..? 어떻게 처리해줘야..할까
    public String getTailQuestion() {
        if (Objects.isNull(aiFeedback)) {
            return "";
        }
        return aiFeedback.getTailQuestion();
    }
}
