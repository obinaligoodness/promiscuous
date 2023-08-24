package africa.semicolon.promeescuous.services;


import africa.semicolon.promeescuous.dtos.requests.RegisterUserRequest;
import africa.semicolon.promeescuous.dtos.requests.VerificationRequest;
import africa.semicolon.promeescuous.dtos.responses.VerificationResponse;
import africa.semicolon.promeescuous.models.User;
import africa.semicolon.promeescuous.services.cloud.CloudService;
import africa.semicolon.promeescuous.utils.AppUtil;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
@Slf4j
public class VerificationServiceTest {

    @Autowired
    private CloudService cloudService;
    @Autowired
    private VerificationService verificationService;


    @Autowired
    private UserService userService;

    RegisterUserRequest registerUserRequest;

//    @BeforeEach
//    public void setUp(){
//
//    }

    @Test
    public void testFoundUser(){
        registerUserRequest = new RegisterUserRequest();
        registerUserRequest.setEmail("xogv34624@vreaa.com");
        registerUserRequest.setPassword("password");
        var registerUserResponse = userService.register(registerUserRequest);
        assertNotNull(registerUserResponse);
        assertNotNull(registerUserResponse.getMessage());

        User foundUser = verificationService.findUser(1L);

        assertThat(foundUser).isNotNull();
    }


    @Test
    void testUploadVideo(){
        registerUserRequest = new RegisterUserRequest();
        registerUserRequest.setEmail("xogo34624@vreaa.com");
        registerUserRequest.setPassword("password");
        var registerUserResponse = userService.register(registerUserRequest);
        assertNotNull(registerUserResponse);
        assertNotNull(registerUserResponse.getMessage());
        Path path = Paths.get(AppUtil.TEST_VIDEO_LOCATION);
        InputStream inputStream = null;
        try {
            inputStream = Files.newInputStream(path);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        MultipartFile file = null;
        try {
            file = new MockMultipartFile("testVideo",inputStream);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        VerificationRequest verificationRequest = new VerificationRequest();
        verificationRequest.setId(1L);
        verificationRequest.setCloudinaryUserVideo(file);

        VerificationResponse response = verificationService.uploadVideo(verificationRequest);
        log.info(response.getMessage());

        assertThat(response.getMessage()).isNotNull();
    }
    @Test
    public void testThatAdminCanApproveVerification(){
        registerUserRequest = new RegisterUserRequest();
        registerUserRequest.setEmail("xogo34624@vreaa.com");
        registerUserRequest.setPassword("password");
        var registerUserResponse = userService.register(registerUserRequest);
        assertNotNull(registerUserResponse);
        assertNotNull(registerUserResponse.getMessage());
        Path path = Paths.get(AppUtil.TEST_VIDEO_LOCATION);
        InputStream inputStream = null;
        try {
            inputStream = Files.newInputStream(path);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        MultipartFile file = null;
        try {
            file = new MockMultipartFile("testVideo",inputStream);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        VerificationRequest verificationRequest = new VerificationRequest();
        verificationRequest.setId(1L);
        verificationRequest.setCloudinaryUserVideo(file);

        VerificationResponse response = verificationService.uploadVideo(verificationRequest);
        log.info(response.getMessage());

        assertThat(response.getMessage()).isNotNull();
        VerificationResponse verified = verificationService.approveVerification(1L);
        log.info(verified.getMessage());
        assertThat(verified.getMessage()).isNotNull();
    }
    @Test
    public void testThatAdminCanDisapproveVerification(){
        registerUserRequest = new RegisterUserRequest();
        registerUserRequest.setEmail("xogo34624@vreaa.com");
        registerUserRequest.setPassword("password");
        var registerUserResponse = userService.register(registerUserRequest);
        assertNotNull(registerUserResponse);
        assertNotNull(registerUserResponse.getMessage());
        Path path = Paths.get(AppUtil.TEST_VIDEO_LOCATION);
        InputStream inputStream = null;
        try {
            inputStream = Files.newInputStream(path);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        MultipartFile file = null;
        try {
            file = new MockMultipartFile("testVideo",inputStream);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        VerificationRequest verificationRequest = new VerificationRequest();
        verificationRequest.setId(1L);
        verificationRequest.setCloudinaryUserVideo(file);

        VerificationResponse response = verificationService.uploadVideo(verificationRequest);
        log.info(response.getMessage());

        assertThat(response.getMessage()).isNotNull();
        VerificationResponse verified = verificationService.disapproveVerification(1L);
        log.info(verified.getMessage());
        assertThat(verified.getMessage()).isNotNull();
    }

}
