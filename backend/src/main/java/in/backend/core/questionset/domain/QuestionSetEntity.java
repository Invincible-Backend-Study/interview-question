package in.backend.core.questionset.domain;


import static in.backend.core.exception.DomainExceptionCode.INTERVIEW_CREATE_FAIL;

import in.backend.core.interview.entity.InterviewSettings;
import in.backend.core.question.entity.QuestionEntity;
import in.backend.core.questionset.domain.policy.QuestionSetPolicy;
import in.backend.global.entity.BaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.util.Collections;
import java.util.Comparator;
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

    /**
     * question set이 가지는 질문 목록
     */
    @Embedded
    private Questions questions = Questions.empty();

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
    /**
     * question set을 interview로 변환할 때 사용할 기본 규칙
     */
    @Embedded
    private QuestionSetRules questionSetRules;

    @Builder
    public QuestionSetEntity(Long adminId, String title, QuestionSetRules questionSetRules) {
        this.adminId = adminId;
        this.title = title;
        this.questionSetRules = questionSetRules;

        QuestionSetPolicy.validate(this);
    }


    /**
     * interview에서 사용하는 규칙을 만듭니다.
     */
    public InterviewSettings create(
            Integer tailQuestionDepth,
            Integer timeToThink,
            Integer timeToAnswer
    ) {
        return InterviewSettings.builder()
                .tailQuestionDepth(getIfPresent(tailQuestionDepth, questionSetRules.getDefaultTailQuestionDepth()))
                .timeToThink(getIfPresent(timeToThink, questionSetRules.getDefaultTimeToThink()))
                .timeToAnswer(getIfPresent(timeToAnswer, questionSetRules.getDefaultTimeToAnswer()))
                .build();
    }


    public List<QuestionEntity> extractQuestions(int count) {
        log.info("{}", questions.size());
        INTERVIEW_CREATE_FAIL.invokeByCondition(questions.isEmpty());
        INTERVIEW_CREATE_FAIL.invokeByCondition(count > questions.size());

        final var originalQuestions = questions.getValue();
        if (questions.size() == count) {
            return originalQuestions;
        }

        Collections.shuffle(originalQuestions);

        return originalQuestions.subList(0, count)
                .stream()
                .sorted(Comparator.comparing(QuestionEntity::getSequence))
                .toList();
    }
}


