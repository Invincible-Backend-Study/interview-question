package in.backend.core.interview.application;

import in.backend.core.interview.entity.InterviewEntity;
import in.backend.core.interview.entity.InterviewQuestionEntity;
import in.backend.core.interview.entity.InterviewState;
import in.backend.core.question.entity.AnswerState;
import in.backend.core.question.entity.TailQuestionEntity;
import java.util.List;
import lombok.Builder;

@Builder
public record InterviewDetail(
        Long interviewId,
        String title,
        InterviewState interviewState,
        List<InterviewQuestionDetail> interviewQuestions
) {

    public static InterviewDetail from(
            InterviewEntity interview,
            List<InterviewQuestionDetail> interviewQuestionDetails
    ) {
        return InterviewDetail.builder()
                .interviewId(interview.getId())
                .title(interview.getTitle())
                .interviewState(interview.getInterviewState())
                .interviewQuestions(interviewQuestionDetails)
                .build();
    }


    @Builder
    public record InterviewQuestionDetail(
            Long interviewQuestionId,
            AnswerState answerState,
            String question,
            String answer,
            String referenceLinks,
            String feedback,
            int remainTailQuestionCount,
            List<TailQuestionDetail> tailQuestions
    ) {

        public static InterviewQuestionDetail from(
                InterviewQuestionEntity interviewQuestion,
                List<TailQuestionEntity> tailQuestions
        ) {
            var tailQuestionDetails = tailQuestions.stream()
                    .map(TailQuestionDetail::from)
                    .toList();

            return InterviewQuestionDetail.builder()
                    .interviewQuestionId(interviewQuestion.getId())
                    .answerState(interviewQuestion.getAnswerState())
                    .question(interviewQuestion.getQuestion())
                    .referenceLinks(interviewQuestion.getReferenceLinks())
                    .feedback(interviewQuestion.getFeedback())
                    .remainTailQuestionCount(interviewQuestion.getRemainTailQuestionCount())
                    .tailQuestions(tailQuestionDetails)
                    .build();
        }
    }

    @Builder
    public record TailQuestionDetail(
            Long tailQuestionId,
            AnswerState answerState,
            String question,
            String answer,
            String feedback
    ) {
        public static TailQuestionDetail from(TailQuestionEntity tailQuestion) {
            return TailQuestionDetail.builder()
                    .tailQuestionId(tailQuestion.getId())
                    .answerState(tailQuestion.getAnswerState())
                    .question(tailQuestion.getQuestion())
                    .answer(tailQuestion.getAnswer())
                    .feedback(tailQuestion.getFeedback())
                    .build();

        }

        public static List<TailQuestionDetail> from(List<TailQuestionEntity> tailQuestions) {
            return tailQuestions.stream()
                    .map(TailQuestionDetail::from)
                    .toList();
        }
    }
}
