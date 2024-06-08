package in.backend.domain.question.application;


import in.backend.domain.question.entity.QuestionEntity;
import in.backend.domain.question.repository.QuestionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class QuestionWriter {
    private final QuestionRepository questionRepository;
    private final QuestionReader questionReader;


    public Long execute(QuestionInfo questionInfo) {
        return questionRepository.save(questionInfo.toEntity()).getId();
    }

    public Long update(Long questionId, QuestionInfo questionInfo) {
        var question = questionReader.read(questionId);

        question.update(questionInfo);

        return question.getId();
    }

    public record QuestionInfo(
            String content,
            String referenceLinks,
            int sequence
    ) {
        public QuestionEntity toEntity() {
            return QuestionEntity.builder()
                    .content(content)
                    .referenceLinks(referenceLinks)
                    .sequence(sequence)
                    .build();
        }
    }


}
