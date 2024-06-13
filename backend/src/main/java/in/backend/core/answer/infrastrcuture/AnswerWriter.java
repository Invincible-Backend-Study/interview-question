package in.backend.core.answer.infrastrcuture;

import in.backend.core.answer.entity.AnswerEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class AnswerWriter {
    private final AnswerRepository answerRepository;

    public Long write(AnswerEntity answer) {
        return answerRepository.save(answer).getId();
    }
}
