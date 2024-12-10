package uz.tuit.appquiz.config;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import uz.tuit.appquiz.entity.User;
import uz.tuit.appquiz.enums.Role;
import uz.tuit.appquiz.repository.UserRepository;

@Component
@RequiredArgsConstructor
public class DataLoader implements CommandLineRunner {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public void run(String... args) throws Exception {
        if(userRepository.findAll().size() == 0){
            User build = User.builder()
                    .name("Azizbek")
                    .surname("Xusanov")
                    .username("Azizbek_077")
                    .email("xusanov0511@gmail.com")
                    .role(Role.SUPER_ADMIN)
                    .password(passwordEncoder.encode("Az1zbek_077"))
                    .build();
            userRepository.save(build);
            System.out.println("Super admin created successfully !!!");
        }
    }
}
