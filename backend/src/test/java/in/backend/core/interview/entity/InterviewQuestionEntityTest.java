package in.backend.core.interview.entity;

import static org.assertj.core.api.Assertions.assertThat;

import in.backend.core.interview.application.InterviewSubmitCommand.AnswerInfo;
import in.backend.core.interview.application.InterviewSubmitCommand.FeedbackInfo;
import in.backend.core.question.entity.AnswerState;
import in.backend.global.fixture.InterviewQuestionFixture;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;


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
}