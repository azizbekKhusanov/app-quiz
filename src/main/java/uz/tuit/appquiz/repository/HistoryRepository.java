package uz.tuit.appquiz.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.tuit.appquiz.entity.History;

import java.util.List;
import java.util.Optional;

public interface HistoryRepository extends JpaRepository<History, Long> {
    Optional<History> findTopByUserIdAndTestIdOrderByCreatedAtDesc(Long user_id, Long test_id);
    List<History> findByUserId(Long id);
    List<History> findByTestId(Long id);
}
