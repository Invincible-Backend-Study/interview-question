package in.backend.core.auth.application;


import in.backend.core.auth.application.payload.IssuedToken;
import in.backend.core.auth.domain.Visitor;
import in.backend.core.auth.infrastrcutrue.RefreshTokenWriter;
import in.backend.global.provider.JwtProvider;
import java.time.Instant;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TokenReissue {

    private final JwtProvider jwtProvider;
    private final RefreshTokenWriter refreshTokenWriter;

    public IssuedToken publish(Visitor visitor) {

        var now = Instant.now();
        var accessToken = jwtProvider.createAccessToken(visitor.memberId(), now);
        var refreshToken = jwtProvider.createRefreshToken(visitor.memberId(), now);

        refreshTokenWriter.write(visitor.memberId(), refreshToken);

        return IssuedToken.builder()
                .accessToken(accessToken)
                .refreshToken(refreshToken)
                .build();
    }
}
