package in.backend.core.interview.infrastructure;


import in.backend.global.property.PromptProperty;
import java.util.List;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.ai.converter.BeanOutputConverter;
import org.springframework.ai.openai.OpenAiChatModel;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class AIFeedbackProvider {
    private final OpenAiChatModel openAiChatModel;
    private final PromptProperty promptProperty;

    public FeedbackResponse execute(String question, String answer, List<String> tailQuestions) {

        var beanOutputConverter = new BeanOutputConverter<>(FeedbackResponse.class);
        var prompt = promptProperty.backendPromptTemplate()
                .create(Map.of(
                        "format", beanOutputConverter.getFormat(),
                        "question", question,
                        "tailQuestions", convertTailQuestions(tailQuestions),
                        "answer", answer
                ));

        return beanOutputConverter.convert(
                openAiChatModel.call(prompt)
                        .getResult()
                        .getOutput()
                        .getContent()
        );
    }

    private String convertTailQuestions(List<String> tailQuestions) {
        if (tailQuestions.isEmpty()) {
            return "";
        }

        return String.join(",", tailQuestions);
    }


    public record FeedbackResponse(int score, String feedback, String tailQuestion, List<String> referenceLinks) {

    }

}

