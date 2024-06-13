package in.backend.core.interview.repository;


import in.backend.core.interview.entity.InterviewQuestionEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class InterviewQuestionReader {

    private final InterviewQuestionRepository interviewQuestionRepository;
    private final InterviewReader interviewReader;

    public InterviewQuestionEntity read(Long interviewId, int index) {
        return interviewQuestionRepository.findByInterviewId(interviewId, PageRequest.of(index, 1))
                .orElseThrow(IllegalArgumentException::new);
    }

    public InterviewQuestionEntity read(Long interviewQuestionId, Long memberId) {
        return interviewQuestionRepository.findByIdAndMemberId(interviewQuestionId, memberId)
                .orElseThrow(IllegalArgumentException::new);
    }

}
