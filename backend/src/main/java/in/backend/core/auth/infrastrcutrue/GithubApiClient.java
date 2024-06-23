package in.backend.core.auth.infrastrcutrue;

import static org.springframework.http.MediaType.APPLICATION_JSON;

import in.backend.core.auth.infrastrcutrue.payload.request.GithubAccessTokenRequest;
import in.backend.core.auth.infrastrcutrue.payload.response.GithubAccessTokenResponse;
import in.backend.core.auth.infrastrcutrue.payload.response.GithubProfileResponse;
import in.backend.global.exception.GlobalException;
import in.backend.global.exception.GlobalExceptionCode;
import in.backend.global.property.GithubApiProperty;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;


@Slf4j
@Component
public class GithubApiClient {

    private final RestClient restClient;
    private final GithubApiProperty githubApiProperty;

    public GithubApiClient(RestClient.Builder clientBuilder, GithubApiProperty githubApiProperty) {
        this.restClient = clientBuilder.build();
        this.githubApiProperty = githubApiProperty;
    }

    private GithubAccessTokenResponse requestAccessToken(String code, String redirectUrl) {
        return restClient.post()
                .uri("https://github.com/login/oauth/access_token")
                .contentType(APPLICATION_JSON)
                .accept(APPLICATION_JSON)
                .body(GithubAccessTokenRequest.create(githubApiProperty, code, redirectUrl))
                .retrieve()
                .onStatus(HttpStatusCode::is4xxClientError, (_, _) -> {
                    throw new GlobalException(GlobalExceptionCode.INVALID_OAUTH_CODE);
                })
                .onStatus(HttpStatusCode::is5xxServerError, (_, _) -> {
                    throw new GlobalException(GlobalExceptionCode.INVALID_OAUTH_SERVER);
                })
                .body(GithubAccessTokenResponse.class);
    }

    public GithubProfileResponse requestProfile(String code, String redirectUrl) {
        final var response = this.requestAccessToken(code, redirectUrl);
        return restClient.get()
                .uri("https://api.github.com/user")
                .header(HttpHeaders.AUTHORIZATION, STR."Bearer \{response.accessToken()}")
                .accept(APPLICATION_JSON)
                .retrieve()
                .onStatus(HttpStatusCode::is4xxClientError, (_, _) -> {
                    throw new GlobalException(GlobalExceptionCode.INVALID_OAUTH_CODE);
                })
                .onStatus(HttpStatusCode::is5xxServerError, (_, _) -> {
                    throw new GlobalException(GlobalExceptionCode.INVALID_OAUTH_SERVER);
                })
                .body(GithubProfileResponse.class);
    }
}
