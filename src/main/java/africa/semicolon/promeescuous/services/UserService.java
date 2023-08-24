package africa.semicolon.promeescuous.services;

import africa.semicolon.promeescuous.dtos.requests.LoginRequest;
import africa.semicolon.promeescuous.dtos.requests.*;
import africa.semicolon.promeescuous.dtos.requests.RegisterUserRequest;
import africa.semicolon.promeescuous.dtos.requests.UpdateUserRequest;
import africa.semicolon.promeescuous.dtos.responses.*;
import africa.semicolon.promeescuous.models.User;
import africa.semicolon.promeescuous.models.Reaction;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface UserService {
    RegisterUserResponse register(RegisterUserRequest registerUserRequest);
    LoginResponse login(LoginRequest loginRequest);
    ApiResponse<?> activateUserAccount(String token);

    GetUserResponse getUserById(Long id);

    List<GetUserResponse> getAllUsers(int page, int pageSize);

    User findUserById(Long id);

    UpdateUserResponse updateProfile(UpdateUserRequest updateUserRequest, Long id);

    UploadMediaResponse uploadMedia(MultipartFile mediaToUpload);

    UploadMediaResponse uploadProfilePicture(MultipartFile mediaToUpload);

    ApiResponse<?> reactToMedia(MediaReactionRequest mediaReactionRequest);
}
