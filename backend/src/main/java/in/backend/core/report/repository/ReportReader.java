package in.backend.core.report.repository;

import in.backend.core.report.entity.ReportEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ReportReader {
    private final ReportRepository reportRepository;


    public ReportEntity read(Long reportId, Long memberId) {
        return reportRepository.findByIdAndMemberId(reportId, memberId)
                .orElseThrow(IllegalArgumentException::new);
    }
}
