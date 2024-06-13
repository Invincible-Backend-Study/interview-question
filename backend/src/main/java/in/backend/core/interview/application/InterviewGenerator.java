package in.backend.core.interview.application;


import in.backend.core.interview.presentation.payload.request.InterviewCreateRequest;
import in.backend.core.interview.presentation.payload.response.InterviewCreateResponse;
import in.backend.core.interview.repository.InterviewWriter;
import in.backend.core.questionset.application.QuestionSetFinder;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class InterviewGenerator {

    private final QuestionSetFinder questionSetFinder;
    private final InterviewWriter interviewWriter;

    public InterviewCreateResponse generate(Long memberId, InterviewCreateRequest interviewCreateRequest) {
        final var questionSet = questionSetFinder.findAndContainQuestions(interviewCreateRequest.questionSetId());
        final var questions = questionSet.extractQuestions(interviewCreateRequest.totalOfProblemCount());

        final var interviewSettings = questionSet.create(
                interviewCreateRequest.tailQuestionDepth(),
                interviewCreateRequest.timeToThink(),
                interviewCreateRequest.timeToAnswer()
        );

        final var interviewId = interviewWriter.write(memberId, interviewSettings, questions);

        return new InterviewCreateResponse(interviewId);
    }


}
