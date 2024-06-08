package in.backend.domain.question.application;


import in.backend.domain.question.entity.QuestionEntity;
import in.backend.domain.question.repository.QuestionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class QuestionReader {
    private final QuestionRepository questionRepository;

    public QuestionEntity read(Long questionId) {
        return questionRepository.findById(questionId)
                .orElseThrow(IllegalArgumentException::new);
    }
}
