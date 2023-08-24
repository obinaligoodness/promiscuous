package africa.semicolon.promeescuous.services;

import africa.semicolon.promeescuous.dtos.requests.MediaReactionRequest;
import africa.semicolon.promeescuous.dtos.responses.UploadMediaResponse;
import org.springframework.web.multipart.MultipartFile;

public interface MediaService {
    UploadMediaResponse uploadProfilePicture(MultipartFile file);
    UploadMediaResponse uploadMedia(MultipartFile file);
    String reactToMedia(MediaReactionRequest mediaReactionRequest);
}