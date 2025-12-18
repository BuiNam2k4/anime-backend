package vn.kurisu.anime_service.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder

public class RegisterRequest {
//    (min =3, message="Username phai co it nhat 3 ki tu")
    private String username;
    private String password;

    private String email;
}
