package in.backend.core.interview.application;

import in.backend.core.interview.entity.InterviewEntity;
import in.backend.core.interview.entity.InterviewState;
import java.time.LocalDateTime;
import lombok.Builder;


@Builder
public record MyInterviewResult(
        Long interviewId,
        String title,
        InterviewState interviewState,
        Long questionCount,
        LocalDateTime createdAt,
        LocalDateTime updatedAt

) {

    public static MyInterviewResult from(InterviewEntity interview, Long questionCount) {

        return MyInterviewResult.builder()
                .interviewId(interview.getId())
                .title(interview.getTitle())
                .interviewState(interview.getInterviewState())
                .createdAt(interview.getCreatedAt())
                .updatedAt(interview.getUpdatedAt())
                .questionCount(questionCount)
                .build();
    }
}
