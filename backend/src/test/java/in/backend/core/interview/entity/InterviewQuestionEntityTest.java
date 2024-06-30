package in.backend.core.interview.entity;

import static in.backend.core.exception.DomainExceptionCode.INTERVIEW_QUESTION_CREATE_FAIL;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertAll;

import in.backend.core.exception.DomainException;
import in.backend.core.interview.application.InterviewSubmitCommand.AnswerInfo;
import in.backend.core.interview.application.InterviewSubmitCommand.FeedbackInfo;
import in.backend.core.question.entity.AnswerState;
import in.backend.global.fixture.InterviewQuestionFixture;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullSource;
import org.junit.jupiter.params.provider.ValueSource;


@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
class InterviewQuestionEntityTest {


    @Test
    void 질문에_대한_답변을_하지_않는_경우_꼬리질문을_만들_수_없습니다() {
        var interviewQuestion = InterviewQuestionFixture.create();

        assertThat(interviewQuestion.createTailQuestion().isEmpty()).isTrue();
    }

    @Test
    void 질문에_대한_답변을_하면_꼬리질문을_만들_수_있습니다() {
        var interviewQuestion = InterviewQuestionFixture.create(2);

        interviewQuestion.submit(
                AnswerState.COMPLETE,
                new AnswerInfo("답변", 1),
                new FeedbackInfo("피드백", "꼬리 질문", 100)
        );

        var tailQuestion = interviewQuestion.createTailQuestion().orElseThrow(IllegalArgumentException::new);

        tailQuestion.submit(
                AnswerState.COMPLETE,
                new AnswerInfo("답변", 1),
                new FeedbackInfo("피드백", "꼬리 질문", 10)
        );
        assertThat(interviewQuestion.createTailQuestion(tailQuestion).isPresent()).isTrue();
    }


    @Nested
    class 인터뷰_질문을 {

        @Test
        void _성공적으로_생성_합니다() {
            var interviewQuestion = InterviewQuestionEntity.builder()
                    .questionContent("질문 내용")
                    .remainTailQuestionCount(1)
                    .memberId(1L)
                    .questionId(1L)
                    .build();

            assertAll(
                    () -> assertThat(interviewQuestion.getMemberId()).isEqualTo(1L),
                    () -> assertThat(interviewQuestion.getRemainTailQuestionCount()).isEqualTo(1L),
                    () -> assertThat(interviewQuestion.getQuestionId()).isEqualTo(1L),
                    () -> assertThat(interviewQuestion.getQuestionContent()).isEqualTo("질문 내용")
            );
        }

        @Test
        void _성공적으로_답변합니다() {
            var question = "질문입니다.";
            var remainTailQuestionCount = 10;
            var interviewQuestion = InterviewQuestionFixture.create(question, remainTailQuestionCount);

            interviewQuestion.submit(
                    AnswerState.COMPLETE,
                    AnswerInfo.builder()
                            .content("답변했습니다")
                            .timeToAnswer(1)
                            .build(),
                    FeedbackInfo.builder()
                            .score(100)
                            .tailQuestion("꼬리질문입니다.")
                            .aiFeedback("피드백했습니다.")
                            .build()
            );

            assertAll(
                    () -> assertThat(interviewQuestion.getQuestionContent()).isEqualTo("질문입니다."),
                    () -> assertThat(interviewQuestion.getQuestionId()).isEqualTo(1L),
                    () -> assertThat(interviewQuestion.getRemainTailQuestionCount()).isEqualTo(10),
                    () -> assertThat(interviewQuestion.getAnswer()).isEqualTo("답변했습니다"),
                    () -> assertThat(interviewQuestion.getFeedback()).isEqualTo("피드백했습니다."),
                    () -> assertThat(interviewQuestion.getScore()).isEqualTo(100),
                    () -> assertThat(interviewQuestion.getAnswerState()).isEqualTo(AnswerState.COMPLETE)
            );
        }

