package in.backend.core.interview.entity.policy;

import static in.backend.core.exception.DomainExceptionCode.INTERVIEW_CREATE_FAIL;

import in.backend.core.interview.entity.InterviewEntity;
import in.backend.core.interview.entity.InterviewSettings;
import java.util.Objects;
import org.apache.logging.log4j.util.Strings;

public class InterviewPolicy {

    public static void validate(InterviewEntity interview) {
        INTERVIEW_CREATE_FAIL.invokeByCondition(Objects.isNull(interview.getMemberId()));
        INTERVIEW_CREATE_FAIL.invokeByCondition(Objects.isNull(interview.getTitle()));
        INTERVIEW_CREATE_FAIL.invokeByCondition(Strings.isBlank(interview.getTitle()));
    }

    public static void validate(InterviewSettings interviewSettings) {
        INTERVIEW_CREATE_FAIL.invokeByCondition(interviewSettings.getTailQuestionDepth() < 0);
        INTERVIEW_CREATE_FAIL.invokeByCondition(interviewSettings.getTimeToAnswer() < 0);
        INTERVIEW_CREATE_FAIL.invokeByCondition(interviewSettings.getTimeToThink() < 0);
    }

}
