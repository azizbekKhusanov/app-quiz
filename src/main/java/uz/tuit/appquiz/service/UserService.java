package uz.tuit.appquiz.service;


import uz.tuit.appquiz.dto.UserDTO;
import uz.tuit.appquiz.exceptions.ApiResult;

import java.util.List;

public interface UserService {
    ApiResult<List<UserDTO>> getAll();
    ApiResult<List<UserDTO>> getAllAdmins();
    ApiResult<List<UserDTO>> getAllUsers();
    ApiResult<UserDTO> getById(Long id);
    ApiResult<UserDTO> addAdmin(Long userId);
    ApiResult<UserDTO> removeAdmin(Long userId);

}
