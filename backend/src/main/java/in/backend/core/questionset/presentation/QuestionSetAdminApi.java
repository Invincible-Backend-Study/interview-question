package in.backend.core.questionset.presentation;


import in.backend.core.auth.domain.Visitor;
import in.backend.core.auth.domain.attributes.AdminOnly;
import in.backend.core.auth.domain.attributes.Auth;
import in.backend.core.questionset.application.QuestionSetService;
import in.backend.core.questionset.presentation.payload.QuestionSetCreateRequest;
import in.backend.core.questionset.presentation.payload.QuestionSetCreateResponse;
import in.backend.core.questionset.presentation.payload.QuestionSetDetailResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequiredArgsConstructor
@RequestMapping("/api/admin/question-set")
public class QuestionSetAdminApi {
    private final QuestionSetService questionSetService;

    @AdminOnly
    @GetMapping
    public Page<QuestionSetDetailResponse> get(
            @Auth Visitor visitor,
            Pageable pageable
    ) {
        return questionSetService.find(pageable, visitor.toAdmin())
                .map(QuestionSetDetailResponse::from);
    }


    @AdminOnly
    @PostMapping
    public QuestionSetCreateResponse create(
            @Auth Visitor visitor,
            @RequestBody QuestionSetCreateRequest questionSetCreateRequest

    ) {
        var questionSet = questionSetService.create(
                questionSetCreateRequest.from(),
                visitor.toAdmin()
        );

        return QuestionSetCreateResponse.from(questionSet);
    }
}
