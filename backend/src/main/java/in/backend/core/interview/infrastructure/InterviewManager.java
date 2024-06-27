package in.backend.core.interview.infrastructure;


import in.backend.core.interview.application.InterviewSubmitCommand;
import in.backend.core.interview.application.InterviewSubmitResult;
import in.backend.core.question.entity.TailQuestionEntity;
import in.backend.core.question.infrastrcuture.TailQuestionRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class InterviewManager {

    private final InterviewReader interviewReader;
    private final InterviewQuestionReader interviewQuestionReader;
    private final TailQuestionRepository tailQuestionRepository;


    public InterviewSubmitResult submit(Long memberId, InterviewSubmitCommand interviewSubmitCommand) {
        log.info("{}", interviewSubmitCommand);
        var interview = interviewReader.read(interviewSubmitCommand.interviewId(), memberId);
        interview.increaseIndex(interviewSubmitCommand.currentIndex());

        var interviewQuestion = interviewQuestionReader.read(interviewSubmitCommand.interviewQuestionId());

        interviewQuestion.submit(
                interviewSubmitCommand.answerState(),
                interviewSubmitCommand.answer(),
                interviewSubmitCommand.feedback()
        );

        return interviewQuestion.createTailQuestion()
                .map(tailQuestionRepository::save)
                .map(TailQuestionEntity::getId)
                .map(InterviewSubmitResult::new)
                .orElseGet(InterviewSubmitResult::empty);
    }
}
