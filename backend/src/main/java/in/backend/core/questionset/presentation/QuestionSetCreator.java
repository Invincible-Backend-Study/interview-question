package in.backend.core.questionset.presentation;


import in.backend.core.questionset.application.QuestionSetInfo;
import in.backend.core.questionset.infrastructure.QuestionSetRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class QuestionSetCreator {
    private final QuestionSetRepository questionSetRepository;

    public void create(QuestionSetInfo questionSetInfo) {
        questionSetRepository.save(questionSetInfo.toEntity());
    }

}
