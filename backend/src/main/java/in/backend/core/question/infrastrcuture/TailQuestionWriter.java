package in.backend.core.question.infrastrcuture;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class TailQuestionWriter {
    private final TailQuestionRepository tailQuestionRepository;
}
