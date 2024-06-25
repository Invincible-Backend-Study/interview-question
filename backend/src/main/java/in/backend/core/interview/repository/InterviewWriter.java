package in.backend.core.interview.repository;


import in.backend.core.interview.application.InterviewCreateCommand;
import in.backend.core.interview.entity.InterviewEntity;
import in.backend.core.questionset.infrastructure.QuestionSetReader;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class InterviewWriter {

    private final InterviewRepository interviewRepository;
    private final InterviewQuestionWriter interviewQuestionWriter;
    private final QuestionSetReader questionSetReader;

    public Long write(Long memberId, InterviewCreateCommand interviewCreateCommand) {
        var questionSet = questionSetReader.read(interviewCreateCommand.questionSetId());
        var questions = questionSet.extractQuestions(interviewCreateCommand.totalOfProblemCount());

        var interview = InterviewEntity.init(
                memberId,
                questionSet.getTitle(),
                questions.size(),
                interviewCreateCommand.toInterviewSettings(questionSet.getQuestionSetRules())
        );

        interviewRepository.save(interview);
        interviewQuestionWriter.write(memberId, interview, questions);

        return interview.getId();
    }
}
