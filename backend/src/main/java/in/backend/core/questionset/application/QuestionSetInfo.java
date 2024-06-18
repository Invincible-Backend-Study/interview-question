package in.backend.core.questionset.application;

import in.backend.core.questionset.entity.QuestionSetEntity;
import lombok.AccessLevel;
import lombok.Builder;


@Builder(access = AccessLevel.PRIVATE)
public record QuestionSetInfo(
        Long questionSetId,
        String title,
        String description,
        Long count
) {

    public static QuestionSetInfo from(QuestionSetEntity questionSet, Long problemCount) {
        return QuestionSetInfo.builder()
                .title(questionSet.getTitle())
                .description(questionSet.getDescription())
                .count(problemCount)
                .build();
    }

}
