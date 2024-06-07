package in.backend.report.domain;

import java.util.List;

public class Report {
    private Long interviewId;
    private Long userId;
    private Long totalScore;

    private List<ReportItem> reportItems;
    private ReportState reportState;
}
