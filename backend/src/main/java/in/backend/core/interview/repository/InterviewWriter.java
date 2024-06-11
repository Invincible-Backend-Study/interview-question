package in.backend.core.interview.repository;


import in.backend.core.interview.entity.InterviewEntity;
import in.backend.core.interview.entity.InterviewSettings;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class InterviewWriter {
    private final InterviewRepository interviewRepository;

    public Long write(Long userId, InterviewSettings interviewSettings) {
        return interviewRepository.save(new InterviewEntity(userId, interviewSettings)).getId();
    }
}
