package in.backend.core.questionset.presentation.payload;

import in.backend.core.questionset.application.QuestionSetCreator;
import jakarta.validation.constraints.NotNull;
import org.hibernate.validator.constraints.Length;

public record QuestionSetCreateRequest(

        /**
         * 질문 제목
         */
        @NotNull
        @Length(min = 3, max = 50, message = "제목은 최소 6자이며 최대50자 입니다.")
        String title,

        @NotNull
        @Length(min = 3, max = 100, message = "설명은 최소 3자이면 최대 100자입니다.")
        String description,

        /**
         * 질문 depth
         */
        @NotNull
        Integer defaultTailQuestionDepth,

        /**
         * 질문 생각 시간
         */
        Integer defaultTimeToThink,

        /**
         * 답변 소요 시간
         */
        Integer defaultTimeToAnswer
) {

    public QuestionSetCreateRequest {
        // 이후 추가될 기능
        defaultTimeToThink = 1;
        defaultTimeToAnswer = 1;
    }


    public QuestionSetCreator from(String thumbnailUrl) {
        return QuestionSetCreator.builder()
                .title(title)
                .description(description)
                .thumbnailUrl(thumbnailUrl)
                .defaultTailQuestionDepth(defaultTailQuestionDepth)
                .defaultTimeToThink(defaultTimeToThink)
                .defaultTimeToAnswer(defaultTimeToAnswer)
                .build();
    }
}
