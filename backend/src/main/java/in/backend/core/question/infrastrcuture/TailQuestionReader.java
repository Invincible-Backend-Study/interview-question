package in.backend.core.question.infrastrcuture;


import static java.util.stream.Collectors.groupingBy;

import in.backend.core.exception.DomainExceptionCode;
import in.backend.core.question.entity.TailQuestionEntity;
import java.util.List;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class TailQuestionReader {

    private final TailQuestionRepository tailQuestionRepository;


    public TailQuestionEntity read(Long tailQuestionId, Long memberId) {
        return tailQuestionRepository.findByIdAndMemberId(tailQuestionId, memberId)
                .orElseThrow(DomainExceptionCode.TAIL_QUESTION_NOT_FOUND::create);

    }

    public Map<Long, List<TailQuestionEntity>> read(Long interviewId) {
        return tailQuestionRepository.findByInterviewId(interviewId)
                .stream()
                .collect(groupingBy(TailQuestionEntity::getInterviewQuestionId));
    }
}
