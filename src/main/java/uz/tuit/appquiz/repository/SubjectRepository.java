package uz.tuit.appquiz.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.tuit.appquiz.entity.Subject;

import java.util.Optional;

public interface SubjectRepository extends JpaRepository<Subject, Long> {
    boolean existsByName(String name);

    Optional<Subject> findByName(String name);

}
