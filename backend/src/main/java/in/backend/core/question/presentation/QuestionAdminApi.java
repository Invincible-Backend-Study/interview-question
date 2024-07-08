package in.backend.core.question.presentation;


import static java.util.stream.Collectors.collectingAndThen;
import static java.util.stream.Collectors.toList;

import in.backend.core.auth.domain.Visitor;
import in.backend.core.auth.domain.attributes.AdminOnly;
import in.backend.core.auth.domain.attributes.Auth;
import in.backend.core.question.application.QuestionService;
import in.backend.core.question.presentation.payload.QuestionAdminDetailResponse;
import in.backend.core.question.presentation.payload.QuestionAdminSearchRequest;
import in.backend.core.question.presentation.payload.QuestionAdminSearchResponse;
import in.backend.core.question.presentation.payload.QuestionSaveRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/admin/questions")
public class QuestionAdminApi {
    private final QuestionService questionService;


    @AdminOnly
    @GetMapping
    public QuestionAdminSearchResponse get(
            @Auth Visitor visitor,
            @ModelAttribute QuestionAdminSearchRequest request
    ) {
        return questionService.find(request.questionSetId(), visitor.toAdmin())
                .stream()
                .map(QuestionAdminDetailResponse::from)
                .collect(collectingAndThen(toList(), QuestionAdminSearchResponse::new));
    }


    @AdminOnly
    @PostMapping
    public void save(
            @RequestBody QuestionSaveRequest request,
            @Auth Visitor visitor
    ) {
        questionService.save(request.toDto(), visitor.toAdmin());
    }
}
