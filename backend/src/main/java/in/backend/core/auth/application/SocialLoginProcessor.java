package in.backend.core.auth.application;


import in.backend.core.auth.application.payload.IssuedToken;
import in.backend.core.auth.infrastrcutrue.GithubApiClient;
import in.backend.core.auth.infrastrcutrue.RefreshTokenWriter;
import in.backend.core.auth.presentation.payload.request.OAuthProfileRequest;
import in.backend.core.auth.presentation.payload.request.SigninRequest;
import in.backend.core.auth.presentation.payload.response.OAuthProfileResponse;
import in.backend.core.member.infrastructure.MemberInfo;
import in.backend.core.member.infrastructure.MemberReader;
import in.backend.core.member.infrastructure.MemberWriter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SocialLoginProcessor {
    private final TokenIssuer tokenIssuer;
    private final GithubApiClient githubApiClient;
    private final MemberReader memberReader;
    private final MemberWriter memberWriter;
    private final RefreshTokenWriter refreshTokenWriter;

    public OAuthProfileResponse findProfile(OAuthProfileRequest request) {
        final var profile = githubApiClient.requestProfile(request.providerId(), request.redirectUrl());

        return new OAuthProfileResponse(
                profile.id(),
                profile.avatarUrl(),
                memberReader.isMember(profile.id())
        );
    }

    public IssuedToken signup(MemberInfo memberInfo) {
        var memberId = memberWriter.write(memberInfo);
        var issuedToken = tokenIssuer.publish(memberId);

        refreshTokenWriter.write(memberId, issuedToken.refreshToken());
        return issuedToken;
    }


    public IssuedToken signin(SigninRequest signinRequest) {
        var memberId = memberReader.read(signinRequest.providerId()).getId();
        var issuedToken = tokenIssuer.publish(memberId);

        refreshTokenWriter.write(memberId, issuedToken.refreshToken());
        return issuedToken;
    }
}
