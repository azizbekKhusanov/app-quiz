package uz.tuit.appquiz.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import uz.tuit.appquiz.dto.LoginDTO;
import uz.tuit.appquiz.dto.RegisterDTO;
import uz.tuit.appquiz.dto.UserDTO;
import uz.tuit.appquiz.exceptions.ApiResult;
import uz.tuit.appquiz.service.AuthService;
import uz.tuit.appquiz.utils.AppConstants;

@RestController
//@CrossOrigin(AppConstants.REACT_URL)
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
