package in.backend.core.questionset.application;

import lombok.Builder;
import org.springframework.web.multipart.MultipartFile;

@Builder
public record QuestionSetCreator(
        String title,
        String description,
        String thumbnailUrl,
        MultipartFile multipartFile,
        int defaultTailQuestionDepth,
        int defaultTimeToAnswer,
        int defaultTimeToThink
) {
}
