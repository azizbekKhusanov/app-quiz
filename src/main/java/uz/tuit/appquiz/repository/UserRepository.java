package uz.tuit.appquiz.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.tuit.appquiz.entity.User;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    boolean existsByEmail(String email);
    Optional<User> findByEmail(String email);
    Optional<User> findByUsername(String username);

}
