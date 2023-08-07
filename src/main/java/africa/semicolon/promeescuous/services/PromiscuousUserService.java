package africa.semicolon.promeescuous.services;

import africa.semicolon.promeescuous.dtos.requests.RegisterUserRequest;
import africa.semicolon.promeescuous.dtos.responses.RegisterUserResponse;
import africa.semicolon.promeescuous.models.User;
import africa.semicolon.promeescuous.repositories.UserRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

@Repository
@AllArgsConstructor
@Slf4j
public class PromiscuousUserService implements UserService{
    private final UserRepository userRepository;

    @Override
    public RegisterUserResponse register(RegisterUserRequest registerUserRequest) {
        //1. extract registration details from the registration form(registerUserRequest)
        String email = registerUserRequest.getEmail();
        String password = registerUserRequest.getPassword();
        //2. create a user profile with the registration details
        User user = new User();
        user.setEmail(email);
        user.setPassword(password);
        //3. save that users profile in the Database
        User savedUser = userRepository.save(user);
        log.info("saved guy-->{}", savedUser);
        //4. send verification token to the users email
        String emailResponse=MockEmailService.sendEmail(savedUser.getEmail());
        log.info("email sending response->{}", emailResponse);
        //5. return a response
        RegisterUserResponse registerUserResponse = new RegisterUserResponse();
        registerUserResponse.setMessage("Registration Successful, check your email inbox for verification token");

        return registerUserResponse;
    }
}
