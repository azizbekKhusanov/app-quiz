package uz.tuit.appquiz.config;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import uz.tuit.appquiz.entity.User;
import uz.tuit.appquiz.enums.Role;
import uz.tuit.appquiz.repository.UserRepository;

@Component
@RequiredArgsConstructor
public class DataLoader implements CommandLineRunner {

    private final UserRepository userRepository;

    @Override
    public void run(String... args) {
        if (userRepository.findAll().size() == 0) {
            User superAdmin = User.builder()
                    .name("Super_admin")
                    .surname("Super_admin")
                    .username("Super_admin")
                    .email("superadmin@gmail.com")
                    .role(Role.SUPER_ADMIN)
                    .password("app_quiz")
                    .build();
            userRepository.save(superAdmin);
            System.out.println("User created successfully !!!");

            User admin = User.builder()
                    .name("Admin")
                    .surname("Admin")
                    .username("Admin")
                    .email("admin@gmail.com")
                    .role(Role.ADMIN)
                    .password("app_quiz")
                    .build();
            userRepository.save(admin);
            System.out.println("Admin created successfully !!!");

            User user = User.builder()
                    .name("User")
                    .surname("User")
                    .username("User")
                    .email("user@gmail.com")
                    .role(Role.USER)
                    .password("app_quiz")
                    .build();
            userRepository.save(user);
            System.out.println("Super admin created successfully !!!");
        }
    }
}
