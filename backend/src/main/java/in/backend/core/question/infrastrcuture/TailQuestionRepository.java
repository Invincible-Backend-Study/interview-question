package in.backend.core.question.infrastrcuture;

import in.backend.core.question.entity.TailQuestionEntity;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TailQuestionRepository extends JpaRepository<TailQuestionEntity, Long> {
    Optional<TailQuestionEntity> findByIdAndMemberId(Long tailQuestionId, Long memberId);


    List<TailQuestionEntity> findByInterviewId(Long interviewId);
}
