package africa.semicolon.promeescuous.services;


import africa.semicolon.promeescuous.dtos.requests.RegisterUserRequest;
import africa.semicolon.promeescuous.dtos.responses.ApiResponse;
import africa.semicolon.promeescuous.dtos.responses.GetUserResponse;
import africa.semicolon.promeescuous.dtos.responses.RegisterUserResponse;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
@ActiveProfiles("test")
@Slf4j
public class UserServiceTest {
    @Autowired
    private UserService userService;

    private RegisterUserRequest registerUserRequest;
    private RegisterUserResponse registerUserResponse;
    @BeforeEach
    public void setUp(){
        //user fills registration form
        registerUserRequest = new RegisterUserRequest();
        registerUserRequest.setEmail("rofime9859@royalka.com");
        registerUserRequest.setPassword("password");
        //user submits form by calling register method
    }

    @Test
    public void testThatUserCanRegister(){
        registerUserResponse = userService.register(registerUserRequest);
        assertNotNull(registerUserResponse);
        assertNotNull(registerUserResponse.getMessage());
    }
    @Test
    public void testActivateUserAccount(){
        registerUserRequest.setEmail("test@email.com");
        registerUserResponse=userService.register(registerUserRequest);
        assertNotNull(registerUserResponse);
        ApiResponse<?> activateUserAccountResponse =
                userService.activateUserAccount("abc1234.erytuuoi.67t75646");
       assertThat(activateUserAccountResponse).isNotNull();
    }

    @Test
    public void getUserByIdTest(){
        userService.register(registerUserRequest);
        GetUserResponse response = userService.getUserById(1L);
        assertThat(response).isNotNull();
        assertThat(response.getEmail()).isEqualTo(registerUserRequest.getEmail());
    }

    @Test
    public void getAllUsers(){
        registerTestUsers();
        List<GetUserResponse> users = userService.getAllUsers(1, 5);
        log.info("users-->{}", users);
        assertThat(users).isNotNull();
        assertThat(users.size()).isEqualTo(5);
    }

    private void registerTestUsers() {
        RegisterUserRequest request = new RegisterUserRequest();
        request.setEmail("john@email.com");
        request.setPassword("password");
        userService.register(request);

        request.setEmail("jane@email.com");
        request.setPassword("password");
        userService.register(request);


        request.setEmail("johnny@email.com");
        request.setPassword("password");
        userService.register(request);

        request.setEmail("joey@email.com");
        request.setPassword("password");
        userService.register(request);

        request.setEmail("zaza@email.com");
        request.setPassword("password");
        userService.register(request);

        request.setEmail("nedu@email.com");
        request.setPassword("password");
        userService.register(request);
    }
}



