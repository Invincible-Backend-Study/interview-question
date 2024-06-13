package in.backend.core.tailquestion.infrastructure;


import in.backend.core.interview.entity.InterviewQuestionEntity;
import in.backend.core.tailquestion.entity.TailQuestionEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class TailQuestionWriter {

    private final TailQuestionRepository tailQuestionRepository;

    public Long write(Long memberId, InterviewQuestionEntity interviewQuestion) {

        var tailQuestion = TailQuestionEntity.builder()
                .interviewQuestion(interviewQuestion)
                .memberId(memberId)
                .build();

        tailQuestionRepository.save(tailQuestion);
        
        return tailQuestion.getId();
    }
}
