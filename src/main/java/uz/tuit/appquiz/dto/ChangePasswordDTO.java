package uz.tuit.appquiz.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class ChangePasswordDTO {

    private Long id;
    private String curPassword;
    private String password;
    private String prePassword;

}
