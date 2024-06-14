package in.backend.core.interview.entity;


import in.backend.core.answer.entity.AnswerEntity;
import in.backend.core.question.entity.QuestionEntity;
import in.backend.global.entity.BaseEntity;
import jakarta.persistence.Column;
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
import lombok.NoArgsConstructor;

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

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(nullable = false)
    private QuestionEntity question;

    @ManyToOne(fetch = FetchType.LAZY)
    private AnswerEntity answer;

    @Column(nullable = false)
    private int remainTailQuestionCount;


    @Builder
    public InterviewQuestionEntity(
            Long interviewId,
            Long memberId,
            QuestionEntity question,
            int remainTailQuestionCount
    ) {
        this.interviewId = interviewId;
        this.memberId = memberId;
        this.question = question;
        this.remainTailQuestionCount = remainTailQuestionCount;
    }


}
