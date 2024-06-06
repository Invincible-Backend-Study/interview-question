package in.backend.speech.presentation;


import static java.util.stream.Collectors.collectingAndThen;
import static java.util.stream.Collectors.joining;

import com.google.cloud.speech.v1.RecognitionAudio;
import com.google.cloud.speech.v1.RecognitionConfig;
import com.google.cloud.speech.v1.RecognitionConfig.AudioEncoding;
import com.google.cloud.speech.v1.RecognizeResponse;
import com.google.cloud.speech.v1.SpeechClient;
import com.google.cloud.speech.v1.SpeechRecognitionAlternative;
import com.google.cloud.speech.v1.SpeechRecognitionResult;
import com.google.protobuf.ByteString;
import in.backend.speech.payload.request.SpeechRecognizeRequest;
import in.backend.speech.payload.response.SpeechRecognizeResponse;
import java.io.IOException;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/speech")
public class SpeechApi {

    @PostMapping("/recognize")
    public SpeechRecognizeResponse recognize(
            @RequestBody SpeechRecognizeRequest request
    ) {

        try (SpeechClient speechClient = SpeechClient.create()) {

            // The path to the audio file to transcribe

            // Builds the sync recognize request
            var config = RecognitionConfig.newBuilder()
                    .setEncoding(AudioEncoding.LINEAR16)
                    .setSampleRateHertz(16000)
                    .setLanguageCode("ko-KR")
                    .build();
            RecognitionAudio audio = RecognitionAudio.newBuilder()
                    .setContent(ByteString.copyFromUtf8(request.content()))
                    .build();

            // Performs speech recognition on the audio file
            RecognizeResponse response = speechClient.recognize(config, audio);
            List<SpeechRecognitionResult> results = response.getResultsList();

            for (SpeechRecognitionResult result : results) {
                // There can be several alternative transcripts for a given chunk of speech. Just use the
                // first (most likely) one here.
                SpeechRecognitionAlternative alternative = result.getAlternativesList().getFirst();
                log.info("{}", alternative.getTranscript());
            }
            return results.stream()
                    .map(SpeechRecognitionResult::getAlternativesList)
                    .map(List::getFirst)
                    .map(SpeechRecognitionAlternative::getTranscript)
                    .collect(collectingAndThen(joining(), SpeechRecognizeResponse::new));

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
