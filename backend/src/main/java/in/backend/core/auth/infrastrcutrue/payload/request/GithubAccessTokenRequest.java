package in.backend.core.auth.infrastrcutrue.payload.request;

import in.backend.global.property.GithubApiProperty;

public record GithubAccessTokenRequest(
        String clientId,
        String clientSecret,
        String code,
        String redirectUrl
) {

    public static GithubAccessTokenRequest create(
            GithubApiProperty githubApiProperty,
            String code,
            String redirectUrl
    ) {

        return new GithubAccessTokenRequest(
                githubApiProperty.clientId(),
                githubApiProperty.clientSecret(),
                code,
                redirectUrl
        );
    }
}
