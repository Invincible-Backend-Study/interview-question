package in.backend.core.interview.repository;


import static in.backend.core.exception.DomainExceptionCode.INTERVIEW_QUESTION_NOT_FOUND;

import in.backend.core.exception.DomainExceptionCode;
import in.backend.core.interview.application.InterviewQuestionCount;
import in.backend.core.interview.entity.InterviewEntity;
import in.backend.core.interview.entity.InterviewQuestionEntity;
import java.util.List;
import java.util.Objects;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class InterviewQuestionReader {
    private final InterviewQuestionRepository interviewQuestionRepository;


    public InterviewQuestionEntity read(Long interviewId, Long memberId, int index) {
        var interviewQuestions = interviewQuestionRepository.findByInterviewIdAndMemberId(
                interviewId,
                memberId,
                PageRequest.of(index, 1)
        );

        INTERVIEW_QUESTION_NOT_FOUND.invokeByCondition(interviewQuestions.isEmpty());

        return interviewQuestions.getFirst();
    }

    public InterviewQuestionEntity read(Long interviewQuestionId) {
        return interviewQuestionRepository.findById(interviewQuestionId)
                .orElseThrow(DomainExceptionCode.INTERVIEW_QUESTION_NOT_FOUND::create);
    }

    public List<InterviewQuestionCount> readByCount(List<InterviewEntity> interviews) {
        var interviewIds = interviews.stream()
                .map(InterviewEntity::getId)
                .filter(Objects::nonNull)
                .toList();

        return interviewQuestionRepository.countByInterviewIds(interviewIds);
    }
}
