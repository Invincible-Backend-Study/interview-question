package in.backend.core.interview.entity;


import in.backend.core.interview.application.InterviewSubmitCommand.AnswerInfo;
import in.backend.core.interview.application.InterviewSubmitCommand.FeedbackInfo;
import in.backend.core.question.entity.AnswerState;
import in.backend.core.question.entity.TailQuestionEntity;
import in.backend.global.entity.BaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.util.Optional;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Getter
@Entity
@Table(name = "interview_items")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class InterviewQuestionEntity extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Long interviewId;

    @Column(nullable = false)
    private Long memberId;

//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(nullable = false)
//    private QuestionEntity question;

    @Column(nullable = false)
    private Long questionId;

    /**
     * 질문 가져올 때 조인 횟수 최소화 용도
     */
    @Column(nullable = false)
    private String questionContent;

    @Embedded
    private Answer answer = Answer.init();

    @Embedded
    private AIFeedback aiFeedback = AIFeedback.empty();

    @Column(nullable = false)
    private int remainTailQuestionCount;


    @Builder
    public InterviewQuestionEntity(
            Long interviewId,
            Long memberId,
            Long questionId,
            String questionContent,
            int remainTailQuestionCount
    ) {
        this.interviewId = interviewId;
        this.memberId = memberId;
        this.questionId = questionId;
        this.questionContent = questionContent;
        this.remainTailQuestionCount = remainTailQuestionCount;
    }


    public String getQuestion() {
        return questionContent;
    }

    public void submit(AnswerState answerState, AnswerInfo answerInfo, FeedbackInfo feedbackInfo) {
        if (answerState == AnswerState.PASS) {
            this.answer = Answer.pass();
            return;
        }

        if (answerState == AnswerState.COMPLETE) {
            this.answer = Answer.complete(answerInfo.content(), answerInfo.timeToAnswer());
            this.aiFeedback = AIFeedback.from(feedbackInfo);
            return;
        }

        throw new UnsupportedOperationException();
    }

    public boolean hasTailQuestionCount() {
        return answer.isComplete() && remainTailQuestionCount > 0;
    }


    private Optional<TailQuestionEntity> createTailQuestion(String content) {
        if (hasTailQuestionCount()) {
            remainTailQuestionCount--;
            return Optional.of(TailQuestionEntity.builder()
                    .memberId(memberId)
                    .interviewId(interviewId)
                    .interviewQuestionId(id)
                    .question(content)
                    .build());
        }
        return Optional.empty();
    }

    public Optional<TailQuestionEntity> createTailQuestion() {
        return createTailQuestion(aiFeedback.getTailQuestion());
    }


    public Optional<TailQuestionEntity> createTailQuestion(TailQuestionEntity previousQuestion) {
        if (!previousQuestion.isComplete()) {
            return Optional.empty();
        }

        return createTailQuestion(previousQuestion.getTailQuestion());
    }

    public AnswerState getAnswerState() {
        return answer.getAnswerState();
    }

    public String getReferenceLinks() {
        return "";

    }

    public String getFeedback() {
        return aiFeedback.getFeedbackContent();
    }

    public String getAnswer() {
        return answer.getContent();
    }
}

