package in.backend.core.question.entity;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertAll;

import in.backend.core.exception.DomainException;
import in.backend.core.interview.application.InterviewSubmitCommand.AnswerInfo;
import in.backend.core.interview.application.InterviewSubmitCommand.FeedbackInfo;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullSource;
import org.junit.jupiter.params.provider.ValueSource;


@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
class TailQuestionEntityTest {


    @Test
    void 꼬리질문을_정상적으로_생성합니다() {
        var tailQuestion = TailQuestionEntity.builder()
                .memberId(1L)
                .interviewId(1L)
                .interviewQuestionId(1L)
                .question("질문입니다.")
                .build();

        assertAll(
                () -> assertThat(tailQuestion.getMemberId()).isEqualTo(1L),
                () -> assertThat(tailQuestion.getInterviewId()).isEqualTo(1L),
                () -> assertThat(tailQuestion.getQuestion()).isEqualTo("질문입니다."),
                () -> assertThat(tailQuestion.getInterviewQuestionId()).isEqualTo(1L),
                () -> assertThat(tailQuestion.getAnswer()).isNull(),
                () -> assertThat(tailQuestion.getAnswerState()).isEqualTo(AnswerState.INIT),
                () -> assertThat(tailQuestion.getFeedback()).isNull(),
                () -> assertThat(tailQuestion.getScore()).isEqualTo(0)
        );
    }


    @Nested
    class 생성하지_못하는_조건은_다음과_같습니다 {
        @Test
        void 회원_아이디가_존재하지_않는_경우() {
            assertThatThrownBy(() -> TailQuestionEntity.builder()
                    .memberId(null)
                    .interviewId(1L)
                    .interviewQuestionId(1L)
                    .question("질문입니다.")
                    .build())
                    .isInstanceOf(DomainException.class);
        }

        @Test
        void 면접_아이디가_존재하지_않는_경우() {
            assertThatThrownBy(() -> TailQuestionEntity.builder()
                    .memberId(1L)
                    .interviewId(null)
                    .interviewQuestionId(1L)
                    .question("질문입니다.")
                    .build())
                    .isInstanceOf(DomainException.class);
        }

        @ParameterizedTest
        @NullSource
        @ValueSource(strings = {" ", ""})
        void 꼬리질문이_존재하지_않는_경우(String input) {
            assertThatThrownBy(() -> TailQuestionEntity.builder()
                    .memberId(1L)
                    .interviewId(1L)
                    .interviewQuestionId(1L)
                    .question(input)
                    .build())
                    .isInstanceOf(DomainException.class);

        }
    }


    @Nested
    class 꼬리질문에_답변을 {

        @Test
        void 제출합니다() {
            var tailQuestion = TailQuestionEntity.builder()
                    .memberId(1L)
                    .interviewId(1L)
                    .interviewQuestionId(1L)
                    .question("질문입니다.")
                    .build();

            tailQuestion.submit(
                    AnswerState.COMPLETE,
                    AnswerInfo.builder()
                            .content("답변입니다.")
                            .timeToAnswer(1)
                            .build(),
                    FeedbackInfo.builder()
                            .aiFeedback("피드백입니다.")
                            .tailQuestion("다음 꼬리 질문입니다.")
                            .score(10)
                            .build()
            );

            assertAll(
                    () -> assertThat(tailQuestion.getScore()).isEqualTo(10),
                    () -> assertThat(tailQuestion.getQuestion()).isEqualTo("질문입니다."),
                    () -> assertThat(tailQuestion.getFeedback()).isEqualTo("피드백입니다."),
                    () -> assertThat(tailQuestion.getAnswer()).isEqualTo("답변입니다."),
                    () -> assertThat(tailQuestion.getAnswerState()).isEqualTo(AnswerState.COMPLETE)
            );


        }

        @Test
        void 제출_못합니다() {
            var tailQuestion = TailQuestionEntity.builder()
                    .memberId(1L)
                    .interviewId(1L)
                    .interviewQuestionId(1L)
                    .question("질문입니다.")
                    .build();

            tailQuestion.submit(
                    AnswerState.PASS,
                    null,
                    null
            );

            assertAll(
                    () -> assertThat(tailQuestion.getScore()).isEqualTo(0),
                    () -> assertThat(tailQuestion.getQuestion()).isEqualTo("질문입니다."),
                    () -> assertThat(tailQuestion.getFeedback()).isNull(),
                    () -> assertThat(tailQuestion.getAnswer()).isNull(),
                    () -> assertThat(tailQuestion.getAnswerState()).isEqualTo(AnswerState.PASS)
            );
        }
    }
}