        @Test
        void _답변하지_못합니다() {
            var question = "질문입니다.";
            var remainTailQuestionCount = 10;
            var interviewQuestion = InterviewQuestionFixture.create(question, remainTailQuestionCount);

            interviewQuestion.submit(
                    AnswerState.PASS,
                    null,
                    null
            );

            assertAll(
                    () -> assertThat(interviewQuestion.getAnswer()).isNull(),
                    () -> assertThat(interviewQuestion.getRemainTailQuestionCount()).isEqualTo(10),
                    () -> assertThat(interviewQuestion.getAnswer()).isNull(),
                    () -> assertThat(interviewQuestion.getFeedback()).isNull(),
                    () -> assertThat(interviewQuestion.getScore()).isEqualTo(0),
                    () -> assertThat(interviewQuestion.getAnswerState()).isEqualTo(AnswerState.PASS)
            );

        }


        @Nested
        class _성공적으로_생성하지_못하는_조건은 {

            @ParameterizedTest
            @NullSource
            @ValueSource(strings = {" ", ""})
            void _질문_내용이_없거나_비어_있는_경우_입니다(String input) {
                assertThatThrownBy(() ->
                        InterviewQuestionEntity.builder()
                                .questionContent(input)
                                .remainTailQuestionCount(1)
                                .memberId(1L)
                                .questionId(1L)
                                .build())
                        .isInstanceOf(DomainException.class)
                        .hasMessage(INTERVIEW_QUESTION_CREATE_FAIL.getMessage());
            }


            @ParameterizedTest
            @NullSource
            @ValueSource(ints = {-1, -2, -3})
            void _꼬리질문_개수가_음수인_경우입니다(Integer input) {
                assertThatThrownBy(() -> InterviewQuestionEntity.builder()
                        .questionContent("질문 내용")
                        .remainTailQuestionCount(input)
                        .memberId(1L)
                        .questionId(1L)
                        .build());
            }
        }
    }

    @Nested
    class 인터뷰_질문을_답변한_후 {


        @Test
        void 꼬리질문을_생성할_수_있습니다() {
            var question = "질문입니다.";
            var remainTailQuestionCount = 10;
            var interviewQuestion = InterviewQuestionFixture.create(question, remainTailQuestionCount);

            interviewQuestion.submit(
                    AnswerState.COMPLETE,
                    AnswerInfo.builder()
                            .content("답변했습니다")
                            .timeToAnswer(1)
                            .build(),
                    FeedbackInfo.builder()
                            .score(100)
                            .tailQuestion("꼬리질문입니다.")
                            .aiFeedback("피드백했습니다.")
                            .build()
            );

            var tailQuestion = interviewQuestion.createTailQuestion();

            assertAll(
                    () -> assertThat(interviewQuestion.getRemainTailQuestionCount()).isEqualTo(9),
                    () -> assertThat(tailQuestion.isPresent()).isTrue()
            );


        }

        @Test
        void 답변하지_못한_경우_꼬리질문을_만들지_못합니다() {
            var question = "질문입니다.";
            var remainTailQuestionCount = 10;
            var interviewQuestion = InterviewQuestionFixture.create(question, remainTailQuestionCount);

            interviewQuestion.submit(
                    AnswerState.PASS,
                    null,
                    null
            );

            var tailQuestion = interviewQuestion.createTailQuestion();

            assertAll(
                    () -> assertThat(interviewQuestion.getRemainTailQuestionCount()).isEqualTo(10),
                    () -> assertThat(tailQuestion.isPresent()).isFalse()
            );
        }

        @Test
        void 잔여_꼬리질문_수가_없다면_꼬리질문을_만들_수_없습니다() {
            var question = "질문입니다.";
            var remainTailQuestionCount = 0;
            var interviewQuestion = InterviewQuestionFixture.create(question, remainTailQuestionCount);

            interviewQuestion.submit(
                    AnswerState.COMPLETE,
                    AnswerInfo.builder()
                            .content("답변했습니다")
                            .timeToAnswer(1)
                            .build(),
                    FeedbackInfo.builder()
                            .score(100)
                            .tailQuestion("꼬리질문입니다.")
                            .aiFeedback("피드백했습니다.")
                            .build()
            );

            var tailQuestion = interviewQuestion.createTailQuestion();

            assertAll(
                    () -> assertThat(interviewQuestion.getRemainTailQuestionCount()).isEqualTo(0),
                    () -> assertThat(tailQuestion.isPresent()).isFalse()
            );
        }

    }


}