package in.backend.core.questionset.application;


import in.backend.core.questionset.entity.QuestionSetEntity;
import in.backend.core.questionset.infrastructure.QuestionSetReader;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class QuestionSetService {

    private final QuestionSetReader questionSetReader;

    public Page<QuestionSetEntity> find(Pageable pageable) {
        return questionSetReader.read(pageable);
    }
}
