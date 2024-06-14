package in.backend.core.interview.repository;


import in.backend.core.interview.entity.InterviewEntity;
import in.backend.core.interview.entity.InterviewQuestionEntity;
import in.backend.core.question.entity.QuestionEntity;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class InterviewQuestionWriter {
    private final InterviewQuestionRepository questionRepository;


    public void write(Long memberId, InterviewEntity interview, List<QuestionEntity> questions) {
        questionRepository.saveAll(questions.stream()
                .map(question -> InterviewQuestionEntity.builder()
                        .interviewId(interview.getId())
                        .remainTailQuestionCount(interview.getTailQuestionDepth())
                        .memberId(memberId)
                        .question(question)
                        .build()
                ).toList());
    }
}
