package in.backend.core.auth.entity;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.NoArgsConstructor;


@Entity
@NoArgsConstructor
@Table(name = "REFRESH_TOKENS")
public class RefreshTokenEntity {
    @Id
    private Long id;

    @Column(length = 700)
    private String token;


    public RefreshTokenEntity(Long memberId, String token) {
        this.id = memberId;
        this.token = token;
    }


    public void update(String token) {
        this.token = token;
    }
}