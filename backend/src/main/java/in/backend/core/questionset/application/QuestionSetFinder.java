package in.backend.core.questionset.application;


import in.backend.core.questionset.domain.QuestionSetEntity;
import in.backend.core.questionset.repository.QuestionSetRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class QuestionSetFinder {
    private final QuestionSetRepository questionSetRepository;

    public QuestionSetEntity find(Long id) {
        return questionSetRepository.findById(id)
                .orElseThrow(IllegalArgumentException::new);
    }


    public QuestionSetEntity findAndContainQuestions(Long id) {
        return questionSetRepository.findQuestionSet(id)
                .orElseThrow(IllegalArgumentException::new);
    }


}
