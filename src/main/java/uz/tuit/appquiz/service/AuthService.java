package uz.tuit.appquiz.service;


import org.springframework.security.core.userdetails.UserDetailsService;
import uz.tuit.appquiz.dto.LoginDTO;
import uz.tuit.appquiz.dto.RegisterDTO;
import uz.tuit.appquiz.dto.TokenDTO;
import uz.tuit.appquiz.exceptions.ApiResult;

public interface AuthService extends UserDetailsService {

    ApiResult<TokenDTO> login(LoginDTO loginDTO);
    String register(RegisterDTO registerDTO);
    ApiResult<TokenDTO> verifyAndLogin(String code, RegisterDTO registerDTO);

}
