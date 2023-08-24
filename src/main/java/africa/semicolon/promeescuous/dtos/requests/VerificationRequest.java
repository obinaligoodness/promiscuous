package africa.semicolon.promeescuous.dtos.requests;

import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

@Setter @Getter
public class VerificationRequest {
    private Long id;
    private MultipartFile cloudinaryUserVideo;
}
