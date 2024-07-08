package in.backend.core.question.presentation.payload;

import java.util.List;

public record QuestionAdminSearchResponse(
        List<QuestionAdminDetailResponse> questions
) {
}
