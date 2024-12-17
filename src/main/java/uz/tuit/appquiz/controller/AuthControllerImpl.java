package uz.tuit.appquiz.controller;

import lombok.RequiredArgsConstructor;
import org.apache.coyote.Response;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import uz.tuit.appquiz.dto.ChangePasswordDTO;
import uz.tuit.appquiz.dto.LoginDTO;
import uz.tuit.appquiz.dto.RegisterDTO;
import uz.tuit.appquiz.dto.UserDTO;
import uz.tuit.appquiz.exceptions.ApiResult;
import uz.tuit.appquiz.service.AuthService;
import uz.tuit.appquiz.service.UserService;

@RestController
@RequiredArgsConstructor
public class AuthControllerImpl implements AuthController {

    private final AuthService authService;

    @Override   
    public HttpEntity<ApiResult<UserDTO>> login(LoginDTO loginDTO) {
        return ResponseEntity.ok(authService.login(loginDTO));
    }

    @Override
    public ResponseEntity<String> register(RegisterDTO registerDTO) {
        return ResponseEntity.ok(authService.register(registerDTO));
    }

    @Override
    public HttpEntity<ApiResult<UserDTO>> verifyAndLogin(String code) {
        return ResponseEntity.ok(authService.verifyAndLogin(code));
    }

    @Override
    public HttpEntity<ApiResult<UserDTO>> changePassword(ChangePasswordDTO changePasswordDTO) {
        return ResponseEntity.ok(authService.changePassword(changePasswordDTO));
    }
}
