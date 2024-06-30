package in.backend.core.question.entity.policy;

import static in.backend.core.exception.DomainExceptionCode.TAIL_QUESTION_CREATE_FAIL;

import in.backend.core.question.entity.TailQuestionEntity;
import java.util.Objects;
import org.apache.logging.log4j.util.Strings;

public class TailQuestionPolicy {
    public static void validate(TailQuestionEntity tailQuestionEntity) {
        TAIL_QUESTION_CREATE_FAIL.invokeByCondition(Objects.isNull(tailQuestionEntity.getMemberId()));
        TAIL_QUESTION_CREATE_FAIL.invokeByCondition(Objects.isNull(tailQuestionEntity.getInterviewId()));
        TAIL_QUESTION_CREATE_FAIL.invokeByCondition(Objects.isNull(tailQuestionEntity.getInterviewQuestionId()));
        TAIL_QUESTION_CREATE_FAIL.invokeByCondition(Strings.isBlank(tailQuestionEntity.getQuestion()));
    }
}
