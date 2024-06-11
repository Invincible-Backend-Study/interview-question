package in.backend.core.report.repository;


import in.backend.core.report.entity.ReportItemEntity;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class ReportItemWriter {
    private final ReportItemRepository reportItemRepository;

    public void write(Long userId, Long reportId, List<Long> questionIds) {

        final var reportItems = questionIds.stream()
                .map(questionId -> ReportItemEntity.builder()
                        .userId(userId)
                        .reportId(reportId)
                        .questionId(questionId)
                        .build()
                ).toList();

        reportItemRepository.saveAll(reportItems);
    }
}
