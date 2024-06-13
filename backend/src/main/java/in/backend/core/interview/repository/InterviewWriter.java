package in.backend.core.interview.repository;


import in.backend.core.interview.entity.InterviewEntity;
import in.backend.core.interview.entity.InterviewQuestionEntity;
import in.backend.core.interview.entity.InterviewSettings;
import in.backend.core.question.entity.QuestionEntity;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class InterviewWriter {
    private final InterviewRepository interviewRepository;
    private final InterviewQuestionRepository interviewQuestionRepository;


    public Long write(Long memberId, InterviewSettings interviewSettings, List<QuestionEntity> questions) {
        var interview = InterviewEntity.init(memberId, interviewSettings, questions.size());

        interviewRepository.save(interview);
        interviewQuestionRepository.saveAll(questions.stream()
                .map(question -> InterviewQuestionEntity.builder()
                        .interviewId(interview.getId())
                        .memberId(memberId)
                        .question(question)
                        .build())
                .toList());

        return interview.getId();
    }
}
