package in.backend.core.question.presentation;


import in.backend.core.auth.domain.Visitor;
import in.backend.core.auth.domain.attributes.Auth;
import in.backend.core.auth.domain.attributes.MemberOnly;
import in.backend.core.question.application.TailQuestionService;
import in.backend.core.question.presentation.payload.TailQuestionSubmitRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/tail-questions")
public class TailQuestionApi {
    private final TailQuestionService tailQuestionService;


    @MemberOnly
    @PostMapping("/submit")
    public void submit(
            @Auth Visitor visitor,
            @RequestBody TailQuestionSubmitRequest tailQuestionSubmitRequest
    ) {
        this.tailQuestionService.submit(visitor, tailQuestionSubmitRequest.to());
    }
}
