package in.backend.core.questionset.application;


import static java.util.stream.Collectors.toMap;

import in.backend.core.question.application.QuestionReader;
import in.backend.core.questionset.entity.QuestionSetEntity;
import in.backend.core.questionset.infrastructure.QuestionSetReader;
import in.backend.core.questionset.infrastructure.QuestionSetWriter;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class QuestionSetService {

    private final QuestionSetReader questionSetReader;
    private final QuestionSetWriter questionSetWriter;
    private final QuestionReader questionReader;

    public Page<QuestionSetInfo> find(Pageable pageable) {
        var questionSets = questionSetReader.read(pageable);
        var questionSetProblemCounts = questionReader.readByCount(questionSets.toList())
                .stream()
                .collect(toMap(QuestionSetProblemCount::id, QuestionSetProblemCount::count));

        return questionSets
                .map(questionSet -> QuestionSetInfo.from(
                        questionSet,
                        questionSetProblemCounts.get(questionSet.getId())
                ));
    }

    public QuestionSetEntity create(QuestionSetCreator questionSetCreator, Long adminId) {
        return questionSetWriter.write(questionSetCreator, adminId);
    }
}
