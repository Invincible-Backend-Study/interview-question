package in.backend.core.questionset.infrastructure;


import in.backend.core.exception.DomainExceptionCode;
import in.backend.core.questionset.entity.QuestionSetEntity;
import in.backend.core.questionset.repository.QuestionSetRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class QuestionSetReader {

    private final QuestionSetRepository questionSetRepository;


    public QuestionSetEntity read(Long questionSetId) {
        return questionSetRepository.findByIdContainsQuestions(questionSetId)
                .orElseThrow(DomainExceptionCode.QUESTION_SET_NOT_FOUND::create);
    }

}
