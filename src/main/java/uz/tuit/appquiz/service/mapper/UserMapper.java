package uz.tuit.appquiz.service.mapper;

import org.springframework.stereotype.Component;
import uz.tuit.appquiz.dto.UserDTO;
import uz.tuit.appquiz.entity.User;

@Component
public class UserMapper {

    public UserDTO convertToDTO(User user) {
        return UserDTO.builder()
                .id(user.getId())
                .name(user.getName())
                .surname(user.getSurname())
                .username(user.getUsername())
                .email(user.getEmail())
                .role(user.getRole().toString())
                .build();
    }

}
