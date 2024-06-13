package in.backend.core.report.repository;


import in.backend.core.report.entity.ReportState;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class ReportManager {

    private final ReportReader reportReader;

    public void giveup(Long reportId, Long memberId) {
        var report = reportReader.read(reportId, memberId);

        report.giveUp();
    }

    public void submit(Long reportId, Long memberId, Integer solvedIndex) {
        var report = reportReader.read(reportId, memberId);

        report.solve(solvedIndex, ReportState.GIVE_UP);
    }

}
