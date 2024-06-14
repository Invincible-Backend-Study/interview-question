package in.backend.core.interview.application;


import in.backend.core.auth.domain.Visitor;
import in.backend.core.interview.repository.InterviewWriter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class InterviewService {

    private final InterviewWriter interviewWriter;

    public void create(Visitor visitor, InterviewCreateCommand interviewCreateCommand) {
        interviewWriter.write(visitor.memberId(), interviewCreateCommand);

    }

}
