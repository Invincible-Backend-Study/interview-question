package in.backend.core.auth.infrastrcutrue;


import in.backend.core.auth.entity.RefreshTokenEntity;
import in.backend.global.exception.GlobalExceptionCode;
import in.backend.global.exception.RefreshTokenException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class RefreshTokenReader {
    private final RefreshTokenRepository refreshTokenRepository;

    public boolean existsBy(Long memberId) {
        return refreshTokenRepository.existsById(memberId);
    }

    public RefreshTokenEntity read(String refreshToken) {
        return refreshTokenRepository.findByToken(refreshToken)
                .orElseThrow(() -> new RefreshTokenException(GlobalExceptionCode.NOT_FOUND_REFRESH_TOKEN));
    }
}
