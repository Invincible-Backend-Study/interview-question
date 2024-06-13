package in.backend.core.interview.presentation;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

import com.fasterxml.jackson.databind.ObjectMapper;
import in.backend.core.interview.presentation.payload.request.InterviewCreateRequest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;


@WebMvcTest(InterviewApi.class)
class InterviewApiUnitTest {

    @Autowired
    MockMvc mockMvc;


    @Autowired
    ObjectMapper objectMapper;


    @Test
    void 인터뷰_생성할_때_null값을_허용하지_않습니다() throws Exception {
        var interviewCreateRequest = InterviewCreateRequest.builder().build();

        mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/interviews")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsBytes(interviewCreateRequest)))
                .andDo(print())
                .andExpect(MockMvcResultMatchers.status().is4xxClientError())
                .andExpect(MockMvcResultMatchers.jsonPath("$.message").value("유효하지 않은 요쳥입니다."));

    }

}