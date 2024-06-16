package in.backend.core.question.infrastrcuture;


import in.backend.core.exception.DomainExceptionCode;
import in.backend.core.question.entity.TailQuestionEntity;
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
}
