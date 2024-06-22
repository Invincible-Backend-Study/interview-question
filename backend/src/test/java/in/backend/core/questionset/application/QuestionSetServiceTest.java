package in.backend.core.questionset.application;

import static org.assertj.core.api.Assertions.assertThat;

import in.backend.core.questionset.entity.QuestionSetEntity;
import in.backend.global.fixture.QuestionFixture;
import in.backend.global.fixture.QuestionSetFixture;
import in.backend.global.layer.ImplementLayerTest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;

class QuestionSetServiceTest extends ImplementLayerTest {


    @Autowired
    QuestionSetService questionSetService;

    @Test
    void QuestionSet을_조회하면_포함된_문제가_몇개_인지_알_수_있습니다() {
        given(() -> {
            questionRepository.saveAll(
                    QuestionFixture.creates(questionSetRepository.save(QuestionSetFixture.create()), 10)
            );

            questionRepository.saveAll(
                    QuestionFixture.creates(questionSetRepository.save(QuestionSetFixture.create()), 5)
            );
        });

        var expected = questionSetService.find(PageRequest.of(0, 2))
                .toList();

        questionRepository.countByQuestionIds(questionSetRepository.findAll().stream()
                .map(QuestionSetEntity::getId)
                .toList());

        Assertions.assertAll(
                () -> assertThat(expected.get(0).count()).isEqualTo(10),
                () -> assertThat(expected.get(1).count()).isEqualTo(5)
        );

    }

}