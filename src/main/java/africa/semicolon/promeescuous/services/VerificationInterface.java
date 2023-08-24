package africa.semicolon.promeescuous.services;


import africa.semicolon.promeescuous.dtos.requests.VerificationRequest;
import africa.semicolon.promeescuous.dtos.responses.VerificationResponse;
import africa.semicolon.promeescuous.models.User;

public interface VerificationInterface {
    User findUser(Long id);
    VerificationResponse uploadVideo(VerificationRequest verificationRequest);

    VerificationResponse approveVerification(Long userId);


    VerificationResponse disapproveVerification(long id);

}
