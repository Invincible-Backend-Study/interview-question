package in.backend.speech.payload.request;

public record SpeechRecognizeRequest(
        /**
         * base64audio data
         */
        String content
) {
}
