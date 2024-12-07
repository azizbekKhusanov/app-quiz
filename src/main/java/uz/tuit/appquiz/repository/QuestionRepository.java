package uz.tuit.appquiz.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.tuit.appquiz.entity.Question;
import uz.tuit.appquiz.entity.Test;

public interface QuestionRepository extends JpaRepository<Question, Long> {

}
