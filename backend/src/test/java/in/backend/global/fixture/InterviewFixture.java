package in.backend.global.fixture;

import in.backend.core.interview.entity.InterviewEntity;
import in.backend.core.interview.entity.InterviewSettings;

public class InterviewFixture {

    public static InterviewEntity create() {
        return InterviewEntity.init(1L, 10, InterviewSettings.builder()
                .tailQuestionDepth(1)
                .timeToAnswer(10)
                .timeToThink(10)
                .build()
        );
    }

    public static InterviewEntity create(int depth) {
        return InterviewEntity.init(1L, 10, InterviewSettings.builder()
                .tailQuestionDepth(depth)
                .timeToAnswer(10)
                .timeToThink(10)
                .build()
        );
    }
}
