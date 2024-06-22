package in.backend.core.interview.presentation;


import in.backend.core.auth.domain.Visitor;
import in.backend.core.auth.domain.attributes.Auth;
import in.backend.core.auth.domain.attributes.MemberOnly;
import in.backend.core.interview.application.InterviewService;
import in.backend.core.interview.application.InterviewSubmitResult;
import in.backend.core.interview.application.MyInterviewResult;
import in.backend.core.interview.presentation.payload.InterviewCreateRequest;
import in.backend.core.interview.presentation.payload.InterviewCreateResponse;
import in.backend.core.interview.presentation.payload.InterviewQuestionResponse;
import in.backend.core.interview.presentation.payload.InterviewSubmitRequest;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/interviews")
public class InterviewApi {

    private final InterviewService interviewService;

    @MemberOnly
    @PostMapping
    public InterviewCreateResponse createInterview(
            @Auth Visitor visitor,
            @RequestBody InterviewCreateRequest request
    ) {
        var interviewId = interviewService.create(visitor, request.to());
        return new InterviewCreateResponse(interviewId);
    }

    @MemberOnly
    @GetMapping("/{interviewId}/current/problem")
    public InterviewQuestionResponse getCurrentProblem(
            @Auth Visitor visitor,
            @NotNull @PathVariable("interviewId") Long interviewId
    ) {
        var interviewQuestionInfo = interviewService.loadByCurrentProblem(visitor, interviewId);
        return InterviewQuestionResponse.from(interviewQuestionInfo);
    }

    @MemberOnly
    @PostMapping("/submit")
    public InterviewSubmitResult submit(
            @Auth Visitor visitor,
            @RequestBody InterviewSubmitRequest request
    ) {
        return interviewService.submit(visitor, request.to());
    }


    @MemberOnly
    @GetMapping("/me")
    public Page<MyInterviewResult> getMe(
            @Auth Visitor visitor,
            Pageable pageable
    ) {
        return interviewService.search(visitor, pageable);
    }


}
