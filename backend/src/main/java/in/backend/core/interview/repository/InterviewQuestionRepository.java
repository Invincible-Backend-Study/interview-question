package in.backend.core.interview.repository;


import in.backend.core.interview.entity.InterviewQuestionEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InterviewQuestionRepository extends JpaRepository<InterviewQuestionEntity, Long> {

}
