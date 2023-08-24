package africa.semicolon.promeescuous.services.cloud;

import africa.semicolon.promeescuous.models.Reaction;
import africa.semicolon.promiscuous.dtos.response.UploadMediaResponse;
import org.springframework.web.multipart.MultipartFile;

public interface MediaService {
    UploadMediaResponse uploadProfilePicture(MultipartFile file);
    UploadMediaResponse uploadMedia(MultipartFile file);
    String likeOrDislike(Reaction reaction, Long userId);
}