package uz.tuit.appquiz.controller;

import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import uz.tuit.appquiz.dto.LoginDTO;
import uz.tuit.appquiz.dto.RegisterDTO;
import uz.tuit.appquiz.dto.TokenDTO;
import uz.tuit.appquiz.exceptions.ApiResult;

import static uz.tuit.appquiz.controller.AuthController.BASE_PATH;

@RequestMapping(BASE_PATH)
public interface AuthController {

    String BASE_PATH = "/api-auth";

    @PostMapping("/login")
    HttpEntity<ApiResult<TokenDTO>> login(@RequestBody LoginDTO loginDTO);

    @PostMapping("/register")
    ResponseEntity<String> register(@RequestBody RegisterDTO registerDTO);

    @PostMapping("/verify")
    HttpEntity<ApiResult<TokenDTO>> verifyAndLogin(@RequestBody String code,
                                                   @RequestBody RegisterDTO registerDTO);


}
