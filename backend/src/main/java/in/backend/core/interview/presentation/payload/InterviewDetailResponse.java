package in.backend.core.interview.presentation.payload;

import in.backend.core.interview.entity.InterviewState;
import in.backend.core.question.entity.AnswerState;
import java.util.List;

public record InterviewDetailResponse(
        Long interviewId,
        InterviewState interviewState,
        String question,

        List<InterviewQuestionDetail> interviewQuestions
) {

    public record InterviewQuestionDetail(
            Long interviewQuestionId,
            AnswerState answerState,
            String question,
            String answer,
            String referenceLinks,
            String feedback,
            Long remainTailQuestionCount

    ) {

    }
}
