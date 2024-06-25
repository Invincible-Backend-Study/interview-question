package in.backend.core.member.infrastructure;

import in.backend.core.member.entity.MemberEntity;
import lombok.Builder;


@Builder
public record MemberInfo(
        String nickname,
        String avatarUrl,
        String providerId
) {
    public MemberEntity toEntity() {
        return MemberEntity.builder()
                .nickname(nickname)
                .avatarUrl(avatarUrl)
                .providerId(providerId)
                .build();

    }
}
