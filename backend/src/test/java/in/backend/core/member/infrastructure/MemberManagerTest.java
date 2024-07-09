package in.backend.core.member.infrastructure;

import static org.assertj.core.api.Assertions.assertThat;

import in.backend.core.auth.entity.RefreshTokenEntity;
import in.backend.core.member.entity.MemberEntity;
import in.backend.global.layer.ImplementLayerTest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;


class MemberManagerTest extends ImplementLayerTest {

    @Autowired
    MemberManager memberManager;

    @Autowired
    MemberReader memberReader;

    @Test
    void 회원_탈퇴가_정상적으로_처리됩니다() {
        given(() -> {
            var member = memberRepository.save(MemberEntity.builder()
                    .providerId("1")
                    .avatarUrl("1")
                    .nickname("1")
                    .build());
            refreshTokenRepository.save(new RefreshTokenEntity(member.getId(), "token"));
            return member.getId();
        });

        memberManager.widthDraw(1L);
        var member = memberReader.read(1L);

        assertThat(member.getAvatarUrl()).isEmpty();
        assertThat(member.getProviderId()).isEmpty();
        assertThat(member.isDeleted()).isTrue();
    }

}