package uz.tuit.appquiz.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import uz.tuit.appquiz.dto.ChangePasswordDTO;
import uz.tuit.appquiz.dto.LoginDTO;
import uz.tuit.appquiz.dto.RegisterDTO;
import uz.tuit.appquiz.dto.UserDTO;
import uz.tuit.appquiz.entity.User;
import uz.tuit.appquiz.enums.Role;
import uz.tuit.appquiz.exceptions.ApiResult;
import uz.tuit.appquiz.exceptions.RestException;
import uz.tuit.appquiz.repository.UserRepository;
import uz.tuit.appquiz.service.mapper.UserMapper;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final EmailService emailService;
    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final Map<String, String> verificationUsers = new HashMap<>();
    private User user;

    @Override
    public ApiResult<UserDTO> login(LoginDTO loginDTO) {
        User user = userRepository.findByUsername(loginDTO.getUsername()).orElseThrow(() ->
                RestException.restThrow("Username or Password is wrong !!!"));
        if (!Objects.equals(user.getPassword(), loginDTO.getPassword())) {
            throw RestException.restThrow("Username or Password is wrong !!!");
        }

        return ApiResult.successResponse(userMapper.convertToDTO(user));
    }

    @Override
    public ApiResult<UserDTO> verifyAndLogin(String code) {
        if (!verify(code)) {
            verificationUsers.remove(user.getEmail());
            user = new User();
            throw RestException.restThrow("Invalid code !!! ");
        }

        User saveUser = userRepository.save(user);
        verificationUsers.remove(user.getEmail());
        user = new User();
        return ApiResult.successResponse(userMapper.convertToDTO(saveUser));
    }

    @Override
    public String register(RegisterDTO registerDTO) {
        checkUserFields(registerDTO);
        String code = emailService.sendVerificationEmail(registerDTO.getEmail());
        user = User.builder()
                .name(registerDTO.getName())
                .surname(registerDTO.getSurname())
                .username(registerDTO.getUsername())
                .email(registerDTO.getEmail())
                .password(registerDTO.getPassword())
                .role(Role.USER)
                .build();
        verificationUsers.put(user.getEmail(), code);


        return "Verification code sent to " + registerDTO.getEmail();
    }

    @Override
    public ApiResult<UserDTO> changePassword(ChangePasswordDTO changePasswordDTO) {
        User user1 = userRepository.findById(changePasswordDTO.getId())
                .orElseThrow(() -> RestException.restThrow("User not found !!!"));
        if (!Objects.equals(user1.getPassword(), changePasswordDTO.getCurPassword())) {
            throw RestException.restThrow("Wrong current password !!! ");
        }
        if (!Objects.equals(changePasswordDTO.getPassword(), changePasswordDTO.getPrePassword())) {
            throw RestException.restThrow("Passwords don't match");
        }

        user1.setPassword(changePasswordDTO.getPassword());

        return ApiResult.successResponse(userMapper.convertToDTO(userRepository.save(user1)));
    }

    private boolean verify(String code) {
        return Objects.equals(verificationUsers.get(user.getEmail()), code);
    }

    private void checkUserFields(RegisterDTO registerDTO) {
        if (userRepository.existsByUsername(registerDTO.getUsername())) {
            throw RestException.restThrow("This username is available !!!");
        }
        if (userRepository.existsByEmail(registerDTO.getEmail())) {
            throw RestException.restThrow("This email is available !!!");
        }
        if (!Objects.equals(registerDTO.getPassword(), registerDTO.getPrePassword())) {
            throw RestException.restThrow("Passwords don't match");
        }
    }
}
