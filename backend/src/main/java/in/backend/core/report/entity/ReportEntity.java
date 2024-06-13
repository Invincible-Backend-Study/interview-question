package in.backend.core.report.entity;


import in.backend.global.entity.BaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
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
    private Long memberId;


    @Column(nullable = false)
    private Integer totalScore;


    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private ReportState reportState;


    @Column(nullable = false)
    private Integer index;

    @Column(nullable = false)
    private Integer size;


    @OneToMany
    @JoinColumn(nullable = false)
    private List<ReportItemEntity> reportItems = new ArrayList<>();


    @Builder(access = AccessLevel.PROTECTED)
    protected ReportEntity(Long interviewId, Long memberId, Integer size) {
        this.interviewId = interviewId;
        this.memberId = memberId;
        this.totalScore = 0;
        this.size = size;
        this.index = 0;
        this.reportState = ReportState.PROGRESS;
    }

    public static ReportEntity init(Long userId, Long interviewId, Integer size) {
        return ReportEntity.builder()
                .memberId(userId)
                .size(size)
                .interviewId(interviewId)
                .build();
    }


    public void giveUp() {
        reportState = ReportState.GIVE_UP;
    }

    public void solve(Integer solvedIndex, ReportState reportState) {
        // 풀고 있는 문제 번호가 다른 경우
        if (!Objects.equals(solvedIndex, this.index)) {
            throw new IllegalArgumentException();
        }

        // 문제 크기 보다 초과해서 푸는 경우
        if (this.index >= size) {
            throw new IllegalArgumentException();
        }
        this.index += 1;

        reportItems.get(index).solve();
    }
}
