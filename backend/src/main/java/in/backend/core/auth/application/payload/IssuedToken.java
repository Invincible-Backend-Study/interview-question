package in.backend.core.auth.application.payload;


import lombok.Builder;

@Builder
public record IssuedToken(
        String accessToken,
        String refreshToken
) {
}
