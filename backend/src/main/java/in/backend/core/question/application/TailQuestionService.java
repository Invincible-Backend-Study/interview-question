package in.backend.core.question.application;


import in.backend.core.auth.domain.Visitor;
import in.backend.core.question.infrastrcuture.TailQuestionManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TailQuestionService {

    private final TailQuestionManager tailQuestionManager;

    public TailQuestionSubmitResult submit(Visitor visitor, TailQuestionSubmitCommand tailQuestionSubmitCommand) {
        return tailQuestionManager.submit(tailQuestionSubmitCommand, visitor.memberId());
    }
}
