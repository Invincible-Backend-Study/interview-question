package in.backend.core.question.application;


import in.backend.core.question.entity.QuestionEntity;
import in.backend.core.question.infrastrcuture.QuestionRepository;
import in.backend.core.questionset.application.QuestionSetProblemCount;
import in.backend.core.questionset.entity.QuestionSetEntity;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class QuestionReader {
    private final QuestionRepository questionRepository;


    public List<QuestionEntity> readAll(Long questionSetId) {
        return questionRepository.findByQuestionSetId(questionSetId);
    }

    public QuestionEntity read(Long questionId) {
        return questionRepository.findById(questionId)
                .orElseThrow(IllegalArgumentException::new);
    }


    public List<QuestionSetProblemCount> readByCount(List<QuestionSetEntity> questionSets) {
        var questionSetIds = questionSets.stream()
                .map(QuestionSetEntity::getId)
                .toList();

        return questionRepository.countByQuestionIds(questionSetIds);
    }
}
