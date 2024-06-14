package in.backend.core.interview.application;


import in.backend.core.auth.domain.Visitor;
import in.backend.core.interview.repository.InterviewManager;
import in.backend.core.interview.repository.InterviewQuestionReader;
import in.backend.core.interview.repository.InterviewReader;
import in.backend.core.interview.repository.InterviewWriter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class InterviewService {

    private final InterviewWriter interviewWriter;
    private final InterviewReader interviewReader;
    private final InterviewQuestionReader interviewQuestionReader;
    private final InterviewManager interviewManager;

    public Long create(Visitor visitor, InterviewCreateCommand interviewCreateCommand) {
        return interviewWriter.write(visitor.memberId(), interviewCreateCommand);
    }


    public InterviewQuestionInfo loadByCurrentProblem(Visitor visitor, Long interviewId) {
        var interview = interviewReader.read(interviewId, visitor.memberId());
        var interviewQuestion = interviewQuestionReader.read(
                interviewId,
                visitor.memberId(),
                interview.getCurrentProgressIndex()
        );

        return InterviewQuestionInfo.builder()
                .interviewId(interview.getId())
                .interviewQuestionId(interviewQuestion.getId())
                .question(interviewQuestion.getQuestionContent())
                .remainTailQuestionCount(interviewQuestion.getRemainTailQuestionCount())
                .sequence(interview.getIndex())
                .build();
    }


    public void submit(Visitor visitor, InterviewSubmitCommand interviewSubmitCommand) {
        interviewManager.submit(visitor.memberId(), interviewSubmitCommand);
    }


}
