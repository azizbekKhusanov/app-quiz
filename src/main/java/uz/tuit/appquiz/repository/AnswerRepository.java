package uz.tuit.appquiz.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.tuit.appquiz.entity.Answer;

public interface AnswerRepository extends JpaRepository<Answer, Long> {

}
