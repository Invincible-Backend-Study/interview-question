package in.backend.core.question.presentation.payload;

import in.backend.core.interview.application.InterviewSubmitCommand.AnswerInfo;
import in.backend.core.interview.application.InterviewSubmitCommand.FeedbackInfo;
import in.backend.core.question.application.TailQuestionSubmitCommand;
import in.backend.core.question.entity.AnswerState;

public record TailQuestionSubmitRequest(
        Long interviewQuestionId,
        Long tailQuestionId,

        String answerState,
        String aiFeedback,
        String tailQuestion,
        String originalContent,
        Integer timeToAnswer,
        String answerContent

) {
    public TailQuestionSubmitCommand to() {
        var feedback = FeedbackInfo.builder()
                .aiFeedback(aiFeedback)
                .originalContent(originalContent)
                .build();

        var answer = AnswerInfo.builder()
                .timeToAnswer(timeToAnswer)
                .content(answerContent)
                .build();

        return TailQuestionSubmitCommand.builder()
                .interviewQuestionId(interviewQuestionId)
                .tailQuestionId(tailQuestionId)
                .answerState(AnswerState.valueOf(answerState))
                .feedbackInfo(feedback)
                .answerInfo(answer)
                .build();
    }
}
