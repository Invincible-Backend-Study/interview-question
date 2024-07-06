package in.backend.core.question.application;


import in.backend.core.question.application.QuestionWriter.QuestionInfo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class QuestionService {
    private final QuestionWriter questionWriter;

    public Long save(QuestionSaveCommand command) {
        return switch (command.action()) {
            case CREATE -> questionWriter.write(new QuestionInfo(command));
            case UPDATE -> questionWriter.write(command.questionId(), new QuestionInfo(command));
            case DELETE -> throw new UnsupportedOperationException();
        };
    }

}
