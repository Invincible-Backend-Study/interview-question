package in.backend.core.interview.infrastructure;


import in.backend.core.exception.DomainExceptionCode;
import in.backend.core.interview.entity.InterviewEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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

    public Page<InterviewEntity> read(Long memberId, Pageable pageable) {
        return interviewRepository.findByMemberId(memberId, pageable);
    }

}
