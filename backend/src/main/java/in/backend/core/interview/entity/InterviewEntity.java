package in.backend.core.interview.entity;


import in.backend.core.interview.application.InterviewQuestionManager.SolveType;
import in.backend.global.entity.BaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
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
@Table(name = "interview")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class InterviewEntity extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    /**
     * 면접을 시작하는 사용자 id
     */
    @Column(nullable = false)
    private Long memberId;


    @Embedded
    private InterviewSettings settings;


    /**
     * 현재 문제를 풀고 있는 인덱스 번호
     */
    @Column(nullable = false)
    private Integer index;


    /**
     * 인터뷰에서 제공하는 문제 수량
     */
    @Column(nullable = false)
    private Integer size;


    @Builder(access = AccessLevel.PROTECTED)
    protected InterviewEntity(Long memberId, InterviewSettings settings, Integer index, Integer size) {
        this.memberId = memberId;
        this.settings = settings;
        this.index = index;
        this.size = size;
    }


    public static InterviewEntity init(Long memberId, InterviewSettings settings, int size) {
        return InterviewEntity.builder()
                .memberId(memberId)
                .size(size)
                .index(0)
                .settings(settings)
                .build();
    }

    public void submit(int index, SolveType solveType) {
        if (index == this.index) {
            throw new IllegalArgumentException("올바르지 않은 접근입니다.");
        }

        if (solveType == SolveType.COMPLETE) {

        }
    }

    public int getNextIndex() {
        return ++index;
    }
}
