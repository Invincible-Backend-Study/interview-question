package in.backend.core.questionset.domain.policy;

import static in.backend.core.exception.DomainExceptionCode.QUESTION_SET_GENERATION_FAIL;
import static in.backend.core.exception.DomainExceptionCode.QUESTION_SET_RULES_GENERATION_FAIL;

import in.backend.core.questionset.domain.QuestionSetEntity;
import in.backend.core.questionset.domain.QuestionSetRules;
import java.util.Objects;

public class QuestionSetPolicy {

    private static final int TITLE_SIZE_MAX = 100;

    private static final int TAIL_QUESTION_DEPTH_MIN = 0;
    private static final int TAIL_QUESTION_DEPTH_MAX = 10;

    // 초 단위
    private static final int TIME_TO_THINK_MIN = 5;
    private static final int TIME_TO_THINK_MAX = 20;

    // 초 단위
    private static final int TIME_TO_ANSWER_MIN = 5;
    private static final int TIME_TO_ANSWER_MAX = 90;


    public static void validate(QuestionSetRules rules) {
        final var tailQuestionDepth = rules.getDefaultTailQuestionDepth();
        final var timeToAnswer = rules.getDefaultTimeToAnswer();
        final var timeToThink = rules.getDefaultTimeToThink();

        QUESTION_SET_RULES_GENERATION_FAIL.invokeByCondition(tailQuestionDepth < TAIL_QUESTION_DEPTH_MIN);
        QUESTION_SET_RULES_GENERATION_FAIL.invokeByCondition(tailQuestionDepth > TAIL_QUESTION_DEPTH_MAX);

        QUESTION_SET_RULES_GENERATION_FAIL.invokeByCondition(timeToAnswer < TIME_TO_ANSWER_MIN);
        QUESTION_SET_RULES_GENERATION_FAIL.invokeByCondition(timeToAnswer > TIME_TO_ANSWER_MAX);

        QUESTION_SET_RULES_GENERATION_FAIL.invokeByCondition(timeToThink < TIME_TO_THINK_MIN);
        QUESTION_SET_RULES_GENERATION_FAIL.invokeByCondition(timeToThink > TIME_TO_THINK_MAX);
    }


    public static void validate(QuestionSetEntity questionSet) {
        final var title = questionSet.getTitle();

        QUESTION_SET_GENERATION_FAIL.invokeByCondition(Objects.isNull(title));
        QUESTION_SET_GENERATION_FAIL.invokeByCondition(title.length() > TITLE_SIZE_MAX);

    }
}
