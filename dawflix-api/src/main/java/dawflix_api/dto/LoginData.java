package dawflix_api.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class LoginData {
    private UserDTO usuario;
    private String accessToken;
    private String refreshToken;
}
