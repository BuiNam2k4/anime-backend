package vn.kurisu.anime_service.dto.request;

import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;
import vn.kurisu.anime_service.exception.ErrorCode;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder

public class RegisterRequest {
    @Size( min =3, message= "USERNAME_INVALID")
    private String username;
    @Size(min =3, message = "PASSWORD_INVALID")
    private String password;


    private String email;
}
