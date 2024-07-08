package in.backend.core.question.infrastrcuture;

import in.backend.core.question.entity.QuestionEntity;
import in.backend.core.questionset.application.QuestionSetProblemCount;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface QuestionRepository extends JpaRepository<QuestionEntity, Long> {

    @Query("""
                select new in.backend.core.questionset.application.QuestionSetProblemCount(Q.questionSet.id, count(Q.questionSet.id)) from QuestionEntity Q
                where Q.questionSet.id in :questionSetIds
                group by Q.questionSet.id
            """)
    List<QuestionSetProblemCount> countByQuestionIds(@Param("questionSetIds") List<Long> questionSetIds);


    @Query("select Q from QuestionEntity Q where Q.questionSet.id = :questionSetId")
    List<QuestionEntity> findByQuestionSetId(@Param("questionSetId") Long questionSetId);
}
