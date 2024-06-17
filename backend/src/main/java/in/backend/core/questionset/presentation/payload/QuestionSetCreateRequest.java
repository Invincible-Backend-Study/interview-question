package in.backend.core.questionset.presentation.payload;

public record QuestionSetCreateRequest(
        String title,
        Long defaultTailQuestionDepth,
        Long defaultTimeToThink,
        Long defaultTimeToAnswer
) {
}
