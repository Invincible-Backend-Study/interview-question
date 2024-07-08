package in.backend.core.question.application;


import in.backend.core.question.entity.QuestionEntity;
import in.backend.core.question.infrastrcuture.QuestionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class QuestionWriter {
    private final QuestionRepository questionRepository;
    private final QuestionReader questionReader;


    public Long write(QuestionInfo questionInfo) {
        return questionRepository.save(questionInfo.toEntity())
                .getId();
    }

    public Long write(Long questionId, QuestionInfo questionInfo) {
        var question = questionReader.read(questionId);

        question.update(questionInfo);

        return question.getId();
    }

    public record QuestionInfo(
            String content,
            int sequence
    ) {

        public QuestionInfo(QuestionSaveCommand command) {
            this(command.question(), command.sequence());
        }

        public QuestionEntity toEntity() {
            return QuestionEntity.builder()
                    .content(content)
                    .sequence(sequence)
                    .build();
        }
    }


}
