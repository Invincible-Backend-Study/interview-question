package in.backend.core.questionset.application;


import in.backend.core.questionset.entity.QuestionSetEntity;
import in.backend.core.questionset.repository.QuestionSetRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class QuestionSetFinder {
    private final QuestionSetRepository questionSetRepository;

    public QuestionSetEntity find(Long id) {
        return questionSetRepository.findById(id)
                .orElseThrow(IllegalArgumentException::new);
    }

    public void validate(Long id) {
        if (!questionSetRepository.existsById(id)) {
            throw new IllegalArgumentException();
        }
    }
}
