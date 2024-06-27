package in.backend.core.auth.infrastrcutrue;

import static org.springframework.http.MediaType.APPLICATION_JSON;

import in.backend.core.auth.infrastrcutrue.payload.request.GithubAccessTokenRequest;
import in.backend.core.auth.infrastrcutrue.payload.response.GithubAccessTokenResponse;
import in.backend.core.auth.infrastrcutrue.payload.response.GithubProfileResponse;
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
    private final Status4xxErrorHandler status4xxErrorHandler;
    private final Status5xxErrorHandler status5xxErrorHandler;

    public GithubApiClient(
            RestClient.Builder clientBuilder,
            GithubApiProperty githubApiProperty,
            Status4xxErrorHandler status4xxErrorHandler,
            Status5xxErrorHandler status5xxErrorHandler
    ) {
        this.restClient = clientBuilder.build();
        this.githubApiProperty = githubApiProperty;
        this.status4xxErrorHandler = status4xxErrorHandler;
        this.status5xxErrorHandler = status5xxErrorHandler;
    }

    private GithubAccessTokenResponse requestAccessToken(String code, String redirectUrl) {
        return restClient.post()
                .uri("https://github.com/login/oauth/access_token")
                .contentType(APPLICATION_JSON)
                .accept(APPLICATION_JSON)
                .body(GithubAccessTokenRequest.create(githubApiProperty, code, redirectUrl))
                .retrieve()
                .onStatus(HttpStatusCode::is4xxClientError, status4xxErrorHandler)
                .onStatus(HttpStatusCode::is5xxServerError, status5xxErrorHandler)
                .body(GithubAccessTokenResponse.class);
    }

    public GithubProfileResponse requestProfile(String code, String redirectUrl) {
        final var response = this.requestAccessToken(code, redirectUrl);
        return restClient.get()
                .uri("https://api.github.com/user")
                .header(HttpHeaders.AUTHORIZATION, STR."Bearer \{response.accessToken()}")
                .accept(APPLICATION_JSON)
                .retrieve()
                .onStatus(HttpStatusCode::is4xxClientError, status4xxErrorHandler)
                .onStatus(HttpStatusCode::is5xxServerError, status5xxErrorHandler)
                .body(GithubProfileResponse.class);
    }
}
