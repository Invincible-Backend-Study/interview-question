package in.backend.core.interview.repository;

import in.backend.core.interview.entity.InterviewQuestionEntity;
import java.util.Optional;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InterviewQuestionRepository extends JpaRepository<InterviewQuestionEntity, Long> {

    Optional<InterviewQuestionEntity> findByInterviewId(Long interviewId, Pageable pageable);

    Optional<InterviewQuestionEntity> findByIdAndMemberId(Long id, Long memberId);


}
