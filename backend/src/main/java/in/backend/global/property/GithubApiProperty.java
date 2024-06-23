package in.backend.global.property;


import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.ConfigurationPropertiesBinding;

@ConfigurationProperties("oauth2.github")
@ConfigurationPropertiesBinding
public record GithubApiProperty(
        String clientId,
        String clientSecret
) {
}


