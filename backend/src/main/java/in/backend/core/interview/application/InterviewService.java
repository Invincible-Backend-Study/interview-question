package in.backend.core.interview.application;


import static java.util.stream.Collectors.toMap;

import in.backend.core.auth.domain.Visitor;
import in.backend.core.interview.repository.InterviewManager;
import in.backend.core.interview.repository.InterviewQuestionReader;
import in.backend.core.interview.repository.InterviewReader;
import in.backend.core.interview.repository.InterviewWriter;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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

        return InterviewQuestionInfo.from(interview, interviewQuestion);

    }


    public InterviewSubmitResult submit(Visitor visitor, InterviewSubmitCommand interviewSubmitCommand) {
        return interviewManager.submit(visitor.memberId(), interviewSubmitCommand);
    }


    public Page<MyInterviewResult> search(Visitor visitor, Pageable pageable) {
        var interviews = interviewReader.read(visitor.memberId(), pageable);
        var interviewQuestionCountTable = interviewQuestionReader.readByCount(interviews.toList())
                .stream()
                .collect(toMap(InterviewQuestionCount::interviewId, InterviewQuestionCount::count));

        return interviews.map(interview -> MyInterviewResult.from(
                interview,
                interviewQuestionCountTable.get(interview.getId())
        ));

    }
}
