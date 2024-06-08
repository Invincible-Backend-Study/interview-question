package in.backend.domain.questionset.application;


import in.backend.domain.questionset.repository.QuestionSetRepository;
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
