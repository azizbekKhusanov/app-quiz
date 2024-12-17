package uz.tuit.appquiz.controller;

import org.springframework.http.HttpEntity;
import org.springframework.web.bind.annotation.*;
import uz.tuit.appquiz.dto.CreateAdminDTO;
import uz.tuit.appquiz.dto.UserDTO;
import uz.tuit.appquiz.entity.User;
import uz.tuit.appquiz.exceptions.ApiResult;

import java.util.List;

import static uz.tuit.appquiz.controller.UserController.BASE_PATH;


@RequestMapping(BASE_PATH)
public interface UserController {

    String BASE_PATH = "/api-user";

    @GetMapping("/all")
    HttpEntity<ApiResult<List<UserDTO>>> getAll();
    @GetMapping("/allAdmins")
    HttpEntity<ApiResult<List<UserDTO>>> getAllAdmins();

    @GetMapping("/allUsers")
    HttpEntity<ApiResult<List<UserDTO>>> getAllUsers();

    @GetMapping("/{id}")
    HttpEntity<ApiResult<UserDTO>> getUserById(@PathVariable Long id);


    @PostMapping("/add-admin")
    HttpEntity<ApiResult<UserDTO>> addAdmin(@RequestBody Long userId);

    @PostMapping("/remove-admin")
    HttpEntity<ApiResult<UserDTO>> removeAdmin(@RequestBody Long userId);




}
