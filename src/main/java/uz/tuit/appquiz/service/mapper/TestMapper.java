package uz.tuit.appquiz.service.mapper;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import uz.tuit.appquiz.dto.CreateTestDTO;
import uz.tuit.appquiz.dto.TestDTO;
import uz.tuit.appquiz.entity.Subject;
import uz.tuit.appquiz.entity.Test;
import uz.tuit.appquiz.exceptions.RestException;
import uz.tuit.appquiz.repository.SubjectRepository;

import java.util.ArrayList;

@Component
@RequiredArgsConstructor
public class TestMapper {
    private final QuestionMapper questionMapper;
    private final SubjectRepository subjectRepository;

    public Test convertToEntity(CreateTestDTO createTestDTO) {
        Subject subject = subjectRepository.findById(createTestDTO.getSubjectId())
                .orElseThrow(() -> RestException.restThrow("Subject not found", HttpStatus.BAD_REQUEST));

        return Test.builder()
                .name(createTestDTO.getName())
                .description(createTestDTO.getDescription())
                .subject(subject)
                .build();
    }

    public TestDTO convertToDTO(Test test) {
        return TestDTO.builder()
                .id(test.getId())
                .name(test.getName())
                .description(test.getDescription())
                .createdAt(test.getCreatedAt().toString())
                .subjectName(test.getSubject().getName())
                .totalQuestion(test.getQuestions() != null ? test.getQuestions().size() : 0)
                .allQuestions(test.getQuestions() != null ? test.getQuestions()
                        .stream()
                        .map(questionMapper::convertToDTO)
                        .toList() : new ArrayList<>())
                .build();
    }

}
