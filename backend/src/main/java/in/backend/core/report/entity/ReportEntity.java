package in.backend.core.report.entity;


import in.backend.global.entity.BaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
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


    @Column(nullable = false)
    private Integer totalScore;


    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private ReportState reportState;


    @Builder(access = AccessLevel.PROTECTED)
    public ReportEntity(Long interviewId, Long userId, ReportState reportState) {
        this.interviewId = interviewId;
        this.userId = userId;
        this.totalScore = 0;
        this.reportState = reportState;
    }

    public static ReportEntity init(Long userId, Long interviewId) {
        return ReportEntity.builder()
                .userId(userId)
                .interviewId(interviewId)
                .build();
    }


}
