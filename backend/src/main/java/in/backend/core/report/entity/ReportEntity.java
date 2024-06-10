package in.backend.core.report.entity;


import in.backend.global.entity.BaseEntity;
import in.backend.report.domain.ReportState;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "reports")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ReportEntity extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 어떤 interview를 통해 만들어졌는지 식별
     */
    @Column(nullable = false)
    private Long interviewId;


    /**
     * 누가 만들었는지 확인
     */
    @Column(nullable = false)
    private Long userId;

    private Long totalScore;

    private ReportState reportState;


}
