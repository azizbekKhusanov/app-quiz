package uz.tuit.appquiz.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import uz.tuit.appquiz.dto.UserDTO;
import uz.tuit.appquiz.entity.User;
import uz.tuit.appquiz.enums.Role;
import uz.tuit.appquiz.exceptions.ApiResult;
import uz.tuit.appquiz.exceptions.RestException;
import uz.tuit.appquiz.repository.UserRepository;
import uz.tuit.appquiz.service.mapper.UserMapper;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;

    @Override
    public ApiResult<List<UserDTO>> getAll() {
        List<User> allUser = userRepository.findAll();
        if (allUser.isEmpty()) {
            throw RestException.restThrow("This list is empty !!! ");
        }
        return ApiResult.successResponse(allUser.stream()
                .map(userMapper::convertToDTO)
                .toList());
    }

    @Override
    public ApiResult<List<UserDTO>> getAllAdmins() {
        List<User> admins = userRepository.findByRole(Role.ADMIN);

        if (admins.isEmpty()) {
            throw RestException.restThrow("Admin list is empty !!! ");
        }

        return ApiResult.successResponse(admins.stream()
                .map(userMapper::convertToDTO)
                .toList());
    }

    @Override
    public ApiResult<List<UserDTO>> getAllUsers() {
        List<User> users = userRepository.findByRole(Role.USER);

        if (users.isEmpty()) {
            throw RestException.restThrow("User list is empty !!! ");
        }

        return ApiResult.successResponse(users.stream()
                .map(userMapper::convertToDTO)
                .toList());
    }

    @Override
    public ApiResult<UserDTO> getById(Long id) {
        if (id <= 0) {
            throw RestException.restThrow("ID is INCORRECT");
        }
        User user = userRepository.findById(id)
                .orElseThrow(() -> RestException.restThrow("User not found !!! "));

        return ApiResult.successResponse(userMapper.convertToDTO(user));
    }

    @Override
    public ApiResult<UserDTO> addAdmin(Long userId) {
        if (userId <= 0) {
            throw RestException.restThrow("ID is INCORRECT");
        }

        User user = userRepository.findById(userId).orElseThrow(() ->
                RestException.restThrow("User not found !!!"));
        user.setRole(Role.ADMIN);
        return ApiResult.successResponse(userMapper.convertToDTO(userRepository.save(user)));
    }

    @Override
    public ApiResult<UserDTO> removeAdmin(Long userId) {
        if (userId <= 0) {
            throw RestException.restThrow("ID is INCORRECT");
        }

        User user = userRepository.findById(userId).orElseThrow(() ->
                RestException.restThrow("User not found !!!"));
        user.setRole(Role.USER);
        return ApiResult.successResponse(userMapper.convertToDTO(userRepository.save(user)));
    }

}
