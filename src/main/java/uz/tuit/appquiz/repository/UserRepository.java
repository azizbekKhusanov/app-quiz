package uz.tuit.appquiz.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.tuit.appquiz.entity.User;
import uz.tuit.appquiz.enums.Role;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    boolean existsByUsername(String username);
    Optional<User> findByEmail(String email);
    List<User> findByRole(Role role);
    Optional<User> findByUsername(String username);
    boolean existsByEmail(String email);
}
