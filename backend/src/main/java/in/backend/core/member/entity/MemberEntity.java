package in.backend.core.member.entity;


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

@Getter
@Entity
@Table(name = "members")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class MemberEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @Column(nullable = false)
    private String nickname;


    private String avatarUrl;

    private String providerId;


    @Builder
    public MemberEntity(String nickname, String avatarUrl, String providerId) {
        this.nickname = nickname;
        this.avatarUrl = avatarUrl;
        this.providerId = providerId;
    }
}
