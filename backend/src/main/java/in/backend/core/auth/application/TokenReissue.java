package in.backend.core.auth.application;


import in.backend.core.auth.infrastrcutrue.RefreshTokenReader;
import in.backend.global.provider.JwtProvider;
import java.time.Instant;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TokenReissue {
    private final JwtProvider jwtProvider;
    private final RefreshTokenReader refreshTokenReader;

    public String publish(String refreshToken) {
        jwtProvider.validRefreshToken(refreshToken);

        return jwtProvider.createAccessToken(
                refreshTokenReader.read(refreshToken).getId(),
                Instant.now()
        );
    }

}
