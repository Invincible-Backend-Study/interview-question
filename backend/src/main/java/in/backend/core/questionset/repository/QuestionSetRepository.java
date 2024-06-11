package in.backend.core.questionset.repository;


import in.backend.core.questionset.entity.QuestionSetEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface QuestionSetRepository extends JpaRepository<QuestionSetEntity, Long> {
}
