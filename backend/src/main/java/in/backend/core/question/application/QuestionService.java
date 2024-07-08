package in.backend.core.question.application;


import in.backend.core.auth.domain.Admin;
import in.backend.core.question.application.QuestionWriter.QuestionInfo;
import in.backend.core.question.entity.QuestionEntity;
import in.backend.core.questionset.infrastructure.QuestionSetReader;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;


@Slf4j
@Service
@RequiredArgsConstructor
public class QuestionService {
    private final QuestionSetReader questionSetReader;
    private final QuestionReader questionReader;
    private final QuestionWriter questionWriter;

    public Long save(QuestionSaveCommand command, Admin admin) {
        log.info("admin save questions {}", admin);

        var questionSet = questionSetReader.readOne(command.questionSetId());
        return switch (command.action()) {
            case CREATE -> questionWriter.write(new QuestionInfo(command), questionSet);
            case UPDATE -> questionWriter.write(command.questionId(), new QuestionInfo(command));
            case DELETE -> throw new UnsupportedOperationException();
        };
    }

    public List<QuestionEntity> find(Long questionSetId, Admin admin) {
        log.info("admin get questions {}", admin);
        return questionReader.readAll(questionSetId);
    }
}
