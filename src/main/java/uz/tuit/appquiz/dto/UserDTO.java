package uz.tuit.appquiz.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class UserDTO {

    private Long id;
    private String name;
    private String surname;
    private String username;
    private String email;
    private String role;

}
