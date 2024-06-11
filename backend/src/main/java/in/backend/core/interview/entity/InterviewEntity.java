package in.backend.core.interview.entity;


import in.backend.global.entity.BaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AccessLevel;
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
    private Long userId;


    @Embedded
    private InterviewSettings settings;


    public InterviewEntity(Long userId, InterviewSettings settings) {
        this.userId = userId;
        this.settings = settings;
    }
}
