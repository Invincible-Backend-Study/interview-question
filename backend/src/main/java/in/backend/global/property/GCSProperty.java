package in.backend.global.property;


import com.google.cloud.storage.BlobId;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.ConfigurationPropertiesBinding;
import org.springframework.core.io.Resource;

@ConfigurationProperties("gcs.resource.test")
@ConfigurationPropertiesBinding
public record GCSProperty(
        String bucket,
        Resource location,
        String projectId
) {


    public BlobId create(String fileName) {
        return BlobId.of(bucket, fileName);
    }

    public String getFilePath(String fileName) {
        return STR."https://storage.googleapis.com/\{bucket}/\{fileName}";
    }
}
