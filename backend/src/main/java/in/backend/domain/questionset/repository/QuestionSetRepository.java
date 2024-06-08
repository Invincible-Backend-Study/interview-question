package in.backend.domain.questionset.repository;


import in.backend.domain.questionset.entity.QuestionSetEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface QuestionSetRepository extends JpaRepository<QuestionSetEntity, Long> {
}
