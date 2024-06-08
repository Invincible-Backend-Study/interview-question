package in.backend.report.domain;

import in.backend.answer.domain.Answer;
import in.backend.question.domain.Question;

public class ReportItem {
    private Long interviewItem;
    private Long userId;
    private Question question;
    private Answer answer;
    private AIFeedback aiFeedback;
}
