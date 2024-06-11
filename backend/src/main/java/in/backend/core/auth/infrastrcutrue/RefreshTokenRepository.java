package in.backend.core.auth.infrastrcutrue;

import in.backend.core.auth.entity.RefreshTokenEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RefreshTokenRepository extends JpaRepository<RefreshTokenEntity, Long> {
}
