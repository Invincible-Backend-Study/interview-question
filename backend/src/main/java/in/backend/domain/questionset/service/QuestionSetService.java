package in.backend.domain.questionset.service;

import in.backend.domain.questionset.application.QuestionSetFinder;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class QuestionSetService {
    private final QuestionSetFinder questionSetFinder;


    public void appendQuestion(Long questionSetId) {
        questionSetFinder.find(questionSetId);
    }
}
