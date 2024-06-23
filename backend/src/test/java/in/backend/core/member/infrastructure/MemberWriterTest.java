package in.backend.core.member.infrastructure;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

import in.backend.core.exception.DomainException;
import in.backend.core.exception.DomainExceptionCode;
import in.backend.global.layer.ImplementLayerTest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

class MemberWriterTest extends ImplementLayerTest {

    @Autowired
    MemberWriter memberWriter;

    @Test
    void 동일한_닉네임을_사용하는_유저가_있는경우_해당_닉네임을_사용할_수_없습니다() {

        var nickname = "박재홍";
        memberWriter.write(MemberInfo.builder()
                .avatarUrl("")
                .nickname(nickname)
                .providerId("providerId")
                .build());

        assertThatThrownBy(() -> memberWriter.write(MemberInfo.builder()
                .avatarUrl("")
                .nickname(nickname)
                .providerId("providerId1")
                .build())
        )
                .isInstanceOf(DomainException.class)
                .hasMessage(DomainExceptionCode.NICK_NAME_EXISTS.getMessage());

    }

}