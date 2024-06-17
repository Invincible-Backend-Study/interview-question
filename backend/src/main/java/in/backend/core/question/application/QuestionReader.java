package in.backend.core.question.application;


import in.backend.core.question.entity.QuestionEntity;
import in.backend.core.question.infrastrcuture.QuestionRepository;
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
