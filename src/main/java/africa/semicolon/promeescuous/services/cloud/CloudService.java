package africa.semicolon.promeescuous.services.cloud;

import africa.semicolon.promeescuous.dtos.responses.ApiResponse;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public interface CloudService {
    String upload(MultipartFile file);
    String uploadVideo(MultipartFile file);
}
