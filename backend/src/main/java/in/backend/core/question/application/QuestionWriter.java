package in.backend.core.question.application;


import in.backend.core.question.entity.QuestionEntity;
import in.backend.core.question.repository.QuestionRepository;
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

    public void update(Long questionId, QuestionInfo questionInfo) {
        var question = questionReader.read(questionId);

        question.update(questionInfo);
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
