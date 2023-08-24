package africa.semicolon.promeescuous.dtos.responses;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;

@AllArgsConstructor
@Builder
public class ActivateAccountResponse {
    private String message;

    private GetUserResponse user;
}
