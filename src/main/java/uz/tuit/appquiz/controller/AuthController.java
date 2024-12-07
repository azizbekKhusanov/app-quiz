package uz.tuit.appquiz.controller;

import org.springframework.http.HttpEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import uz.tuit.appquiz.dto.LoginDTO;
import uz.tuit.appquiz.dto.RegisterDTO;
import uz.tuit.appquiz.dto.UserDTO;
import uz.tuit.appquiz.exceptions.ApiResult;

import static uz.tuit.appquiz.controller.AuthController.BASE_PATH;

@RequestMapping(BASE_PATH)
public interface AuthController {

    String BASE_PATH = "/api-auth";

    @PostMapping("/login")
    HttpEntity<ApiResult<UserDTO>> login(LoginDTO loginDTO);

    @PostMapping("/register")
    HttpEntity<ApiResult<UserDTO>> register(RegisterDTO registerDTO);

}
