package in.backend.core.tailquestion.application;


import in.backend.core.interview.repository.InterviewQuestionReader;
import in.backend.core.tailquestion.entity.TailQuestionEntity;
import in.backend.core.tailquestion.presentation.payload.request.TailQuestionSubmitRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TailQuestionService {

    private final InterviewQuestionReader interviewQuestionReader;


    public void submit(Long memberId, TailQuestionSubmitRequest request) {
        var interviewQuestion = interviewQuestionReader.read(request.interviewQuestionId(), memberId);

        var tailQuestion = TailQuestionEntity.builder()
                .interviewQuestion(interviewQuestion)
                .memberId(memberId)
                .content(request.tailQuestionContent())
                .build();
        interviewQuestion.consumeTailDepthCount();


    }
}
