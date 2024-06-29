package in.backend.core.interview.entity;

import static in.backend.core.exception.DomainExceptionCode.INTERVIEW_CREATE_FAIL;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertAll;

import in.backend.core.exception.DomainException;
import in.backend.core.exception.DomainExceptionCode;
import in.backend.global.fixture.InterviewFixture;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullSource;
import org.junit.jupiter.params.provider.ValueSource;


@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
class InterviewEntityTest {

    @Nested
    class 인터뷰를 {

        @Test
        void _정상적으로_생성합니다() {
            var interview = InterviewEntity.init(
                    1L,
                    "인터뷰 제목",
                    10,
                    new InterviewSettings(10, 10, 10)
            );

            assertAll(
                    () -> assertThat(interview.getTitle()).isEqualTo("인터뷰 제목"),
                    () -> assertThat(interview.getIndex()).isEqualTo(0),
                    () -> assertThat(interview.getCurrentProgressIndex()).isEqualTo(0),
                    () -> assertThat(interview.getInterviewState()).isEqualTo(InterviewState.PROGRESS),
                    () -> assertThat(interview.getMemberId()).isEqualTo(1L),
                    () -> assertThat(interview.getSize()).isEqualTo(10),
                    () -> assertThat(interview.getTailQuestionDepth()).isEqualTo(10)
            );
        }

        @Nested
        class _생성_실패하는_조건은 {

            @Test
            void _회원_id가_없는_경우_입니다() {

                assertThatThrownBy(() -> InterviewEntity.init(
                        null,
                        "인터뷰 제목",
                        10,
                        new InterviewSettings(10, 10, 10)
                )).isInstanceOf(DomainException.class)
                        .hasMessage(INTERVIEW_CREATE_FAIL.getMessage());

            }

            @NullSource
            @ParameterizedTest
            @ValueSource(strings = {"  ", ""})
            void _인터뷰_제목이_빈칸_이거나_null인_경우도_해당합니다(String input) {
                assertThatThrownBy(() -> InterviewEntity.init(
                        1L,
                        input,
                        10,
                        new InterviewSettings(10, 10, 10)
                )).isInstanceOf(DomainException.class)
                        .hasMessage(INTERVIEW_CREATE_FAIL.getMessage());
            }
        }

        @Nested
        class 진행시킬_때 {

            @Test
            void _한_문제를_풀면_다음_순번으로_넘어갑니다() {

                var interview = InterviewFixture.create();

                interview.increaseIndex(0);

                assertThat(interview.getCurrentProgressIndex()).isEqualTo(1);
            }

            @Test
            void _현재_풀고_있는_문제와_다른_문제를_풀고_있으면_오류가_발생합니다() {
                var interview = InterviewFixture.create();

                assertThatThrownBy(() -> interview.increaseIndex(1)).isInstanceOf(DomainException.class)
                        .hasMessage(DomainExceptionCode.INTERVIEW_STATE_DID_NOT_MATCH.getMessage());

            }

            @Test
            void 다음_순번에_도달하면_더_이상_진행할_수_없습니다() {
                var problemCount = 4;
                var tailQuestionIndex = 1;
                var interview = InterviewFixture.create(problemCount, tailQuestionIndex);

                interview.increaseIndex(0);
                interview.increaseIndex(1);
                interview.increaseIndex(2);
                interview.increaseIndex(3);
                assertThatThrownBy(() -> interview.increaseIndex(4)).isInstanceOf(DomainException.class)
                        .hasMessage(DomainExceptionCode.INTERVIEW_STATE_IS_DONE.getMessage());
            }
        }
    }
}