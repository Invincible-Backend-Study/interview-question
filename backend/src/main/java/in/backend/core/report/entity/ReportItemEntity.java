package in.backend.core.report.entity;


import in.backend.core.answer.entity.AnswerEntity;
import in.backend.global.entity.BaseEntity;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@Table(name = "report_item")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ReportItemEntity extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long reportId;

    private Long userId;

    private Long questionId;

    @OneToOne
    private AnswerEntity answer;

    @Embedded
    private AIFeedback aiFeedback;


    @Builder
    public ReportItemEntity(Long reportId, Long userId, Long questionId) {
        this.reportId = reportId;
        this.userId = userId;
        this.questionId = questionId;
    }

    public void solve() {
    }
}
