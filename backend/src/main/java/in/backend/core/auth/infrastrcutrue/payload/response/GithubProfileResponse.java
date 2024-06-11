package in.backend.core.auth.infrastrcutrue.payload.response;


import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public record GithubProfileResponse(
        String id,
        String avatarUrl
) {
}
