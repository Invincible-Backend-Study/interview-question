package in.backend.core.interview.entity;


import static in.backend.core.exception.DomainExceptionCode.INTERVIEW_STATE_DID_NOT_MATCH;
import static in.backend.core.exception.DomainExceptionCode.INTERVIEW_STATE_IS_DONE;

import in.backend.global.entity.BaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Embedded;
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

    @Column(nullable = false)
    private String title;

    /**
     * 현재 진행 중인 면접 질문 번호
     */
    @Column(nullable = false)
    private int index;


    /**
     * 인터뷰 면접 질문의 수
     */
    @Column(nullable = false)
    private int size;

    /**
     * 인터뷰에서 사용하는 설정 값
     */
    @Embedded
    private InterviewSettings settings;

    @Enumerated(EnumType.STRING)
    private InterviewState interviewState;


    @Builder(access = AccessLevel.PROTECTED)
    protected InterviewEntity(Long memberId, int index, String title, int size, InterviewSettings settings) {
        this.memberId = memberId;
        this.index = index;
        this.size = size;
        this.title = title;
        this.settings = settings;
        this.interviewState = InterviewState.PROGRESS;
    }

    public static InterviewEntity init(Long memberId, String title, int size, InterviewSettings settings) {
        return InterviewEntity.builder()
                .memberId(memberId)
                .title(title)
                .size(size)
                .settings(settings)
                .build();
    }

    public int getTailQuestionDepth() {
        return this.settings.getTailQuestionDepth();
    }

    public int getCurrentProgressIndex() {
        INTERVIEW_STATE_IS_DONE.invokeByCondition(interviewState != InterviewState.PROGRESS);

        return index;
    }

    public void increaseIndex(int currentIndex) {
        INTERVIEW_STATE_IS_DONE.invokeByCondition(interviewState != InterviewState.PROGRESS);
        INTERVIEW_STATE_DID_NOT_MATCH.invokeByCondition(index != currentIndex);
        INTERVIEW_STATE_IS_DONE.invokeByCondition(index == size);

        index = index + 1;

        if (index == size) {
            interviewState = InterviewState.DONE;
        }
    }
}
