package in.backend.core.interview.entity.policy;

import static in.backend.core.exception.DomainExceptionCode.INTERVIEW_QUESTION_CREATE_FAIL;

import in.backend.core.interview.entity.InterviewQuestionEntity;
import org.apache.logging.log4j.util.Strings;

public class InterviewQuestionPolicy {
    public static void validate(InterviewQuestionEntity interviewQuestion) {
        INTERVIEW_QUESTION_CREATE_FAIL.invokeByCondition(Strings.isBlank(interviewQuestion.getQuestionContent()));
        INTERVIEW_QUESTION_CREATE_FAIL.invokeByCondition(interviewQuestion.getRemainTailQuestionCount() < 0);
    }
}
