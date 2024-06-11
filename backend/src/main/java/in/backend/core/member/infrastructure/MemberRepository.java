package in.backend.core.member.infrastructure;


import in.backend.core.member.entity.MemberEntity;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<MemberEntity, Long> {

    boolean existsByProviderId(String providerId);

    Optional<MemberEntity> findByProviderId(String providerId);

}
