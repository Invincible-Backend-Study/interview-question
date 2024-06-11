package in.backend.core.auth.presentation.payload.request;

import in.backend.core.member.infrastructure.MemberInfo;
import lombok.Builder;

@Builder
public record SignupRequest(
        String providerId,
        String avatarUrl,
        String nickname
) {

    public MemberInfo toDto() {
        return MemberInfo.builder()
                .avatarUrl(avatarUrl)
                .nickname(nickname)
                .providerId(providerId)
                .build();
    }
}
