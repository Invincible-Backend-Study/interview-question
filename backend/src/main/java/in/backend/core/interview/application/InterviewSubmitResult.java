package in.backend.core.interview.application;

public record InterviewSubmitResult(
        Long tailQuestionId
) {
    public static InterviewSubmitResult empty() {
        return new InterviewSubmitResult(null);
    }
}
