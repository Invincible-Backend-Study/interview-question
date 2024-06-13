package in.backend.core.interview.application;


import in.backend.core.answer.infrastrcuture.AnswerWriter;
import in.backend.core.interview.presentation.payload.request.InterviewQuestionSubmitRequest;
import in.backend.core.interview.presentation.payload.response.InterviewQuestionSearchResponse;
import in.backend.core.interview.repository.InterviewQuestionReader;
import in.backend.core.interview.repository.InterviewReader;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class InterviewQuestionManager {

    private final InterviewReader interviewReader;
    private final InterviewQuestionReader interviewQuestionReader;
    private final AnswerWriter answerWriter;

    public void submit(Long memberId, InterviewQuestionSubmitRequest request) {

        // submit
        // interview get
        // answer write
        // interviewQuestion
        // interviewQuestionManage.submit(interviewQuestion, answer)

        var interviewQuestion = interviewQuestionReader.read(request.interviewQuestionId(), memberId);

        var answer = request.toAnswerEntity(memberId);

        answerWriter.write(answer);

        interviewQuestion.consumeTailDepthCount();

    }


    /**
     * 풀어야 할 문제에 대한 정보를 가져옴
     */
    public InterviewQuestionSearchResponse loadInterviewQuestion(Long interviewId, Long memberId) {
        var interview = interviewReader.read(interviewId, memberId);
        var currentInterviewQuestion = interviewQuestionReader.read(interviewId, interview.getIndex());

        return InterviewQuestionSearchResponse.from(currentInterviewQuestion);
    }

    public enum SolveType {
        COMPLETE,
        PASS
    }
}
