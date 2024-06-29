package in.backend.core.interview.entity;


import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

import in.backend.core.interview.application.InterviewSubmitCommand.FeedbackInfo;
import org.junit.jupiter.api.Test;

class AIFeedbackTest {

    @Test
    void 피드백을_정상적으로_생성할_수_있습니다() {
        var aiFeedback = AIFeedback.from(new FeedbackInfo("피드백", "꼬리질문", 100));

        assertAll(
                () -> assertThat(aiFeedback.getFeedbackContent()).isEqualTo("피드백"),
                () -> assertThat(aiFeedback.getScore()).isEqualTo(100),
                () -> assertThat(aiFeedback.getTailQuestion()).isEqualTo("꼬리질문")
        );
    }

    @Test
    void 피드백을_초기화_상태로_구현할_수_있습니다() {
        var aiFeedback = AIFeedback.empty();
        assertAll(
                () -> assertThat(aiFeedback.getFeedbackContent()).isNull(),
                () -> assertThat(aiFeedback.getScore()).isNull(),
                () -> assertThat(aiFeedback.getTailQuestion()).isNull()
        );
    }
}