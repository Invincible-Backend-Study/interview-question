package in.backend.core.interview.entity;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

import in.backend.core.question.entity.AnswerState;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;


@DisplayName("정답 객체를")
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
class AnswerTest {

    @Nested
    class 생성할_때 {

        @Test
        void 초기화_방식으로_생성하면_모든_값이_비어_있습니다() {
            var answer = Answer.init();

            assertAll(
                    () -> assertThat(answer.getAnswerState()).isEqualTo(AnswerState.INIT),
                    () -> assertThat(answer.getContent()).isNull(),
                    () -> assertThat(answer.getTimeToAnswer()).isNull());
        }

        @Test
        void 통과_상태로_생성하면_모든_값이_비어_있습니다() {
            var answer = Answer.pass();

            assertAll(
                    () -> assertThat(answer.getAnswerState()).isEqualTo(AnswerState.PASS),
                    () -> assertThat(answer.getContent()).isNull(),
                    () -> assertThat(answer.getTimeToAnswer()).isNull());
        }

        @Test
        void 완료_상태로_제춣면_값이_존재합니다() {
            var answer = Answer.complete("답변입니다.", 10);

            assertAll(
                    () -> assertThat(answer.getAnswerState()).isEqualTo(AnswerState.COMPLETE),
                    () -> assertThat(answer.getContent()).isEqualTo("답변입니다."),
                    () -> assertThat(answer.getTimeToAnswer()).isEqualTo(10));
        }

    }

}