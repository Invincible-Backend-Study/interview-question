package in.backend.core.interview.presentation.payload;

import in.backend.core.interview.application.InterviewSubmitCommand;
import in.backend.core.interview.application.InterviewSubmitCommand.AnswerInfo;
import in.backend.core.interview.application.InterviewSubmitCommand.FeedbackInfo;
import in.backend.core.question.entity.AnswerState;
import lombok.Builder;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Builder
public record InterviewSubmitRequest(
        Long interviewId,
        Long interviewQuestionId,
        Integer currentIndex,
        String answerState,
        String aiFeedback,
        String tailQuestion,
        String originalContent,
        Integer timeToAnswer,
        String answerContent
) {
    public InterviewSubmitCommand to() {
        log.info("{}", this);
        var feedback = FeedbackInfo.builder()
                .aiFeedback(aiFeedback)
                .originalContent(originalContent)
                .tailQuestion(tailQuestion)
                .score(1)
                .build();

        var answer = AnswerInfo.builder()
                .timeToAnswer(timeToAnswer)
                .content(answerContent)
                .build();

        return InterviewSubmitCommand.builder()
                .interviewQuestionId(interviewQuestionId)
                .interviewId(interviewId)
                .currentIndex(currentIndex)
                .answerState(AnswerState.valueOf(answerState))
                .feedback(feedback)
                .answer(answer)
                .build();
    }
}
