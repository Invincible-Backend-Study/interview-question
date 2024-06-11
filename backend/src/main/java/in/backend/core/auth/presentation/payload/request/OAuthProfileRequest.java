package in.backend.core.auth.presentation.payload.request;

public record OAuthProfileRequest(
        String providerId,
        String redirectUrl
) {
}
