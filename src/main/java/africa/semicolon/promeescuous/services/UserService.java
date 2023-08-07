package africa.semicolon.promeescuous.services;

import africa.semicolon.promeescuous.dtos.requests.RegisterUserRequest;
import africa.semicolon.promeescuous.dtos.responses.RegisterUserResponse;

public interface UserService {
    RegisterUserResponse register(RegisterUserRequest registerUserRequest);
}
