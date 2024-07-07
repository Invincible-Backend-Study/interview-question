package in.backend.core.questionset.infrastructure;


import in.backend.core.questionset.application.QuestionSetCreator;
import in.backend.core.questionset.entity.QuestionSetEntity;
import in.backend.core.questionset.entity.QuestionSetRules;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class QuestionSetWriter {
    private final QuestionSetRepository questionSetRepository;

    public QuestionSetEntity write(QuestionSetCreator questionSetCreator, Long adminId) {

        var questionSet = QuestionSetEntity.builder()
                .adminId(adminId)
                .title(questionSetCreator.title())
                .description(questionSetCreator.description())
                .questionSetRules(QuestionSetRules.builder()
                        .defaultTimeToAnswer(questionSetCreator.defaultTimeToAnswer())
                        .defaultTimeToThink(questionSetCreator.defaultTimeToThink())
                        .defaultTailQuestionDepth(questionSetCreator.defaultTailQuestionDepth())
                        .build())
                .build();

        return questionSetRepository.save(questionSet);
    }
}
