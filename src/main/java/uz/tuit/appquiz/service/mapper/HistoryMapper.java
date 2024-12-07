package uz.tuit.appquiz.service.mapper;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import uz.tuit.appquiz.dto.HistoryDTO;
import uz.tuit.appquiz.entity.History;
import uz.tuit.appquiz.entity.Subject;
import uz.tuit.appquiz.exceptions.RestException;
import uz.tuit.appquiz.repository.SubjectRepository;
import uz.tuit.appquiz.repository.UserRepository;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class HistoryMapper {

    private final SubjectRepository subjectRepository;

    public HistoryDTO convertToDTO(History history){
        Subject subject = subjectRepository.findById(history.getTest().getId())
                .orElseThrow(() -> RestException
                        .restThrow("Subject not found !!! "));
        return HistoryDTO.builder()
                .id(history.getId())
                .totalScore(history.getTotalScore())
                .username(history.getUser().getName() + " " + history.getUser().getSurname())
                .testName(history.getTest().getName())
                .subjectName(subject.getName())
                .createdAt(history.getCreatedAt().toString())
                .build();
    }
}
