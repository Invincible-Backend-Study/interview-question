package in.backend.core.interview.entity;


import in.backend.core.question.entity.AnswerState;
import jakarta.persistence.Embeddable;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;


@Getter
@Embeddable
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Answer {


    @Enumerated(EnumType.STRING)
    private AnswerState answerState;

    private String content;

    private Integer timeToAnswer;


    @Builder(access = AccessLevel.PROTECTED)
    protected Answer(AnswerState answerState, String content, Integer timeToAnswer) {
        this.answerState = answerState;
        this.content = content;
        this.timeToAnswer = timeToAnswer;
    }

    public static Answer init() {
        return Answer.builder()
                .answerState(AnswerState.INIT)
                .build();
    }

    public static Answer pass() {
        return Answer.builder()
                .answerState(AnswerState.PASS)
                .build();
    }

    public static Answer complete(String content, int timeToAnswer) {
        return Answer.builder()
                .answerState(AnswerState.COMPLETE)
                .content(content)
                .timeToAnswer(timeToAnswer)
                .build();
    }


    public boolean isComplete() {
        return answerState == AnswerState.COMPLETE;
    }

}
