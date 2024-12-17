package uz.tuit.appquiz.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import uz.tuit.appquiz.dto.UserDTO;
import uz.tuit.appquiz.exceptions.ApiResult;
import uz.tuit.appquiz.service.UserService;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class UserControllerImpl implements UserController {

    private final UserService userService;

    @Override
    public HttpEntity<ApiResult<List<UserDTO>>> getAll() {
        return ResponseEntity.ok(userService.getAll());
    }

    @Override
    public HttpEntity<ApiResult<UserDTO>> getUserById(Long id) {
        return ResponseEntity.ok(userService.getById(id));
    }

    @Override
    public HttpEntity<ApiResult<List<UserDTO>>> getAllUsers() {
        return ResponseEntity.ok(userService.getAllUsers());
    }

    @Override
    public HttpEntity<ApiResult<List<UserDTO>>> getAllAdmins() {
        return ResponseEntity.ok(userService.getAllAdmins());
    }

    @Override
    public HttpEntity<ApiResult<UserDTO>> addAdmin(Long userId) {
        return ResponseEntity.ok(userService.addAdmin(userId));
    }

    @Override
    public HttpEntity<ApiResult<UserDTO>> removeAdmin(Long userId) {
        return ResponseEntity.ok(userService.removeAdmin(userId));
    }
}
