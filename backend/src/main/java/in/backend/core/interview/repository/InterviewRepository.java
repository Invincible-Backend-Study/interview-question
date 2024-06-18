package in.backend.core.interview.repository;

import in.backend.core.interview.entity.InterviewEntity;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;


public interface InterviewRepository extends JpaRepository<InterviewEntity, Long> {

    Optional<InterviewEntity> findByIdAndMemberId(Long interviewId, Long memberId);

    Page<InterviewEntity> findByMemberId(Long memberId, Pageable pageable);
}
