package dawflix_api.mapper;

import org.springframework.stereotype.Component;

import dawflix_api.dto.UserDTO;
import dawflix_api.model.User;

@Component
public class UserMapper {
    public UserDTO toDTO(User user){
        return UserDTO.builder()
            .id(user.getId())
            .username(user.getUsername())
            .email(user.getEmail())
            .build();
    }
}
