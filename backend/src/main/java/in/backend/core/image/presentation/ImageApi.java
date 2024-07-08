package in.backend.core.image.presentation;


import in.backend.core.auth.domain.Visitor;
import in.backend.core.auth.domain.attributes.AdminOnly;
import in.backend.core.auth.domain.attributes.Auth;
import in.backend.global.provider.ExternalImageProvider;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/admin/images")
public class ImageApi {
    private final ExternalImageProvider externalImageProvider;

    @AdminOnly
    @PostMapping
    public ImageUploadResponse upload(
            @Auth Visitor visitor,
            @RequestPart(name = "thumbnail") MultipartFile thumbnail
    ) {
        log.info("image upload {}", visitor.toAdmin());
        return new ImageUploadResponse(externalImageProvider.write(thumbnail));
    }

    public record ImageUploadResponse(
            String imageUrl
    ) {

    }
}
