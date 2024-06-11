package in.backend.core.report.repository;

import in.backend.core.report.entity.ReportItemEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReportItemRepository extends JpaRepository<ReportItemEntity, Long> {
}
