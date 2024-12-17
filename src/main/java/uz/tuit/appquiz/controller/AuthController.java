package uz.tuit.appquiz.controller;

import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import uz.tuit.appquiz.dto.ChangePasswordDTO;
import uz.tuit.appquiz.dto.LoginDTO;
import uz.tuit.appquiz.dto.RegisterDTO;
import uz.tuit.appquiz.dto.UserDTO;
import uz.tuit.appquiz.exceptions.ApiResult;

import static uz.tuit.appquiz.controller.AuthController.BASE_PATH;

@RequestMapping(BASE_PATH)
public interface AuthController {

    String BASE_PATH = "/api-auth";

    @PostMapping("/login")
    HttpEntity<ApiResult<UserDTO>> login(@RequestBody LoginDTO loginDTO);

    @PostMapping("/register")
    ResponseEntity<String> register(@RequestBody RegisterDTO registerDTO);

    @PostMapping("/verify")
    HttpEntity<ApiResult<UserDTO>> verifyAndLogin(@RequestParam("code") String code);

    @PostMapping("/change-password")
    HttpEntity<ApiResult<UserDTO>> changePassword(@RequestBody ChangePasswordDTO changePasswordDTO);

}
