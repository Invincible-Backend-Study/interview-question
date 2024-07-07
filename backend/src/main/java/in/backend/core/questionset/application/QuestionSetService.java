package in.backend.core.questionset.application;


import static java.util.stream.Collectors.toMap;

import in.backend.core.auth.domain.Admin;
import in.backend.core.question.application.QuestionReader;
import in.backend.core.questionset.entity.QuestionSetEntity;
import in.backend.core.questionset.infrastructure.QuestionSetReader;
import in.backend.core.questionset.infrastructure.QuestionSetWriter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Slf4j
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

    public QuestionSetEntity create(QuestionSetCreator questionSetCreator, Admin admin) {
        return questionSetWriter.write(questionSetCreator, admin.id());
    }

    public Page<QuestionSetEntity> find(Pageable pageable, Admin admin) {
        log.info("question set search by {}", admin);
        return questionSetReader.read(pageable);
    }

}
