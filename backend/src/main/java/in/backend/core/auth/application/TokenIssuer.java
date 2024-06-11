package in.backend.core.auth.application;


import in.backend.core.auth.application.payload.IssuedToken;
import in.backend.global.provider.JwtProvider;
import java.time.Instant;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TokenIssuer {

    private final JwtProvider tokenProvider;


    public IssuedToken publish(Long id) {
        var now = Instant.now();
        var accessToken = tokenProvider.createAccessToken(id, now);
        var refreshToken = tokenProvider.createRefreshToken(id, now);

        return IssuedToken.builder()
                .accessToken(accessToken)
                .refreshToken(refreshToken)
                .build();
    }


}
