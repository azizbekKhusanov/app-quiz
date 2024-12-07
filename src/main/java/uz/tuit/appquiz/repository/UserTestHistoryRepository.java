package uz.tuit.appquiz.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.tuit.appquiz.entity.Question;

public interface UserTestHistoryRepository extends JpaRepository<Question, Long> {

}
