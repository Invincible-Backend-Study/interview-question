package in.backend.core.interview.application;


import in.backend.core.interview.presentation.payload.request.InterviewCreateRequest;
import in.backend.core.interview.repository.InterviewWriter;
import in.backend.core.question.entity.QuestionEntity;
import in.backend.core.questionset.application.QuestionSetFinder;
import in.backend.core.report.repository.ReportItemWriter;
import in.backend.core.report.repository.ReportWriter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class InterviewGenerator {

    private final QuestionSetFinder questionSetFinder;
    private final InterviewWriter interviewWriter;
    private final ReportWriter reportWriter;
    private final ReportItemWriter reportItemWriter;

    public void generate(Long userId, InterviewCreateRequest interviewCreateRequest) {
        final var questionSet = questionSetFinder.findAndCotnainQuestions(interviewCreateRequest.questionSetId());
        final var questionIds = questionSet.extractQuestions(interviewCreateRequest.totalOfProblemCount())
                .stream()
                .map(QuestionEntity::getId)
                .toList();

        final var interviewSettings = questionSet.create(
                interviewCreateRequest.tailQuestionDepth(),
                interviewCreateRequest.timeToThink(),
                interviewCreateRequest.timeToAnswer()
        );

        final var interviewId = interviewWriter.write(userId, interviewSettings);
        final var reportId = reportWriter.write(userId, interviewId);

        reportItemWriter.write(userId, reportId, questionIds);
    }


}
