package in.backend.core.interview.infrastructure;


import in.backend.global.property.PromptProperty;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import org.springframework.ai.converter.BeanOutputConverter;
import org.springframework.ai.openai.OpenAiChatModel;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AIFeedbackProvider {
    private final OpenAiChatModel openAiChatModel;
    private final PromptProperty promptProperty;

    public FeedbackResponse execute(String question, String answer) {

        var beanOutputConverter = new BeanOutputConverter<>(FeedbackResponse.class);
        var prompt = promptProperty.backendPromptTemplate()
                .create(Map.of(
                        "format", beanOutputConverter.getFormat(),
                        "question", question,
                        "answer", answer
                ));

        return beanOutputConverter.convert(
                openAiChatModel.call(prompt)
                        .getResult()
                        .getOutput()
                        .getContent()
        );
    }

    public record FeedbackResponse(int score, String feedback, String tailQuestion) {

    }

}

