package in.backend.domain.question.entity;


import in.backend.global.entity.BaseEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "questions")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class QuestionEntity extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String content;

    /**
     * 어떤 관리자가 해당 질문을 만들었는지 알고 싶습니다.
     */
    private Long adminId;


    /**
     * 콤마로 링크를 구분합니다.
     */
    private String referenceLinks;


    @Builder
    public QuestionEntity(String content, Long adminId, String referenceLinks) {
        this.content = content;
        this.adminId = adminId;
        this.referenceLinks = referenceLinks;
    }


}
