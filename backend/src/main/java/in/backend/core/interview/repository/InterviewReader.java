package in.backend.core.interview.repository;


import in.backend.core.exception.DomainExceptionCode;
import in.backend.core.interview.entity.InterviewEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class InterviewReader {
    private final InterviewRepository interviewRepository;

    public InterviewEntity read(Long interviewId, Long memberId) {
        return interviewRepository.findByIdAndMemberId(interviewId, memberId)
                .orElseThrow(DomainExceptionCode.INTERVIEW_NOT_FOUND::create);
    }
}
