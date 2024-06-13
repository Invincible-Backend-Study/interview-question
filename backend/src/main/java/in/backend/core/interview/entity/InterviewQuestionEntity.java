package in.backend.core.interview.entity;


import static in.backend.core.exception.DomainExceptionCode.TAIL_QUESTION_CREATE_FAIL;

import in.backend.core.answer.entity.AnswerEntity;
import in.backend.core.question.entity.QuestionEntity;
import in.backend.core.report.entity.AIFeedback;
import in.backend.global.entity.BaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;


@Getter
@Entity
@Table(name = "interview_question")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class InterviewQuestionEntity extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Long interviewId;

    @Column(nullable = false)
    private Long memberId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(nullable = false)
    private QuestionEntity question;

    @ManyToOne(fetch = FetchType.LAZY)
    private AnswerEntity answer;

    @Embedded
    private AIFeedback aiFeedback;


    @Column(nullable = false)
    private int remainTailQuestionCount;


    @Builder
    public InterviewQuestionEntity(Long interviewId, Long memberId, QuestionEntity question) {
        this.interviewId = interviewId;
        this.memberId = memberId;
        this.question = question;
    }

    public String getQuestion() {
        return question.getContent();
    }


    public void consumeTailDepthCount() {
        TAIL_QUESTION_CREATE_FAIL.invokeByCondition(remainTailQuestionCount <= 0);
        remainTailQuestionCount -= 1;
    }
}
