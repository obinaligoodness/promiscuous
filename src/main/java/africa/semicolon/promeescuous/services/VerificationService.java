package africa.semicolon.promeescuous.services;


import africa.semicolon.promeescuous.dtos.requests.VerificationRequest;
import africa.semicolon.promeescuous.dtos.responses.VerificationResponse;
import africa.semicolon.promeescuous.exceptions.PromiscuousBaseException;
import africa.semicolon.promeescuous.exceptions.UserNotFoundException;
import africa.semicolon.promeescuous.models.User;
import africa.semicolon.promeescuous.models.Verification;
import africa.semicolon.promeescuous.repositories.UserRepository;
import africa.semicolon.promeescuous.repositories.VerificationRepository;
import africa.semicolon.promeescuous.services.cloud.CloudService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import static africa.semicolon.promeescuous.dtos.responses.ResponseMessage.*;
import static africa.semicolon.promeescuous.exceptions.ExceptionMessage.USER_NOT_FOUND_EXCEPTION;
import static africa.semicolon.promeescuous.models.VerificationStatus.*;

@Service
@AllArgsConstructor
@Slf4j
public class VerificationService implements VerificationInterface{
    @Autowired
    VerificationRepository verificationRepository;
    @Autowired
    CloudService cloudService;
    @Autowired
    UserRepository userRepository;

    @Override
    public User findUser(Long id) {

        return userRepository.findById(id).orElseThrow(()-> new UserNotFoundException(USER_NOT_FOUND_EXCEPTION.getMessage()));

    }

    @Override
    public VerificationResponse uploadVideo(VerificationRequest verificationRequest) {
        Long userId = verificationRequest.getId();
        MultipartFile userVideo = verificationRequest.getCloudinaryUserVideo();
        Verification verification = new Verification();

        try{

            User foundUser = findUser(userId);
            verification.setUserId(foundUser.getId());

            String videoUrl = cloudService.uploadVideo(userVideo);
            log.info(videoUrl);
            verification.setCloudinaryVideoUrl(videoUrl);

            verification.setVerificationStatus(PENDING.toString());

            verificationRepository.save(verification);

            return new VerificationResponse(VERIFICATION_IN_PROGRESS.name());


        }catch(RuntimeException e){
            throw new PromiscuousBaseException(e.getMessage());
        }

    }

    @Override
    public VerificationResponse approveVerification(Long userId) {
        User foundUser = findUser(userId);
        Verification verification = new Verification();
        verification.setUserId(foundUser.getId());
        verification.setVerificationStatus(APPROVED.toString());
        Verification foundVerification= verificationRepository.findById(userId).orElseThrow(()-> new UserNotFoundException(USER_NOT_FOUND_EXCEPTION.getMessage()));
        verification.setCloudinaryVideoUrl(foundVerification.getCloudinaryVideoUrl());
        verificationRepository.save(verification);
        return new VerificationResponse(VERIFICATION_APPROVED.name());}

    @Override
    public VerificationResponse disapproveVerification(long userId) {
        User foundUser = findUser(userId);
        Verification verification = new Verification();
        verification.setUserId(foundUser.getId());
        verification.setVerificationStatus(REJECTED.toString());
        Verification foundVerification= verificationRepository.findById(userId).orElseThrow(()-> new UserNotFoundException(USER_NOT_FOUND_EXCEPTION.getMessage()));
        verification.setCloudinaryVideoUrl(foundVerification.getCloudinaryVideoUrl());
        verificationRepository.save(verification);
        return new VerificationResponse(VERIFICATION_DISAPPROVED.name());
    }
}
