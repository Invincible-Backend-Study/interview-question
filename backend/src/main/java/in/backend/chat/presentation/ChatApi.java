package in.backend.chat.presentation;


import in.backend.chat.payload.request.InterviewRequest;
import in.backend.chat.payload.response.InterviewResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.ai.openai.OpenAiChatModel;
import org.springframework.ai.openai.OpenAiChatOptions;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/interview")
public class ChatApi {


    private final OpenAiChatModel openAiChatModel;


    @PostMapping()
    public InterviewResponse interview(
            @RequestBody InterviewRequest interviewRequest
    ) {
        var chatResponse = openAiChatModel.call(new Prompt(
                STR."""
                        지금부터 역할을 부여해줄께
                        너는 백엔드 면접관. 질문할 범위는 자바, 스프링, JPA, 데이터베이스, CS 지식
                        나는 면접을 보는 지원자.
                                               
                        
                        질문은 내가 지정해줄께.
                        질문:[\{interviewRequest.question()}]
                        대답:[\{interviewRequest.response()}]

                        질문에 대한 답변을 보고 아래 응답을 줘 대신 각 항목마다 '---------'로 구분해줘
                        - 질문에 대한 답변의 점수 (100점 만점)
                        - 질문에 대한 피드백
                        - 질문에 대한 꼬리질문(1개)
                        """,
                OpenAiChatOptions.builder()
                        .withModel("gpt-4o")
                        .withTemperature(0.4f)
                        .build()
        ));

        log.info("{}", chatResponse);

        return new InterviewResponse(chatResponse.getResult()
                .getOutput()
                .getContent()
        );
    }

}
