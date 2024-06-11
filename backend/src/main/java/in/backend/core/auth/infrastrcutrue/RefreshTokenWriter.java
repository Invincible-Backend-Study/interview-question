package in.backend.core.auth.infrastrcutrue;


import in.backend.core.auth.entity.RefreshTokenEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class RefreshTokenWriter {

    private final RefreshTokenRepository refreshTokenRepository;

    public void write(Long memberId, String refreshToken) {
        // 기존에 사용하던 토큰 삭제
        delete(memberId);
        refreshTokenRepository.save(new RefreshTokenEntity(memberId, refreshToken));
    }

    public void delete(Long memberId) {
        refreshTokenRepository.deleteById(memberId);
    }
}
