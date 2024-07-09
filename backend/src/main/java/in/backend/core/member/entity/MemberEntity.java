package in.backend.core.member.entity;


import in.backend.global.entity.BaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.SQLRestriction;

@Getter
@Entity
@Table(name = "members")
@SQLRestriction("is_deleted = false")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@SQLDelete(sql = "UPDATE MemberEntity SET is_deleted = true WHERE id = ?")
public class MemberEntity extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @Column(nullable = false)
    private String nickname;

    private String avatarUrl;

    private String providerId;

    @Column(nullable = false)
    private boolean isDeleted;

    @Builder
    public MemberEntity(String nickname, String avatarUrl, String providerId) {
        this.nickname = nickname;
        this.avatarUrl = avatarUrl;
        this.providerId = providerId;
        this.isDeleted = false;
    }

    public void withDraw() {
        isDeleted = true;
        avatarUrl = "";
        providerId = "";
    }
}
