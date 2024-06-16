package in.backend.core.interview.repository;


import in.backend.core.interview.application.InterviewSubmitCommand;
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


    public void submit(Long memberId, InterviewSubmitCommand interviewSubmitCommand) {
        var interview = interviewReader.read(interviewSubmitCommand.interviewId(), memberId);
        interview.increaseIndex(interviewSubmitCommand.currentIndex());

        var interviewQuestion = interviewQuestionReader.read(interviewSubmitCommand.interviewQuestionId());

        interviewQuestion.submit(
                interviewSubmitCommand.answerState(),
                interviewSubmitCommand.answer(),
                interviewSubmitCommand.feedback()
        );

        interviewQuestion.createTailQuestion()
                .ifPresent(tailQuestionRepository::save);

    }
}
