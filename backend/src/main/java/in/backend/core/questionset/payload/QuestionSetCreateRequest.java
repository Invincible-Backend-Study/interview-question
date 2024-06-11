package in.backend.core.questionset.payload;

public record QuestionSetCreateRequest(
        String title,
        Long defaultTailQuestionDepth,
        Long defaultTimeToThink,
        Long defaultTimeToAnswer
) {
}
