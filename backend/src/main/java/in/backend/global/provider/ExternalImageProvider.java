package in.backend.global.provider;

import com.google.cloud.storage.BlobInfo;
import com.google.cloud.storage.Storage;
import com.google.cloud.storage.StorageException;
import in.backend.global.exception.GlobalException;
import in.backend.global.exception.GlobalExceptionCode;
import in.backend.global.property.GCSProperty;
import java.io.IOException;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;


@Slf4j
@Service
@RequiredArgsConstructor
public class ExternalImageProvider {

    private final Storage storage;
    private final GCSProperty gcsProperty;

    public String write(MultipartFile file) {
        try {
            var fileName = UUID.randomUUID().toString().replace("-", "");
            var blobId = gcsProperty.create(fileName);
            var blobInfo = BlobInfo.newBuilder(blobId)
                    .setContentType(file.getContentType())
                    .build();

            storage.create(blobInfo, file.getBytes());

            return gcsProperty.getFilePath(fileName);
        } catch (IOException | StorageException e) {
            throw new GlobalException(GlobalExceptionCode.IMAGE_SAVE_FAIL);
        }

    }

}
