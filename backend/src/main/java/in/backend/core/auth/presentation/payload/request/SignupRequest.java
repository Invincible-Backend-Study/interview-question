package in.backend.core.auth.presentation.payload.request;

import in.backend.core.member.infrastructure.MemberInfo;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;

@Builder
public record SignupRequest(
        @NotNull
        String providerId,
        @NotNull
        String avatarUrl,

        @Max(20)
        @Min(4)
        @NotNull
        @NotBlank
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
