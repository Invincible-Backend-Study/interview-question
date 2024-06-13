package in.backend.core.interview.presentation;


import in.backend.core.auth.domain.Visitor;
import in.backend.core.auth.domain.attributes.Auth;
import in.backend.core.auth.domain.attributes.MemberOnly;
import in.backend.core.interview.application.InterviewGenerator;
import in.backend.core.interview.presentation.payload.request.InterviewCreateRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/interviews")
public class InterviewApi {

    private final InterviewGenerator interviewGenerator;

    @MemberOnly
    @PostMapping
    public void create(
            @Auth Visitor visitor,
            @RequestBody @Validated InterviewCreateRequest interviewCreateRequest
    ) {
        interviewGenerator.generate(visitor.memberId(), interviewCreateRequest);
    }


}

