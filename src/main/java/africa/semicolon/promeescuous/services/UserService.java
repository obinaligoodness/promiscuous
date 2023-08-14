package africa.semicolon.promeescuous.services;

import africa.semicolon.promeescuous.dtos.requests.RegisterUserRequest;
import africa.semicolon.promeescuous.dtos.responses.ApiResponse;
import africa.semicolon.promeescuous.dtos.responses.GetUserResponse;
import africa.semicolon.promeescuous.dtos.responses.RegisterUserResponse;
import africa.semicolon.promeescuous.models.User;

import java.util.List;

public interface UserService {
    RegisterUserResponse register(RegisterUserRequest registerUserRequest);

    ApiResponse<?> activateUserAccount(String token);


    GetUserResponse getUserById(Long id);

    List<GetUserResponse> getAllUsers(int page, int pageSize);
}
