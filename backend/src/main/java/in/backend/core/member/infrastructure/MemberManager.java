package in.backend.core.member.infrastructure;


import in.backend.core.auth.infrastrcutrue.RefreshTokenWriter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class MemberManager {
    private final MemberReader memberReader;
    private final RefreshTokenWriter refreshTokenWriter;

    public void widthDraw(Long memberId) {
        var member = memberReader.read(memberId);

        member.withDraw();
        refreshTokenWriter.delete(memberId);
    }

}
