package in.backend.core.interview.repository;

import in.backend.core.interview.entity.InterviewEntity;
import org.springframework.data.jpa.repository.JpaRepository;


public interface InterviewRepository extends JpaRepository<InterviewEntity, Long> {
}
