package in.backend.core.answer.infrastrcuture;

import in.backend.core.answer.entity.AnswerEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AnswerRepository extends JpaRepository<AnswerEntity, Long> {

}
