package in.backend.core.interview.presentation.payload.request;

import in.backend.core.answer.entity.AnswerEntity;
import in.backend.core.question.entity.AnswerState;

public record InterviewQuestionSubmitRequest(
        Long interviewId,
        Long interviewQuestionId,
        Long currentIndex,

        Integer timeToAnswer,

        String answer,
        String tailQuestion,
        String feedback,

        AnswerState answerState,
        int score
) {


    public AnswerEntity toAnswerEntity(Long memberId) {
        return AnswerEntity.builder()
                .content(answer)
                .memberId(memberId)
                .timeToAnswer(timeToAnswer)
                .answerState(answerState)
                .build();
    }
}
