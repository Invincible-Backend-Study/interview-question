package in.backend.core.question.infrastrcuture;


import in.backend.core.interview.repository.InterviewQuestionReader;
import in.backend.core.question.application.TailQuestionSubmitCommand;
import in.backend.core.question.application.TailQuestionSubmitResult;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class TailQuestionManager {

    private final InterviewQuestionReader interviewQuestionReader;
    private final TailQuestionReader tailQuestionReader;
    private final TailQuestionRepository tailQuestionRepository;

    public TailQuestionSubmitResult submit(TailQuestionSubmitCommand tailQuestionSubmitCommand, Long memberId) {
        var tailQuestion = tailQuestionReader.read(tailQuestionSubmitCommand.tailQuestionId(), memberId);

        tailQuestion.submit(
                tailQuestionSubmitCommand.answerState(),
                tailQuestionSubmitCommand.answerInfo(),
                tailQuestionSubmitCommand.feedbackInfo()
        );

        return interviewQuestionReader.read(tailQuestionSubmitCommand.interviewQuestionId())
                .createTailQuestion(tailQuestion)
                .map(tailQuestionRepository::save)
                .map(TailQuestionSubmitResult::create)
                .orElseGet(TailQuestionSubmitResult::empty);

    }

}
