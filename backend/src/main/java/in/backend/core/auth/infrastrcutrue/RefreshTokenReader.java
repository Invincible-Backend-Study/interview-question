package in.backend.core.auth.infrastrcutrue;


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
}
