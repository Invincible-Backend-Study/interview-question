package in.backend.global.config;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.spring.autoconfigure.storage.GcpStorageAutoConfiguration;
import com.google.cloud.spring.core.UserAgentHeaderProvider;
import com.google.cloud.storage.Storage;
import com.google.cloud.storage.StorageOptions;
import in.backend.global.property.GCSProperty;
import java.io.IOException;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
@RequiredArgsConstructor
public class GCSConfig {

    private final GCSProperty gcsProperty;

    @Bean
    @ConditionalOnMissingBean
    public Storage storage() throws IOException {
        GoogleCredentials credentials = GoogleCredentials.fromStream(gcsProperty.location().getInputStream());
        return StorageOptions.newBuilder()
                .setHeaderProvider(new UserAgentHeaderProvider(GcpStorageAutoConfiguration.class))
                .setProjectId(gcsProperty.projectId())
                .setCredentials(credentials)
                .build()
                .getService();
    }
}
