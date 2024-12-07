package uz.tuit.appquiz.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import uz.tuit.appquiz.dto.LoginDTO;
import uz.tuit.appquiz.dto.RegisterDTO;
import uz.tuit.appquiz.dto.UserDTO;
import uz.tuit.appquiz.exceptions.ApiResult;
import uz.tuit.appquiz.repository.UserRepository;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final UserRepository userRepository;


    @Override
    public ApiResult<UserDTO> login(LoginDTO loginDTO) {
        // TODO
        return null;
    }

    @Override
    public ApiResult<UserDTO> register(RegisterDTO registerDTO) {
        // TODO
        return null;
    }
}
