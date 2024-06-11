package in.backend.core.auth.presentation.payload.response;


import lombok.Builder;

@Builder
public record OAuthProfileResponse(
        String providerId,
        String avatarUrl,
        boolean isRegistered
) {
}
