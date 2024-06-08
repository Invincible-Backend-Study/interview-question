package in.backend.domain.question.entity;

import in.backend.answer.domain.AnswerState;
import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

@Embeddable
public class Answer {

    /**
     * 질문에 대한 답변
     */
    private String content;


    /**
     * 답변하는 데 걸린 시간
     */
    @Column(nullable = false)
    private Long timeToAnswer;

    /**
     * 답변 상태
     */
    private AnswerState answerState;

}
