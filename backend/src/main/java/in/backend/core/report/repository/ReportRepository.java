package in.backend.core.report.repository;

import in.backend.core.report.entity.ReportEntity;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReportRepository extends JpaRepository<ReportEntity, Long> {

    Optional<ReportEntity> findByIdAndMemberId(Long id, Long memberId);
}
