package in.backend.core.interview.infrastructure;


import in.backend.core.interview.application.InterviewQuestionCount;
import in.backend.core.interview.entity.InterviewQuestionEntity;
import java.util.List;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface InterviewQuestionRepository extends JpaRepository<InterviewQuestionEntity, Long> {

    List<InterviewQuestionEntity> findByInterviewIdAndMemberId(Long interviewId, Long memberId, Pageable pageable);

    List<InterviewQuestionEntity> findByInterviewIdAndMemberId(Long interviewId, Long memberId);


    @Query("""
                select new in.backend.core.interview.application.InterviewQuestionCount(I.interviewId, count(I.interviewId)) 
                from InterviewQuestionEntity I
                where I.interviewId in :interviewIds
                group by I.interviewId
            """)
    List<InterviewQuestionCount> countByInterviewIds(@Param("interviewIds") List<Long> interviewIds);
}
