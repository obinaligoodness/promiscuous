package africa.semicolon.promeescuous.services.verification;


import africa.semicolon.promeescuous.dtos.requests.VerificationRequest;
import africa.semicolon.promeescuous.dtos.responses.VerificationResponse;
import africa.semicolon.promeescuous.models.User;

public interface VerificationService {
    User findUser(Long id);
    VerificationResponse uploadVideo(VerificationRequest verificationRequest);

    VerificationResponse approveVerification(Long userId);


    VerificationResponse disapproveVerification(long id);

}
