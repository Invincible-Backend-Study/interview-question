package in.backend.core.questionset.repository;


import in.backend.core.questionset.domain.QuestionSetEntity;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface QuestionSetRepository extends JpaRepository<QuestionSetEntity, Long> {


    @Query("""
                select Q from QuestionSetEntity Q
                    join Q.questions.value questions
                    where Q.id = :id
                    order by questions.sequence asc
                   
            """)
    Optional<QuestionSetEntity> findQuestionSet(@Param("id") Long id);
}
