package in.backend.core.questionset.presentation;

import in.backend.core.questionset.presentation.payload.response.QuestionSetSearchResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Slice;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/question-set")
public class QuestionSetApi {

    @GetMapping
    @ApiResponse(description = "question set 목록을 조회하는 api 입니다.", responseCode = "200")
    public Slice<QuestionSetSearchResponse> get() {
        return null;
    }


}
