package in.backend.core.auth.presentation.payload.request;

import in.backend.core.member.infrastructure.MemberInfo;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import org.hibernate.validator.constraints.Length;

@Builder
public record SignupRequest(
        @NotNull
        String providerId,
        @NotNull
        String avatarUrl,

        @Length(min = 3, max = 20)
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
