package in.backend.core.questionset.presentation;

import in.backend.core.auth.domain.Visitor;
import in.backend.core.auth.domain.attributes.Auth;
import in.backend.core.auth.domain.attributes.MemberOnly;
import in.backend.core.questionset.application.QuestionSetService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/question-set")
public class QuestionSetApi {

    private final QuestionSetService questionSetService;

    @MemberOnly
    @GetMapping
    public void get(
            @Auth Visitor visitor,
            Pageable pageable
    ) {

    }
}
