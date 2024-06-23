package in.backend.core.member.presentation.payload;


import in.backend.core.member.entity.MemberEntity;

public record MyProfileResponse(
        String nickname,
        String avatarUrl
) {

    public static MyProfileResponse from(MemberEntity member) {
        return new MyProfileResponse(member.getNickname(), member.getAvatarUrl());
    }
}
