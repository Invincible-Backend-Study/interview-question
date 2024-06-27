package in.backend.core.interview.repository;


import static org.assertj.core.api.Assertions.assertThatThrownBy;

import in.backend.core.exception.DomainException;
import in.backend.core.interview.application.InterviewCreateCommand;
import in.backend.core.interview.infrastructure.InterviewWriter;
import in.backend.global.fixture.QuestionFixture;
import in.backend.global.fixture.QuestionSetFixture;
import in.backend.global.layer.ImplementLayerTest;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.beans.factory.annotation.Autowired;

class InterviewWriterTest extends ImplementLayerTest {

    @Autowired
    InterviewWriter interviewWriter;


    @Test
    void 인터뷰를_생성하면_요청한_개수만큼_질문을_만듭니다() {
        var questionSetId = given(() -> {
            var questionSet = questionSetRepository.save(QuestionSetFixture.create());
            questionRepository.saveAll(QuestionFixture.creates(questionSet, 10));
            return questionSet.getId();
        });

        interviewWriter.write(2L, InterviewCreateCommand.builder()
                .questionSetId(questionSetId)
                .totalOfProblemCount(5)
                .build()
        );

        Assertions.assertThat(interviewRepository.count()).isEqualTo(1);
        Assertions.assertThat(interviewQuestionRepository.count()).isEqualTo(5);
    }


    @ParameterizedTest
    @ValueSource(ints = {-1, 0, 11, 100})
    void 인터뷰를_생성하려고_할_때_질문의_개수가_요청한_개수보다_없거나_초과하는_경우_에러가_발생합니다(int total) {
        var questionSet = questionSetRepository.save(QuestionSetFixture.create());

        questionRepository.saveAll(QuestionFixture.creates(questionSet, 10));

        assertThatThrownBy(() -> interviewWriter.write(1L, InterviewCreateCommand.builder()
                .questionSetId(questionSet.getId())
                .totalOfProblemCount(total)
                .build()
        )).isInstanceOf(DomainException.class);
    }

}