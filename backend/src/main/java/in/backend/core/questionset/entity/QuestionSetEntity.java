package in.backend.core.questionset.entity;


import static in.backend.core.exception.DomainExceptionCode.INTERVIEW_CREATE_FAIL;
import static java.util.Comparator.comparing;

import in.backend.core.question.entity.QuestionEntity;
import in.backend.core.questionset.entity.policy.QuestionSetPolicy;
import in.backend.global.entity.BaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.util.List;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Getter
@Entity
@Table(name = "question_sets")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class QuestionSetEntity extends BaseEntity {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * questionSet을 만든 관리자
     */
    @Column(nullable = false)
    private Long adminId;

    /**
     * question set의 제목
     */
    @Column(nullable = false)
    private String title;

    @Column()
    private String description;


    /**
     * question set을 interview로 변환할 때 사용할 기본 규칙
     */
    @Embedded
    private QuestionSetRules questionSetRules;


    /**
     * question set이 가지는 질문 목록
     */
    @Embedded
    private Questions questions = Questions.empty();


    @Builder
    public QuestionSetEntity(Long adminId, String title, String description, QuestionSetRules questionSetRules) {
        this.adminId = adminId;
        this.title = title;
        this.questionSetRules = questionSetRules;
        this.description = description;
        QuestionSetPolicy.validate(this);
    }

    public List<QuestionEntity> extractQuestions(int count) {
        INTERVIEW_CREATE_FAIL.invokeByCondition(count <= 0);
        INTERVIEW_CREATE_FAIL.invokeByCondition(questions.isEmpty());
        INTERVIEW_CREATE_FAIL.invokeByCondition(questions.size() < count);

        if (questions.hasSameSize(count)) {
            return questions.getValue();
        }

        return questions.shuffle()
                .subList(0, count)
                .stream()
                .sorted(comparing(QuestionEntity::getSequence))
                .toList();
    }


    public int getQuestionSize() {
        return questions.size();
    }

    public int getTailQuestionDepth() {
        return questionSetRules.getDefaultTailQuestionDepth();
    }

}


