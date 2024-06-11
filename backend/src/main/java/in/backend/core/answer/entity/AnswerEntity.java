package in.backend.core.answer.entity;


import in.backend.core.question.entity.AnswerState;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "answers")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class AnswerEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 답변한 사용자
     */
    private Long userId;

    /**
     * 답변 내용
     */
    @Column(nullable = false)
    private String content;

    /**
     * 답변 상태
     */
    @Enumerated(EnumType.STRING)
    private AnswerState answerState;

    /**
     * 답변에 걸린 시간 초 단위임
     */
    @Column(nullable = false)
    private Integer timeToAnswer;


}
