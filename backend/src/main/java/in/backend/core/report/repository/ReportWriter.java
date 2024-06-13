package in.backend.core.report.repository;


import in.backend.core.report.entity.ReportEntity;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class ReportWriter {
    private final ReportRepository reportRepository;
    private final ReportItemWriter reportItemWriter;


    public Long write(Long memberId, Long interviewId, List<Long> questionIds) {
        var report = ReportEntity.init(memberId, interviewId, questionIds.size());

        reportRepository.save(report);
        reportItemWriter.write(memberId, interviewId, questionIds);

        return report.getId();
    }
}
