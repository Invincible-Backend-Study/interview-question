package in.backend.core.question.application;


import in.backend.core.question.entity.TailQuestionEntity;
import lombok.AccessLevel;
import lombok.Builder;

@Builder(access = AccessLevel.PRIVATE)
public record TailQuestionSubmitResult(
        Long interviewQuestionId,
        Long tailQuestionId,
        String question
) {


    public static TailQuestionSubmitResult create(TailQuestionEntity tailQuestionEntity) {
        return TailQuestionSubmitResult.builder()
                .interviewQuestionId(tailQuestionEntity.getInterviewQuestionId())
                .tailQuestionId(tailQuestionEntity.getId())
                .question(tailQuestionEntity.getQuestion())
                .build();

    }

    public static TailQuestionSubmitResult empty() {
        return new TailQuestionSubmitResult(null, null, null);
    }

}
