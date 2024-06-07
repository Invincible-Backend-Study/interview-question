package in.backend.domain.questionset.entity;


import in.backend.domain.questionset.entity.policy.QuestionSetPolicy;
import in.backend.global.entity.BaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@Table(name = "question_sets")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class QuestionSetEntity extends BaseEntity {


    /**
     * question set이 가지는 질문 목록
     */

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    /**
     * questionSet을 만든 관리자
     */
    private Long adminId;
    /**
     * question set의 제목
     */
    @Column(nullable = false)
    private String title;
    /**
     * question set을 interview로 변환할 때 사용할 기본 규칙
     */
    @Embedded
    private Questions questions = Questions.empty();
    @Embedded
    private QuestionSetRules questionSetRules;

    public QuestionSetEntity(Long adminId, String title, QuestionSetRules questionSetRules) {
        this.adminId = adminId;
        this.title = title;
        this.questionSetRules = questionSetRules;

        QuestionSetPolicy.validate(this);
    }
}


