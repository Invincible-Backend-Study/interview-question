package in.backend.core.member.infrastructure;

import in.backend.core.auth.entity.RefreshTokenEntity;
import in.backend.core.exception.DomainException;
import in.backend.core.exception.DomainExceptionCode;
import in.backend.core.member.entity.MemberEntity;
import in.backend.global.layer.ImplementLayerTest;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;


class MemberManagerTest extends ImplementLayerTest {

    @Autowired
    MemberManager memberManager;

    @Autowired
    MemberReader memberReader;

    @Autowired
    MemberWriter memberWriter;

    @Test
    void 회원_탈퇴가_정상적으로_처리됩니다() {
        var memberId = memberRepository.save(MemberEntity.builder()
                        .providerId("1")
                        .avatarUrl("1")
                        .nickname("1")
                        .build())
                .getId();

        refreshTokenRepository.save(new RefreshTokenEntity(memberId, "token"));
        memberManager.widthDraw(memberId);

        Assertions.assertThatThrownBy(() -> memberReader.read(memberId))
                .isInstanceOf(DomainException.class)
                .hasMessageContaining(DomainExceptionCode.MEMBER_ID_NOT_EXISTS.getMessage());
    }

}