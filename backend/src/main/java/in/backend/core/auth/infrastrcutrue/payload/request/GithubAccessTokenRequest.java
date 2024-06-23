package in.backend.core.auth.infrastrcutrue.payload.request;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import in.backend.global.property.GithubApiProperty;


@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
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
