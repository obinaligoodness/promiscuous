package africa.semicolon.promeescuous.services;

import africa.semicolon.promeescuous.dtos.requests.LoginRequest;
import africa.semicolon.promeescuous.dtos.requests.RegisterUserRequest;
import africa.semicolon.promeescuous.dtos.requests.UpdateUserRequest;
import africa.semicolon.promeescuous.dtos.responses.*;
import africa.semicolon.promeescuous.models.User;

import java.util.List;

public interface UserService {
    RegisterUserResponse register(RegisterUserRequest registerUserRequest);
    LoginResponse login(LoginRequest loginRequest);
    ApiResponse<?> activateUserAccount(String token);

    GetUserResponse getUserById(Long id);

    List<GetUserResponse> getAllUsers(int page, int pageSize);

    User findUserById(Long id);

    UpdateUserResponse updateProfile(UpdateUserRequest updateUserRequest, Long id);
}
