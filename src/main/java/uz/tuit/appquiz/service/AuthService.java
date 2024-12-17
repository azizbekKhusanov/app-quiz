package uz.tuit.appquiz.service;


import uz.tuit.appquiz.dto.ChangePasswordDTO;
import uz.tuit.appquiz.dto.LoginDTO;
import uz.tuit.appquiz.dto.RegisterDTO;
import uz.tuit.appquiz.dto.UserDTO;
import uz.tuit.appquiz.exceptions.ApiResult;

public interface AuthService {

    ApiResult<UserDTO> login(LoginDTO loginDTO);
    String register(RegisterDTO registerDTO);
    ApiResult<UserDTO> verifyAndLogin(String code);
    ApiResult<UserDTO> changePassword(ChangePasswordDTO changePasswordDTO);
}
