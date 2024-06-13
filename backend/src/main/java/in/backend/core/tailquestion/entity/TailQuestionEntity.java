package in.backend.core.tailquestion.entity;

import in.backend.core.answer.entity.AnswerEntity;
import in.backend.core.interview.entity.InterviewQuestionEntity;
import in.backend.global.entity.BaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
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
@Table(name = "tail_questions")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class TailQuestionEntity extends BaseEntity {

    @Id
    @GeneratedValue
    private Long id;

    @Column(nullable = false)
    private Long memberId;

    @JoinColumn(nullable = false)
    @ManyToOne(fetch = FetchType.LAZY)
    private InterviewQuestionEntity interviewQuestion;

    @Column(nullable = false)
    private String content;

    @ManyToOne(fetch = FetchType.LAZY)
    private AnswerEntity answer;


    @Builder
    public TailQuestionEntity(Long memberId, String content, InterviewQuestionEntity interviewQuestion) {
        this.memberId = memberId;
        this.interviewQuestion = interviewQuestion;
        this.content = content;
    }
}
