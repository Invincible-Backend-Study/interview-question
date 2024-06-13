package in.backend.core.tailquestion.infrastructure;


import in.backend.core.tailquestion.entity.TailQuestionEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TailQuestionRepository extends JpaRepository<TailQuestionEntity, Long> {

}
