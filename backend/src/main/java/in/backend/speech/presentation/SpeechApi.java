package in.backend.speech.presentation;


import static java.util.stream.Collectors.collectingAndThen;
import static java.util.stream.Collectors.joining;

import com.google.api.gax.core.CredentialsProvider;
import com.google.api.gax.core.FixedCredentialsProvider;
import com.google.auth.oauth2.ServiceAccountCredentials;
import com.google.cloud.speech.v1.RecognitionAudio;
import com.google.cloud.speech.v1.RecognitionConfig;
import com.google.cloud.speech.v1.RecognitionConfig.AudioEncoding;
import com.google.cloud.speech.v1.RecognizeResponse;
import com.google.cloud.speech.v1.SpeechClient;
import com.google.cloud.speech.v1.SpeechRecognitionAlternative;
import com.google.cloud.speech.v1.SpeechRecognitionResult;
import com.google.cloud.speech.v1.SpeechSettings;
import com.google.protobuf.ByteString;
import in.backend.speech.payload.request.SpeechRecognizeRequest;
import in.backend.speech.payload.response.SpeechRecognizeResponse;
import java.io.FileInputStream;
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
    ) throws IOException {

        CredentialsProvider credentialsProvider = FixedCredentialsProvider.create(
                ServiceAccountCredentials.fromStream(
                        new FileInputStream("/Users/jaehong/Downloads/drrr-423416-5be1d0478172.json")));

        var settings = SpeechSettings.newBuilder()
                .setCredentialsProvider(credentialsProvider)
                .build();

        try (SpeechClient speechClient = SpeechClient.create(settings)) {
            // The path to the audio file to transcribe

            // Builds the sync recognize request
            var config = RecognitionConfig.newBuilder()
                    .setEncoding(AudioEncoding.LINEAR16)
                    .setSampleRateHertz(16000)
                    .setModel("default")
                    .setLanguageCode("en-US")
                    .build();

            RecognitionAudio audio = RecognitionAudio.newBuilder()
                    .setContent(ByteString.readFrom(new FileInputStream("/Users/jaehong/Desktop/무제.m4a")))
                    .build();

            // Performs speech recognition on the audio file
            RecognizeResponse response = speechClient.recognize(config, audio);
            log.info("{}", response);
            List<SpeechRecognitionResult> results = response.getResultsList();
            log.info("{}", results);

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
