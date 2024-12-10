package uz.tuit.appquiz.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import uz.tuit.appquiz.dto.LoginDTO;
import uz.tuit.appquiz.dto.RegisterDTO;
import uz.tuit.appquiz.dto.TokenDTO;
import uz.tuit.appquiz.exceptions.ApiResult;
import uz.tuit.appquiz.service.AuthService;

@RestController
@RequiredArgsConstructor
public class AuthControllerImpl implements AuthController {

    private final AuthService authService;

    @Override
    public HttpEntity<ApiResult<TokenDTO>> login(LoginDTO loginDTO) {
        return ResponseEntity.ok(authService.login(loginDTO));
    }

    @Override
    public ResponseEntity<String> register(RegisterDTO registerDTO) {
        return ResponseEntity.ok(authService.register(registerDTO));
    }

    @Override
    public HttpEntity<ApiResult<TokenDTO>> verifyAndLogin(String code, RegisterDTO registerDTO) {
        return ResponseEntity.ok(authService.verifyAndLogin(code, registerDTO));
    }
}
