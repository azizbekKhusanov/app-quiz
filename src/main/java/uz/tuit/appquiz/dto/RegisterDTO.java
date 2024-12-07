package uz.tuit.appquiz.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RegisterDTO {

    private String username;
    private String name;
    private String surname;
    private String email;
    private String password;
    private String prePassword;

}
