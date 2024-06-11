package in.backend.core.report.repository;


import in.backend.core.report.entity.ReportEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class ReportWriter {
    private final ReportRepository reportRepository;

    public Long write(Long userId, Long interviewId) {
        return reportRepository.save(ReportEntity.init(userId, interviewId)).getId();
    }
}
