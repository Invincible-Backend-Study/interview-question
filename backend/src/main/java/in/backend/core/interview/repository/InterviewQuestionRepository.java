package in.backend.core.interview.repository;


import in.backend.core.interview.entity.InterviewQuestionEntity;
import java.util.List;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InterviewQuestionRepository extends JpaRepository<InterviewQuestionEntity, Long> {

    List<InterviewQuestionEntity> findByInterviewIdAndMemberId(Long interviewId, Long memberId, Pageable pageable);

}
