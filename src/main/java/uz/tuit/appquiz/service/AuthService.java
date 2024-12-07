package uz.tuit.appquiz.service;


import uz.tuit.appquiz.dto.LoginDTO;
import uz.tuit.appquiz.dto.RegisterDTO;
import uz.tuit.appquiz.dto.UserDTO;
import uz.tuit.appquiz.exceptions.ApiResult;

public interface AuthService {

    ApiResult<UserDTO> login(LoginDTO loginDTO);

    ApiResult<UserDTO> register(RegisterDTO registerDTO);

}
