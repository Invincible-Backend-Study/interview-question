package in.backend.core.question.presentation;


import in.backend.core.auth.domain.Visitor;
import in.backend.core.auth.domain.attributes.Auth;
import in.backend.core.question.presentation.payload.QuestionSaveRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/questions")
public class QuestionApi {


    @PostMapping
    public void save(
            @RequestBody QuestionSaveRequest questionSaveRequest,
            @Auth Visitor visitor
    ) {

    }
}
