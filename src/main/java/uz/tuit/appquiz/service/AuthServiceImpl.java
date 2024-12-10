package uz.tuit.appquiz.service;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import uz.tuit.appquiz.dto.LoginDTO;
import uz.tuit.appquiz.dto.RegisterDTO;
import uz.tuit.appquiz.dto.TokenDTO;
import uz.tuit.appquiz.entity.User;
import uz.tuit.appquiz.enums.Role;
import uz.tuit.appquiz.exceptions.ApiResult;
import uz.tuit.appquiz.exceptions.RestException;
import uz.tuit.appquiz.repository.UserRepository;
import uz.tuit.appquiz.security.CurrentUserDetails;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Service
public class AuthServiceImpl implements AuthService {

    private final UserRepository userRepository;
    private final AuthenticationManager authenticationManager;
    private final EmailService emailService;
    private final PasswordEncoder passwordEncoder;

    private final Map<String, String> verificationCodes = new HashMap<>();

    @Value(value = "${app.jwt.access.token.key}")
    private String accessTokenKey;

    @Value(value = "${app.jwt.access.token.expiration.time}")
    private long accessTokenExpirationTime;

    @Value(value = "${app.jwt.refresh.token.key}")
    private String refreshTokenKey;

    @Value(value = "${app.jwt.refresh.token.expiration.time}")
    private long refreshTokenExpirationTime;

    public AuthServiceImpl(UserRepository userRepository,
                           @Lazy AuthenticationManager authenticationManager,
                           EmailService emailService,
                           @Lazy PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.authenticationManager = authenticationManager;
        this.emailService = emailService;
        this.passwordEncoder = passwordEncoder;
    }


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByEmail(username).orElseThrow(() ->
                new UsernameNotFoundException(username + " not found !!!"));
        return new CurrentUserDetails(user);
    }

    @Override
    public ApiResult<TokenDTO> login(LoginDTO loginDTO) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginDTO.getUsername(),
                        loginDTO.getPassword()));

        CurrentUserDetails user = (CurrentUserDetails) authentication.getPrincipal();


        String accessToken = Jwts.builder()
                .signWith(SignatureAlgorithm.HS256, accessTokenKey)
                .setSubject(user.getId().toString())
                .setIssuedAt(new Date(System.currentTimeMillis() + 60000))
                .setExpiration(new Date(System.currentTimeMillis() + accessTokenExpirationTime))
                .addClaims(new HashMap<>() {{
                    put("name", user.getName());
                    put("test", "how are you ?");
                }})
                .compact();

        String refreshToken = Jwts.builder()
                .signWith(SignatureAlgorithm.HS256, refreshTokenKey)
                .setSubject(user.getId().toString())
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + refreshTokenExpirationTime))
                .compact();

        return ApiResult.successResponse(TokenDTO.builder()
                .accessToken(accessToken)
                .refreshToken(refreshToken)
                .build());
    }

    @Override
    public String register(RegisterDTO registerDTO) {

        if (userRepository.existsByUsername(registerDTO.getUsername())) {
            throw RestException.restThrow("This username is available !!!");
        }
        String code = emailService.sendVerificationEmail(registerDTO.getEmail());
        verificationCodes.put(registerDTO.getEmail(), code);

        checkUserFields(registerDTO);

        return "Verification code sent to " + registerDTO.getEmail();
    }

    @Override
    public ApiResult<TokenDTO> verifyAndLogin(String code, RegisterDTO registerDTO) {
        if (!verificationCodes.containsKey(registerDTO.getEmail()) ||
                !verificationCodes.get(registerDTO.getEmail()).equals(code)) {
            throw new IllegalArgumentException("Invalid verification code");
        }

        User user = User.builder()
                .name(registerDTO.getName())
                .surname(registerDTO.getSurname())
                .username(registerDTO.getUsername())
                .email(registerDTO.getEmail())
                .password(passwordEncoder.encode(registerDTO.getPassword()))
                .role(Role.USER)
                .build();
        userRepository.save(user);
        return login(LoginDTO.builder()
                .username(registerDTO.getUsername())
                .password(registerDTO.getPassword())
                .build());
    }

    private void checkUserFields(RegisterDTO registerDTO) {
        // todo check user fields
    }
}
