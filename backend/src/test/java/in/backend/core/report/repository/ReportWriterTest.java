package in.backend.core.report.repository;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

import in.backend.core.report.entity.ReportState;
import java.util.List;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;


@Transactional
@SpringBootTest
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
class ReportWriterTest {

    @Autowired
    private ReportItemRepository reportItemRepository;
    @Autowired
    private ReportRepository reportRepository;

    @Autowired
    private ReportWriter reportWriter;


    @Test
    void report_를_생성하면_questions_개수만큼_reportItem_이_생성됩니다() {
        var reportId = reportWriter.write(1L, 1L, List.of(1L, 2L, 3L));
        var report = reportRepository.findById(reportId).orElseThrow(IllegalArgumentException::new);

        assertAll(
                () -> assertThat(report.getReportState()).isEqualTo(ReportState.PROGRESS),
                () -> assertThat(report.getSize()).isEqualTo(3L),
                () -> assertThat(reportRepository.count()).isEqualTo(1L),
                () -> assertThat(reportItemRepository.count()).isEqualTo(3L)
        );

    }

}