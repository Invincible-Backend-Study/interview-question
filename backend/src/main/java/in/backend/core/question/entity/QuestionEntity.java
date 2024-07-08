package in.backend.core.question.entity;


import in.backend.core.question.application.QuestionWriter.QuestionInfo;
import in.backend.core.questionset.entity.QuestionSetEntity;
import in.backend.global.entity.BaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
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
@Table(name = "questions")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class QuestionEntity extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 질문 내용입니다.
     */
    @Column(nullable = false)
    private String content;

    /**
     * 질문 목록에서 순서 정렬 기준이 됨
     */
    @Column(nullable = false)
    private Integer sequence;

    /**
     * 콤마로 링크를 구분합니다.
     */
    private String referenceLinks;


    @ManyToOne
    @JoinColumn(name = "question_set_id", nullable = false)
    private QuestionSetEntity questionSet;


    @Builder
    public QuestionEntity(
            String content,
            String referenceLinks,
            QuestionSetEntity questionSet,
            int sequence
    ) {
        this.content = content;
        this.referenceLinks = referenceLinks;
        this.questionSet = questionSet;
        this.sequence = sequence;
    }

    public Long getQuestionSetId() {
        return questionSet.getId();
    }

    public void update(QuestionInfo questionInfo) {
        applyIfPresent(questionInfo.content(), value -> this.content = value);
        applyIfPresent(questionInfo.sequence(), value -> this.sequence = value);
    }

}
