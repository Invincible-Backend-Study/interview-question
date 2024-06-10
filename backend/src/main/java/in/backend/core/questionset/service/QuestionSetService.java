package in.backend.core.questionset.service;

import in.backend.core.questionset.application.QuestionSetFinder;
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
