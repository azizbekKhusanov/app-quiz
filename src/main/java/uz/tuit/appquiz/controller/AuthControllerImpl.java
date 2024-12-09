package uz.tuit.appquiz.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.web.bind.annotation.RestController;
import uz.tuit.appquiz.dto.LoginDTO;
import uz.tuit.appquiz.dto.RegisterDTO;
import uz.tuit.appquiz.dto.UserDTO;
import uz.tuit.appquiz.exceptions.ApiResult;
import uz.tuit.appquiz.service.AuthService;

@RestController
@RequiredArgsConstructor
public class AuthControllerImpl implements AuthController {

    private final AuthService authService;

    @Override
    public HttpEntity<ApiResult<UserDTO>> login(LoginDTO loginDTO) {
        return null;
    }

    @Override
    public HttpEntity<ApiResult<UserDTO>> register(RegisterDTO registerDTO) {
        return null;
    }
}
