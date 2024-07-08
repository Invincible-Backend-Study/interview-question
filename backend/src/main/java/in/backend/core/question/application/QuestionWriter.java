package in.backend.core.question.application;


import in.backend.core.question.entity.QuestionEntity;
import in.backend.core.question.infrastrcuture.QuestionRepository;
import in.backend.core.questionset.entity.QuestionSetEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class QuestionWriter {
    private final QuestionRepository questionRepository;
    private final QuestionReader questionReader;


    public Long write(QuestionInfo questionInfo, QuestionSetEntity questionSet) {
        var question = QuestionEntity.builder()
                .questionSet(questionSet)
                .content(questionInfo.content)
                .sequence(questionInfo.sequence)
                .build();

        return questionRepository.save(question)
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

    }


}
