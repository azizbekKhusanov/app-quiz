package uz.tuit.appquiz.controller;

import org.springframework.http.HttpEntity;
import org.springframework.web.bind.annotation.*;
import uz.tuit.appquiz.dto.CreateAdminDTO;
import uz.tuit.appquiz.dto.UserDTO;
import uz.tuit.appquiz.exceptions.ApiResult;

import java.util.List;

import static uz.tuit.appquiz.controller.UserController.BASE_PATH;

@RequestMapping(BASE_PATH)
public interface UserController {

    String BASE_PATH = "/api-user";

    @GetMapping("/allUsers")
    HttpEntity<ApiResult<List<UserDTO>>> getAllUsers();

    @GetMapping("/{id}")
    HttpEntity<ApiResult<UserDTO>> getUserById(@PathVariable Long id);

    @GetMapping("/allAdmins")
    HttpEntity<ApiResult<List<UserDTO>>> getAllAdmins();

    @PostMapping("/add/admin")
    HttpEntity<ApiResult<UserDTO>> addAdmin(@RequestBody CreateAdminDTO createAdminDTO);


}
