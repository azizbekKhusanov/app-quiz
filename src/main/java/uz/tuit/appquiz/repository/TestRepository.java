package uz.tuit.appquiz.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.tuit.appquiz.entity.Test;

import java.util.List;

public interface TestRepository extends JpaRepository<Test, Long> {
    List<Test> findBySubjectId(Long subjectId);
    boolean existsByNameAndSubjectId(String name, Long id);
}
